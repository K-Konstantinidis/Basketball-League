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
		$lang_stmt = 'p.surname_en AS surname,';
	}
	else if($_GET['lang'] === 'gr') {
		$lang_stmt = 'p.surname_gr AS surname,';
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
'SELECT 
	logo, 
	surname,
	pid,
	CASE WHEN (SUM((total_points + total_rebounds + total_assists + total_steals + total_blocks) - (total_two_points_out + total_three_points_out + total_freethrows_out + total_turnovers)))>0 THEN SUM((total_points + total_rebounds + total_assists + total_steals + total_blocks) - (total_two_points_out + total_three_points_out + total_freethrows_out + total_turnovers)) ELSE 0 END 
AS rating,
	total_points,
    total_shots, 
	shots_made, 
	perc_2_in, 
	perc_3_in, 
	perc_freethrows_in, 
	total_rebounds, 
	total_offensive_rebounds, 
	total_defensive_rebounds, 
    total_assists, 
	total_blocks, 
	total_steals, 
	total_turnovers, 
	total_fouls 
FROM (
    SELECT 
        t.logo_path AS logo, 
        p.surname_en AS surname,
    	p.id AS pid,
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
    FROM `ongoing_game_player_stats`
    JOIN team t ON t.id = ongoing_game_player_stats.team_id
    JOIN player p ON p.id = ongoing_game_player_stats.player_id
    WHERE championship_id = :cid
        AND round_id = :rid
        AND game_id = :gid
    GROUP BY (p.id)
	) AS temp
GROUP BY (pid)
ORDER BY rating DESC';

// Prepare the statement
$stmt = $dbh->prepare($sql);

// Bind the parameters
$stmt->bindParam(':cid', $championship_id, PDO::PARAM_INT);
$stmt->bindParam(':rid', $round_id, PDO::PARAM_INT);
$stmt->bindParam(':gid', $game_id, PDO::PARAM_INT);

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
	$player_stats = array();
	
	$player_stats['logo']						= $row['logo'];
	$player_stats['surname']					= $row['surname'];
	$player_stats['rating']						= $row['rating'];
	$player_stats['total_points']				= $row['total_points'];
	$player_stats['total_shots']				= $row['total_shots'];
	$player_stats['shots_made']					= $row['shots_made'];
	$player_stats['perc_2_in']					= $row['perc_2_in'];
	$player_stats['perc_3_in']					= $row['perc_3_in'];
	$player_stats['perc_freethrows_in']			= $row['perc_freethrows_in'];
	$player_stats['total_rebounds']				= $row['total_rebounds'];
	$player_stats['total_offensive_rebounds'] 	= $row['total_offensive_rebounds'];
	$player_stats['total_defensive_rebounds'] 	= $row['total_defensive_rebounds'];
	$player_stats['total_assists']				= $row['total_assists'];
	$player_stats['total_blocks']				= $row['total_blocks'];
	$player_stats['total_steals']				= $row['total_steals'];
	$player_stats['total_turnovers']			= $row['total_turnovers'];
	$player_stats['total_fouls']				= $row['total_fouls'];
	
	$data[$row['pid']] = $player_stats;
}

// Print the data as JSON
echo json_encode($data);

// Disconnect from the database
unset($dbh);

?>
