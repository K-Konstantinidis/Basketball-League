<?php

// Check if all the necessary parameters are given
/**
 * Web service which returns the statistics of a given ongoing match.
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
header("Content-Type: application/json");

// Check if all the necessary parameters are given
if(	isset($_GET['lang']) && !empty($_GET['lang']) &&
	isset($_GET['cid']) && !empty($_GET['cid'])
)
{
	if($_GET['lang'] === 'en') {
		$lang_stmt = 'p.surname_en AS surname,';
	}
	else if($_GET['lang'] === 'gr') {
		$lang_stmt = 'p.surname_gr AS surname,';
	}
	else {
		die('Invalid language');
	}

	$championship_id	= $_GET['cid'];
}
else {
	// An empty JSON will be returned if any needed
	//  parameter is not given.
	die();
}

// Connect to the database
$dbh = connectDB();

// The array which will be turned into the returned JSON
$data = array();

// The query
$sql =
'SELECT logo, name, team_id,
	ROUND(AVG(total_points),2) 				AS avg_total_points,
	ROUND(AVG(total_freethrows_out),2) 		AS avg_freethrows_out,
    ROUND(AVG(total_two_points_out),2) 		AS avg_two_points_out,
    ROUND(AVG(total_three_points_out),2) 	AS avg_three_points_out,
    ROUND(AVG(total_shots),2) 				AS avg_total_shots,
    ROUND(AVG(shots_made),2) 				AS avg_shots_made,
    ROUND(AVG(perc_2_in),2) 				AS avg_perc_2_in,
    ROUND(AVG(perc_3_in),2) 				AS avg_perc_3_in,
    ROUND(AVG(perc_freethrows_in),2) 		AS avg_perc_freethrows_in,
    ROUND(AVG(total_rebounds),2) 			AS avg_total_rebounds,
    ROUND(AVG(total_offensive_rebounds),2) 	AS avg_offensive_rebounds,
    ROUND(AVG(total_defensive_rebounds),2) 	AS avg_defensive_rebounds,
    ROUND(AVG(total_assists),2) 			AS avg_assists,
    ROUND(AVG(total_blocks),2) 				AS avg_blocks,
    ROUND(AVG(total_steals),2) 				AS avg_steals,
    ROUND(AVG(total_turnovers),2) 			AS avg_turnovers,
    ROUND(AVG(total_fouls),2) 				AS avg_fouls
FROM ( 
    SELECT round_id AS rid,
    t.name_gr AS name,
    t.logo_path AS logo,
    t.id AS team_id,
    SUM(freethrows_out) AS total_freethrows_out,
    SUM(two_points_out) AS total_two_points_out,
    SUM(three_points_out) AS total_three_points_out,
    SUM(freethrows_in+2*two_points_in+3*three_points_in) AS total_points,
    SUM(two_points_in+two_points_out+three_points_in+three_points_out) AS total_shots,
    SUM(two_points_in+three_points_in) AS shots_made,
    CASE WHEN ROUND(100*(SUM(two_points_in)/SUM(two_points_in+two_points_out)),0) IS NULL THEN 0 ELSE ROUND(100*(SUM(two_points_in)/SUM(two_points_in+two_points_out)),0) END AS perc_2_in,
    CASE WHEN ROUND(100*(SUM(three_points_in)/SUM(three_points_in+three_points_out)),0) IS NULL THEN 0 ELSE ROUND(100*(SUM(three_points_in)/SUM(three_points_in+three_points_out)),0) END AS perc_3_in,
    CASE WHEN ROUND(100*(SUM(freethrows_in)/SUM(freethrows_in+freethrows_out)),0) IS NULL THEN 0 ELSE ROUND(100*(SUM(freethrows_in)/SUM(freethrows_in+freethrows_out)),0) END AS perc_freethrows_in,
    SUM(offensive_rebounds+defensive_rebounds) AS total_rebounds,
    SUM(offensive_rebounds) AS total_offensive_rebounds, 
    SUM(defensive_rebounds) AS total_defensive_rebounds,
    SUM(assists) AS total_assists, 
    SUM(blocks) AS total_blocks,
    SUM(steals) AS total_steals, 
    SUM(turnovers) AS total_turnovers,
    SUM(fouls) AS total_fouls
    FROM `player_stats`
    JOIN team t ON t.id = player_stats.team_id
    WHERE championship_id = :cid
    GROUP BY championship_id, round_id, t.id) AS temp
GROUP BY temp.team_id
ORDER BY avg_total_points DESC';

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
	$avg_team_stats = array();
	
	$avg_team_stats['logo']						= $row['logo'];
	$avg_team_stats['name']						= $row['name'];
	$avg_team_stats['team_id']					= $row['team_id'];
	$avg_team_stats['avg_total_points']			= $row['avg_total_points'];
	$avg_team_stats['avg_freethrows_out']		= $row['avg_freethrows_out'];
	$avg_team_stats['avg_two_points_out']		= $row['avg_two_points_out'];
	$avg_team_stats['avg_three_points_out']		= $row['avg_three_points_out'];
	$avg_team_stats['avg_total_shots']			= $row['avg_total_shots'];
	$avg_team_stats['avg_shots_made']			= $row['avg_shots_made'];
	$avg_team_stats['avg_perc_2_in']			= $row['avg_perc_2_in'];
	$avg_team_stats['avg_perc_3_in']			= $row['avg_perc_3_in'];
	$avg_team_stats['avg_perc_freethrows_in'] 	= $row['avg_perc_freethrows_in'];
	$avg_team_stats['avg_total_rebounds'] 		= $row['avg_total_rebounds'];
	$avg_team_stats['avg_offensive_rebounds']	= $row['avg_offensive_rebounds'];
	$avg_team_stats['avg_defensive_rebounds']	= $row['avg_defensive_rebounds'];
	$avg_team_stats['avg_assists']				= $row['avg_assists'];
	$avg_team_stats['avg_blocks']				= $row['avg_blocks'];
	$avg_team_stats['avg_steals']				= $row['avg_steals'];
	$avg_team_stats['avg_turnovers']			= $row['avg_turnovers'];
	$avg_team_stats['avg_fouls']				= $row['avg_fouls'];
	
	$data[$row['team_id']] = $avg_team_stats;
}

// Print the data as JSON
echo json_encode($data);

// Disconnect from the database
unset($dbh);

?>
