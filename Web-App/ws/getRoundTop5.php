<?php

/**
 * Web service which returns the statistics of a given ongoing match.
 * The data is returned as a JSON file.
 * 
 * As parameters, it requires:
 * 		lang	-> The language in which the results will be returned.
 * 		cid		-> The championship's ID in the database
 * 		rid		-> The round's ID in the database
 * 
*/


// Needed for the database connection
require_once '../resources/config.php';

// This Web Service returns it's data as a JSON, specify it
header("Content-Type: application/json");

// Check if all the necessary parameters are given
if(	isset($_GET['lang']) && !empty($_GET['lang']) &&
	isset($_GET['cid']) && !empty($_GET['cid']) &&
	isset($_GET['rid']) && !empty($_GET['rid'])
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
	$round_id			= $_GET['rid'];
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
'SELECT image, surname, pid, rating, position
FROM
    (SELECT temp1.image,
            temp1.surname, 
            temp1.p_id as pid,
            CASE WHEN (SUM((total_points + total_rebounds + assists + steals + blocks) - (two_points_out + three_points_out + freethrows_out + turnovers)))>0 THEN SUM((total_points + total_rebounds + assists + steals + blocks) - (two_points_out + three_points_out + freethrows_out + turnovers)) ELSE 0 END AS rating, position
    FROM (SELECT p.id AS p_id, '.$lang_stmt.' p.img_path as image, p.player_position_code as position, assists, steals, blocks, two_points_out, three_points_out, freethrows_out, turnovers,
            SUM(freethrows_in+2*two_points_in+3*three_points_in) AS total_points,
            SUM(offensive_rebounds+defensive_rebounds) AS total_rebounds
        FROM `player_stats` 
        JOIN player p ON p.id = player_stats.player_id
        WHERE championship_id = :cid AND round_id = :rid
        GROUP BY p.id) AS temp1 
    GROUP BY temp1.p_id) AS f1
LEFT JOIN 
	(SELECT temp2.p_id as pid2,
			CASE WHEN (SUM((total_points + total_rebounds + assists + steals + blocks) - (two_points_out + three_points_out + freethrows_out + turnovers)))>0 THEN SUM((total_points + total_rebounds + assists + steals + blocks) - (two_points_out + three_points_out + freethrows_out + turnovers)) ELSE 0 END AS rating2, position2
		FROM (SELECT p.id AS p_id, p.player_position_code as position2, assists, steals, blocks, two_points_out, three_points_out, freethrows_out, turnovers,
				SUM(freethrows_in+2*two_points_in+3*three_points_in) AS total_points,
				SUM(offensive_rebounds+defensive_rebounds) AS total_rebounds
			FROM `player_stats` 
			JOIN player p ON p.id = player_stats.player_id
			WHERE championship_id = :cid AND round_id = :rid
      GROUP BY p.id) AS temp2
   GROUP BY temp2.p_id) AS f2
ON f1.position = f2.position2 AND f1.rating < f2.rating2
WHERE f2.rating2 IS NULL
GROUP BY position
ORDER BY rating DESC';

// Prepare the statement
$stmt = $dbh->prepare($sql);

// Bind the parameters
$stmt->bindParam(':cid',	$championship_id, PDO::PARAM_INT);
$stmt->bindParam(':rid',	$round_id, PDO::PARAM_INT);

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
	$top_player = array();
	
	$top_player['image']	= $row['image'];
	$top_player['surname']	= $row['surname'];
	$top_player['rating']	= $row['rating'];
	$top_player['position']	= $row['position'];
	
	$data[$row['pid']] = $top_player;
}

// Print the data as JSON
echo json_encode($data);

// Disconnect from the database
unset($dbh);

?>