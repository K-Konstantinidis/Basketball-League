<?php

/**
 * Web service which returns all rounds (numerically) in the given championship.
 * 
 * As parameters, it requires:
 * 		cid		-> The championship's ID in the database
*/


// Needed for the database connection
require_once '../resources/config.php';

// Check if all the necessary parameters are given
if(	isset($_GET['cid'])  && !empty($_GET['cid']))
{
	$championship_id = $_GET['cid'];
}
else {
	die('Not all of the necessary parameters were passed');
}

// Connect to the database
$dbh = connectDB();

// Arrays to store the returned values
$all_rounds = array();

// Get all rounds for the given championship
$sql = 'SELECT id FROM `round` WHERE championship_id = :cid';
$stmt = $dbh->prepare($sql);
$stmt->bindParam(':cid', $championship_id, PDO::PARAM_INT);

try {
	$stmt->execute();
	$result = $stmt->fetchAll();
}
catch(PDOException $ex) {
	echo 'ERROR while fetching rounds for the championship. Reason: ' . $ex->getMessage();
	die();
}

foreach($result as $round) {
	$round_id = array();
	
	$round_id['id'] = $round['id'];
	
	$all_rounds[$round['id']] = $round_id;
}

// Print the data as JSON
echo json_encode($all_rounds);

// Disconnect from the database
unset($dbh);

?>
