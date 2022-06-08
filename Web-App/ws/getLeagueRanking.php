<?php

/**
 * Web service which returns the league's ranking.
 * The data is returned as a JSON file.
 * 
 * As parameters, it requires:
 * 		lang	-> The language in which the results will be returned.
 * 		cid		-> The championship's ID in the database
 * 
*/


// Needed for the database connection
require_once '../resources/config.php';

// This Web Service returns it's data as a JSON, specify it
header('Content-Type: application/json');

// Check if all the necessary parameters are given
if(	isset($_GET['lang']) && !empty($_GET['lang']) &&
	isset($_GET['cid'])  && !empty($_GET['cid']))
{
	if($_GET['lang'] === 'en') {
		$lang_stmt = 'name_en';
	}
	else if($_GET['lang'] === 'gr') {
		$lang_stmt = 'name_gr';
	}
	else {
		die('Invalid language');
	}

	$championship_id = $_GET['cid'];
}

// Connect to the database
$dbh = connectDB();

// The array which will be turned into the returned JSON
$data = array();

$sql = "
SELECT logo, name, matches_played, (2*wins+(matches_played-wins)) as points, wins, (matches_played-wins) AS losses
FROM
    (SELECT logo_path AS logo, " . $lang_stmt . " AS name, matches_played, CASE WHEN matches_won IS NULL THEN 0 ELSE matches_won END As wins
    FROM team
    LEFT JOIN 
        (SELECT team_id, count(*) AS matches_played
        FROM 
            (SELECT DISTINCT g.round_id, t.game_id, t.team_id
            FROM game g
            JOIN team_score_per_quarter t ON g.round_id = t.round_id AND g.id = t.game_id
            WHERE game_status = 0
            ORDER BY g.round_id, g.id, t.team_id) AS all_played_matches
        GROUP BY team_id
        ORDER BY team_id) AS match_count 
	ON team.id = match_count.team_id
    LEFT JOIN 
        (SELECT winner, count(*) AS matches_won
        FROM
            (SELECT h_team.round_id AS round, h_team.game_id AS game, CASE WHEN h_team.total_score > a_team.total_score THEN h_team.team_id ELSE a_team.team_id END As winner
            FROM(
                (SELECT t.championship_id, t.round_id, t.game_id, t.team_id, SUM(t.quarter_score) AS total_score, g.away_team_id
                FROM `team_score_per_quarter` t
                JOIN game g ON t.round_id = g.round_id AND t.game_id = g.id AND t.team_id!=g.away_team_id
				WHERE t.championship_id = :cid 
                GROUP BY round_id,game_id,team_id) AS h_team
            JOIN
                (SELECT t.round_id, t.game_id, t.team_id, SUM(t.quarter_score) AS total_score
                FROM `team_score_per_quarter` t
                JOIN game g ON t.round_id = g.round_id AND t.game_id = g.id AND t.team_id!=g.home_team_id
                GROUP BY round_id,game_id,team_id) AS a_team
			ON h_team.round_id = a_team.round_id AND h_team.game_id = a_team.game_id)) AS a
    GROUP BY winner) AS win_count ON team.id = win_count.winner) AS b
ORDER BY points DESC";

// Prepare the statement
$stmt = $dbh->prepare($sql);

// Bind the parameters
$stmt->bindParam(':cid',	$championship_id, PDO::PARAM_INT);

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

	$nested_data['logo'] = $row['logo'];
	$nested_data['name'] = $row['name'];
	
	$nested_data['matches_played']	= $row['matches_played'];
	$nested_data['points']			= $row['points'];
	$nested_data['wins']			= $row['wins'];
	$nested_data['losses']			= $row['losses'];
	
	$data[$row['name']] = $nested_data;
}

// Print the data as JSON
echo json_encode($data);

// Disconnect from the database
unset($dbh);

?>
