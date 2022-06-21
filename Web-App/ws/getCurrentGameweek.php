<?php

/**
 * Web service which gets the current gameweek (numerically) 
 * and redirects to getGameweekMatches.php
 * 
 * As parameters, it requires:
 * 		cid		-> The championship's ID in the database
 * 
*/


// Needed for the database connection
require_once '../resources/config.php';

// This Web Service returns it's data as a JSON, specify it
header('Content-Type: application/json');

// Check if all the necessary parameters are given
if(isset($_GET['cid']) && !empty($_GET['cid']) ) {
	$championship_id = $_GET['cid'];
}
else {
	die('Not all of the necessary parameters were passed');
}

// Connect to the database
$dbh = connectDB();

// The array which will be turned into the returned JSON
$data = array();

$sql = "SELECT MIN(round_id) AS current_round FROM `game` WHERE championship_id = :cid AND game_status = 2";

// Prepare the statement
$stmt = $dbh->prepare($sql);

// Bind the parameters
$stmt->bindParam(':cid', $championship_id, PDO::PARAM_INT);

// Execute the statement and fetch the results
try {
	$stmt->execute();
	$result = $stmt->fetchAll();
}
catch(PDOException $ex) {
	echo 'ERROR while fetching the results. Reason: ' . $ex->getMessage();
	die();
}

// Disconnect from the database
unset($dbh);

header('Location: ./getGameweekMatches.php?cid='.$championship_id.'&rid='.$result[0]['current_round'])

?>
