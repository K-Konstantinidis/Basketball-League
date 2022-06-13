<?php

/**
 * Web service which returns the statistics of a given ongoing match.
 * The data is returned as a JSON file.
 * 
 * As parameters, it requires:
 * 		lang	-> The language in which the results will be returned.
 * 		cid		-> The championship's ID in the database
 * 		rid		-> The round's ID in the database
 * 		gid		-> The game's ID in the database
 * 
*/


// Needed for the database connection
require_once '../resources/config.php';

// This Web Service returns it's data as a JSON, specify it
header("Content-Type: application/json");

// Check if all the necessary parameters are given
if(	isset($_GET['lang']) && !empty($_GET['lang']) &&
	isset($_GET['cid'])  && !empty($_GET['cid']) &&
	isset($_GET['rid'])  && !empty($_GET['rid']) &&
	isset($_GET['gid'])  && !empty($_GET['gid'])
) {
	if($_GET['lang'] === 'en') {
		$lang_stmt = 't.short_name_en AS name,';
	}
	else if($_GET['lang'] === 'gr') {
		$lang_stmt = 't.short_name_gr AS name,';
	}
	else {
		die('Invalid language');
	}

	$championship_id	= $_GET['cid'];
	$round_id			= $_GET['rid'];
	$game_id			= $_GET['gid'];
}
else {
	die('Not all of the necessary parameters were passed');
}

// Connect to the database
$dbh = connectDB();

// The array which will be turned into the returned JSON
$data = array();

// The query
$sql =
'SELECT ' . $lang_stmt . '
	t.logo_path AS logo,
	t.id AS tid,
	SUM(two_points_in+two_points_out+three_points_in+three_points_out) AS total_shots,
	SUM(two_points_in+three_points_in) AS shots_made,
	ROUND(100*(SUM(two_points_in)/SUM(two_points_in+two_points_out)),2) AS perc_2_in,
	ROUND(100*(SUM(three_points_in)/SUM(three_points_in+three_points_out)),2) AS perc_3_in,
	ROUND(100*(SUM(freethrows_in)/SUM(freethrows_in+freethrows_out)),2) AS perc_freethrows_in,
	SUM(offensive_rebounds+defensive_rebounds) AS total_rebounds,
	SUM(offensive_rebounds) AS total_offensive_rebounds, 
	SUM(defensive_rebounds) AS total_defensive_rebounds,
	SUM(assists) AS total_assists, 
	SUM(blocks) AS total_blocks,
	SUM(steals) AS total_steals, 
	SUM(turnovers) AS total_turnovers,
	SUM(fouls) AS total_fouls
FROM `ongoing_game_player_stats`
JOIN team t ON t.id = ongoing_game_player_stats.team_id
WHERE championship_id = :championship_id 
	AND round_id = :round_id
	AND game_id = :game_id
GROUP BY (t.id)';

// Prepare the statement
$stmt = $dbh->prepare($sql);

// Bind the parameters
$stmt->bindParam(':championship_id',	$championship_id,	PDO::PARAM_INT);
$stmt->bindParam(':round_id',			$round_id,			PDO::PARAM_INT);
$stmt->bindParam(':game_id',			$game_id,			PDO::PARAM_INT);

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
	$team_stats = array();
	
	$team_stats['name']						= $row['name'];
	$team_stats['logo']						= $row['logo'];
	$team_stats['total_shots']				= $row['total_shots'];
	$team_stats['shots_made']				= $row['shots_made'];
	$team_stats['perc_2_in']				= $row['perc_2_in'];
	$team_stats['perc_3_in']				= $row['perc_3_in'];
	$team_stats['perc_freethrows_in']		= $row['perc_freethrows_in'];
	$team_stats['total_rebounds']			= $row['total_rebounds'];
	$team_stats['total_offensive_rebounds'] = $row['total_offensive_rebounds'];
	$team_stats['total_defensive_rebounds'] = $row['total_defensive_rebounds'];
	$team_stats['total_assists']			= $row['total_assists'];
	$team_stats['total_blocks']				= $row['total_blocks'];
	$team_stats['total_steals']				= $row['total_steals'];
	$team_stats['total_turnovers']			= $row['total_turnovers'];
	$team_stats['total_fouls']				= $row['total_fouls'];
	
	$data[$row['tid']] = $team_stats;
}

// Print the data as JSON
echo json_encode($data);

// Disconnect from the database
unset($dbh);

?>
