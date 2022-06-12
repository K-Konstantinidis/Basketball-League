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
"SELECT h_team_info.game_id AS game, h_team_info.logo AS home_logo, h_team_info.score AS home_team_score, a_team_info.logo AS away_logo, a_team_info.score AS away_team_score, h_team_info.game_status
FROM 
	(SELECT g.id AS game_id, h_team.logo_path AS logo, g.game_status AS game_status, 
	CASE WHEN g.game_status = 2 THEN -1 ELSE SUM(spq.quarter_score) END AS score
    FROM `game` g
    JOIN team h_team ON h_team.id = g.home_team_id
    LEFT JOIN team_score_per_quarter spq ON spq.championship_id=g.championship_id AND spq.round_id=g.round_id AND g.home_team_id=spq.team_id
    WHERE g.championship_id = :cid AND g.round_id = :rid
    GROUP BY (g.home_team_id)
    ORDER BY g.id) AS h_team_info
JOIN
	(SELECT g.id AS game_id, a_team.logo_path AS logo,
	CASE WHEN g.game_status = 2 THEN -1 ELSE SUM(spq.quarter_score) END AS score
    FROM `game` g
    JOIN team a_team ON a_team.id = g.away_team_id
    LEFT JOIN team_score_per_quarter spq ON spq.championship_id=g.championship_id AND spq.round_id=g.round_id AND g.away_team_id=spq.team_id
    WHERE g.championship_id = :cid AND g.round_id = :rid
    GROUP BY (g.away_team_id)
    ORDER BY g.id) AS a_team_info
ON h_team_info.game_id = a_team_info.game_id";

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

	//$nested_data['round']			= $row['round'];
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
