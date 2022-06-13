<?php

/**
 * Web service which returns the detailed score given match.
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
header('Content-Type: application/json');

// Check if all the necessary parameters are given
if(	isset($_GET['cid']) &&
	isset($_GET['rid']) && isset($_GET['gid']))
{

	$championship_id	= $_GET['cid'];
	$round_id			= $_GET['rid'];
	$game_id			= $_GET['gid'];
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
'SELECT t.logo_path AS logo, t.id AS id,
SUM(q_t_score.quarter_score) AS total_score
FROM `team_score_per_quarter` q_t_score
JOIN team t ON t.id = q_t_score.team_id
WHERE championship_id = :championship_id 
	AND round_id = :round_id
	AND game_id = :game_id
GROUP BY team_id
ORDER BY round_id, game_id, team_id, quarter';

// Prepare the statement
$stmt = $dbh->prepare($sql);

// Bind the parameters
$stmt->bindParam(':championship_id',	$championship_id, PDO::PARAM_INT);
$stmt->bindParam(':round_id',			$round_id, PDO::PARAM_INT);
$stmt->bindParam(':game_id',			$game_id, PDO::PARAM_INT);

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
	$scores_data = array();
		
	$scores_data['logo']		= $row['logo'];
	$scores_data['total_score']	= $row['total_score'];
	$scores_data['id']			= $row['id'];
	
	$data[$row['id']] = $scores_data;
}

// Print the data as JSON
echo json_encode($data);

// Disconnect from the database
unset($dbh);

?>
