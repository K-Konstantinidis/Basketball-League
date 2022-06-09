<?php

/**
 * Web service which returns all the matches of a given game week.
 * The data is returned as a JSON file.
 * 
 * As parameters, it requires:
 * 		cid		-> The championship's ID in the database
 * 		rid		-> The round's ID in the database
 * 
*/

// Needed for the database connection
require_once '../resources/config.php';

// This Web Service returns it's data as a JSON, specify it
header('Content-Type: application/json');

// Check if all the necessary parameters are given
if( isset($_GET['cid']) && !empty($_GET['cid']) &&
	isset($_GET['rid']) && !empty($_GET['rid'])
) {
	$championship_id = $_GET['cid'];
	$round_id		 = $_GET['rid'];
}
else {
	// An empty JSON will be returned if the parameter is not given
	die('Not all of the necessary parameters were passed');
}

// Connect to the database
$dbh = connectDB();

// The array which will be turned into the returned JSON
$data = array();

$sql =
"SELECT h_team_info.rid AS round, h_team_info.gid AS game, 
	h_team_info.logo AS home_logo, h_team_info.total_score AS home_team_score, 
	a_team_info.logo AS away_logo, a_team_info.total_score AS away_team_score, h_team_info.game_status AS game_status
FROM 
	(SELECT q_score.round_id AS rid, q_score.game_id AS gid, h_team.logo_path AS logo, SUM(quarter_score) AS total_score, g.game_status AS game_status
	FROM `team_score_per_quarter` AS q_score
	JOIN team h_team ON q_score.team_id = h_team.id
	JOIN game g ON (g.championship_id = q_score.championship_id AND g.round_id = q_score.round_id AND g.id = q_score.game_id AND g.home_team_id = q_score.team_id)
	WHERE q_score.championship_id = :cid AND q_score.round_id = :rid
	GROUP BY q_score.championship_id, q_score.round_id, q_score.game_id, q_score.team_id
	ORDER BY q_score.round_id, q_score.game_id, q_score.team_id, q_score.quarter) AS h_team_info
JOIN 
	(SELECT q_score.round_id AS rid, q_score.game_id AS gid, a_team.logo_path AS logo, SUM(quarter_score) AS total_score
	FROM `team_score_per_quarter` AS q_score
	JOIN team a_team ON q_score.team_id = a_team.id
	JOIN game g ON (g.championship_id = q_score.championship_id AND g.round_id = q_score.round_id AND g.id = q_score.game_id AND g.away_team_id = q_score.team_id)
	WHERE q_score.championship_id = :cid AND q_score.round_id = :rid
	GROUP BY q_score.championship_id, q_score.round_id, q_score.game_id, q_score.team_id
	ORDER BY q_score.round_id, q_score.game_id, q_score.team_id, q_score.quarter) AS a_team_info
ON (h_team_info.rid = a_team_info.rid AND h_team_info.gid = a_team_info.gid)";

// Prepare the statement
$stmt = $dbh->prepare($sql);

// Bind the parameters
$stmt->bindParam(':cid', $championship_id,	PDO::PARAM_INT);
$stmt->bindParam(':rid', $round_id,			PDO::PARAM_INT);

// Execute the statement and fetch the results
try {
	$stmt->execute();
	$result = $stmt->fetchAll();
}
catch(PDOException $ex) {
	echo 'ERROR while fetching the results. Reason: ' . $ex->getMessage();
	die();
}

// Convert the returned results to a JSON compatible array
foreach($result as $row) {
	$nested_data = array();

	$nested_data['round']			= $row['round'];
	$nested_data['game']			= $row['game'];
	$nested_data['home_logo']		= $row['home_logo'];
	$nested_data['home_team_score']	= $row['home_team_score'];
	$nested_data['away_logo']		= $row['away_logo'];
	$nested_data['away_team_score']	= $row['away_team_score'];
	$nested_data['game_status']		= $row['game_status'];
	
	$data[$row['game']] = $nested_data;
}

// Print the data as JSON
echo json_encode($data);

// Disconnect from the database
unset($dbh);

?>
