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
SUM(q_t_score.quarter_score) AS total_score, GROUP_CONCAT(q_t_score.quarter_score) AS scores
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
		
	$scores_data['name']		= $row['name'];
	$scores_data['total_score']	= $row['total_score'];
	$scores_data['scores']		= $row['scores'];
	
	$data[$row['name']] = $scores_data;
}

// Print the data as JSON
echo json_encode($data);

// Disconnect from the database
unset($dbh);

?>
