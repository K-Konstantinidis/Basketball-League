<?php

/**
 * Comment needed.
 * 
 * As parameters, it requires:
 * 		lang	-> The language in which the results will be returned.
 * 		cid		-> The championship's ID in the database
*/


// Needed for the database connection
require_once '../resources/config.php';

// This Web Service returns it's data as a JSON, specify it
header("Content-Type: application/json");

// Check if all the necessary parameters are given
if(	isset($_GET['lang']) && !empty($_GET['lang']) &&
	isset($_GET['cid'])  && !empty($_GET['cid'])
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

	$championship_id = $_GET['cid'];
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
'SELECT logo, surname, 
	ROUND(AVG(rating),2)				AS avg_rating,
	ROUND(AVG(freethrows_out),2) 		AS avg_freethrows_out,
    ROUND(AVG(two_points_out),2) 		AS avg_two_points_out,
    ROUND(AVG(three_points_out),2) 		AS avg_three_points_out,
    ROUND(AVG(total_points),2) 			AS avg_total_points,
    ROUND(AVG(total_shots),2) 			AS avg_total_shots,
    ROUND(AVG(shots_made),2) 			AS avg_shots_made,
    ROUND(AVG(perc_2_in),2) 			AS avg_perc_2_in,
    ROUND(AVG(perc_3_in),2) 			AS avg_perc_3_in,
    ROUND(AVG(perc_freethrows_in),2) 	AS avg_perc_freethrows_in,
    ROUND(AVG(total_rebounds),2) 		AS avg_total_rebounds,
    ROUND(AVG(offensive_rebounds),2) 	AS avg_offensive_rebounds,
    ROUND(AVG(defensive_rebounds),2) 	AS avg_defensive_rebounds,
    ROUND(AVG(assists),2) 				AS avg_assists,
    ROUND(AVG(blocks),2) 				AS avg_blocks,
    ROUND(AVG(steals),2) 				AS avg_steals,
    ROUND(AVG(turnovers),2) 			AS avg_turnovers,
    ROUND(AVG(fouls),2) 				AS avg_fouls
FROM
    (SELECT logo, surname, pid,
        CASE WHEN (SUM((total_points + total_rebounds + assists + steals + blocks) - (two_points_out + three_points_out + freethrows_out + turnovers)))>0 THEN SUM((total_points + total_rebounds + assists + steals + blocks) - (two_points_out + three_points_out + freethrows_out + turnovers)) ELSE 0 END AS rating,
        freethrows_out, two_points_out, three_points_out, total_points, total_shots, shots_made, perc_2_in, perc_3_in, perc_freethrows_in, total_rebounds, offensive_rebounds, defensive_rebounds, assists, blocks, steals, turnovers, fouls
    FROM (
        SELECT 
            t.logo_path AS logo, 
            '.$lang_stmt.'
            round_id AS rid,
            p.id AS pid,
            freethrows_out, two_points_out, three_points_out,
            SUM(freethrows_in+2*two_points_in+3*three_points_in) AS total_points,
            SUM(two_points_in+two_points_out+three_points_in+three_points_out) AS total_shots,
            SUM(two_points_in+three_points_in) AS shots_made,
            CASE WHEN ROUND(100*(SUM(two_points_in)/SUM(two_points_in+two_points_out)),0) IS NULL THEN 0 ELSE ROUND(100*(SUM(two_points_in)/SUM(two_points_in+two_points_out)),0) END AS perc_2_in,
            CASE WHEN ROUND(100*(SUM(three_points_in)/SUM(three_points_in+three_points_out)),0) IS NULL THEN 0 ELSE ROUND(100*(SUM(three_points_in)/SUM(three_points_in+three_points_out)),0) END AS perc_3_in,
            CASE WHEN ROUND(100*(SUM(freethrows_in)/SUM(freethrows_in+freethrows_out)),0) IS NULL THEN 0 ELSE ROUND(100*(SUM(freethrows_in)/SUM(freethrows_in+freethrows_out)),0) END AS perc_freethrows_in,
            SUM(offensive_rebounds+defensive_rebounds) AS total_rebounds, offensive_rebounds, defensive_rebounds, assists, blocks, steals, turnovers, fouls
        FROM `player_stats` pstats
        JOIN team t ON t.id = pstats.team_id
        JOIN player p ON p.id = pstats.player_id
        WHERE championship_id = :cid
        GROUP BY championship_id, round_id, player_id) AS temp
    GROUP BY temp.rid, temp.pid) AS per_game_pstats
GROUP BY per_game_pstats.pid
ORDER BY avg_rating DESC';

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
	$avg_player_stats = array();
	
	$avg_player_stats['logo']					= $row['logo'];
	$avg_player_stats['surname']				= $row['surname'];
	$avg_player_stats['avg_rating']				= $row['avg_rating'];
	$avg_player_stats['avg_freethrows_out']		= $row['avg_freethrows_out'];
	$avg_player_stats['avg_two_points_out']		= $row['avg_two_points_out'];
	$avg_player_stats['avg_three_points_out']	= $row['avg_three_points_out'];
	$avg_player_stats['avg_total_shots']		= $row['avg_total_shots'];
	$avg_player_stats['avg_perc_2_in']			= $row['avg_perc_2_in'];
	$avg_player_stats['avg_perc_3_in']			= $row['avg_perc_3_in'];
	$avg_player_stats['avg_perc_freethrows_in'] = $row['avg_perc_freethrows_in'];
	$avg_player_stats['avg_total_rebounds'] 	= $row['avg_total_rebounds'];
	$avg_player_stats['avg_offensive_rebounds']	= $row['avg_offensive_rebounds'];
	$avg_player_stats['avg_defensive_rebounds']	= $row['avg_defensive_rebounds'];
	$avg_player_stats['avg_assists']			= $row['avg_assists'];
	$avg_player_stats['avg_blocks']				= $row['avg_blocks'];
	$avg_player_stats['avg_steals']				= $row['avg_steals'];
	$avg_player_stats['avg_turnovers']			= $row['avg_turnovers'];
	$avg_player_stats['avg_fouls']				= $row['avg_fouls'];
	
	$data[$row['surname']] = $avg_player_stats;
}

// Print the data as JSON
echo json_encode($data);

// Disconnect from the database
unset($dbh);

?>
