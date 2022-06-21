<?php

/**
 * Web service which returns the players of a given team.
 * The data is returned as a JSON file.
 * 
 * As parameters, it requires:
 * 		lang	-> The language in which the results will be returned.
 * 		tid		-> The teams's ID in the database
 * 
*/


// Needed for the database connection
require_once '../resources/config.php';

// This Web Service returns it's data as a JSON, specify it
header("Content-Type: application/json");

// Check if all the necessary parameters are given
if(	isset($_GET['lang']) && !empty($_GET['lang']) &&
	isset($_GET['tid'])  && !empty($_GET['tid'])
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

	$team_id = $_GET['tid'];
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
'SELECT t.id AS team_id, p.id AS player_id, p.surname_en AS surname
FROM player p
JOIN team t ON t.id = p.team_id
WHERE t.id = :tid';

// Prepare the statement
$stmt = $dbh->prepare($sql);

// Bind the parameters
$stmt->bindParam(':tid', $team_id, PDO::PARAM_INT);

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
	$player_info = array();
	
	$player_info['team_id']		= $row['team_id'];
	$player_info['player_id']	= $row['player_id'];
	$player_info['surname']		= $row['surname'];
	
	$data[$row['player_id']] = $player_info;
}

// Print the data as JSON
echo json_encode($data);

// Disconnect from the database
unset($dbh);

?>
