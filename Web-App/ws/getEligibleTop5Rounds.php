<?php

/**
 * Web service which returns the league's ranking.
 * The data is returned as a JSON file.
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
if(	isset($_GET['cid'])  && !empty($_GET['cid']) ) {

	$championship_id = $_GET['cid'];
}
else {
	die('Not all of the necessary parameters were passed');
}

// Connect to the database
$dbh = connectDB();

// The array which will be turned into the returned JSON
$eligible_rounds = array();

$sql = "
SELECT t1.round_id AS id
FROM (
    SELECT round_id, COUNT(round_id) AS matches_completed
    FROM round r
    JOIN game g ON r.championship_id = g.championship_id AND r.id = g.round_id
    WHERE g.championship_id = :cid AND game_status = 0
    GROUP BY round_id) AS t1
LEFT JOIN (
    SELECT round_id, COUNT(*) AS matches_per_round
	FROM game g
	WHERE g.championship_id = :cid
	GROUP BY g.championship_id, g.round_id) AS t2
ON t1.round_id = t2.round_id
WHERE t1.matches_completed = t2.matches_per_round";

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

// Convert the returned results to a JSON compatible array
foreach($result as $round) {
	$round_id = array();

	$round_id['id'] = $round['id'];
	
	$eligible_rounds[$round['id']] = $round_id;
}

// Print the data as JSON
echo json_encode($eligible_rounds);

// Disconnect from the database
unset($dbh);

?>
