<?php

/**
 * Web service which returns the 3 most recent events in a game.
 * The data is returned as a JSON file.
 * 
 * As parameters, it requires:
 * 		lang	-> The language in which the results will be returned.
 * 		cid		-> The championship's ID in the database
 * 		rid		-> The round's ID in the database
 * 		gid		-> The round's ID in the database
 * 
*/


// Needed for the database connection
require_once '../resources/config.php';

// This Web Service returns it's data as a JSON, specify it
header("Content-Type: application/json");

// Check if all the necessary parameters are given
if(	isset($_GET['lang']) && !empty($_GET['lang']) &&
	isset($_GET['cid']) && !empty($_GET['cid']) &&
	isset($_GET['rid']) && !empty($_GET['rid']) &&
	isset($_GET['gid']) && !empty($_GET['gid'])
) 
{
	if($_GET['lang'] === 'en') {
		$pname_lang_stmt = 'p.surname_en AS surname,';
		$tname_lang_stmt = 't.short_name_en AS team_name,';
		
		$p2name_lang_stmt = 'p2.surname_en AS extra_surname,';
		$t2name_lang_stmt = 't2.short_name_en AS extra_team ';
		
	}
	else if($_GET['lang'] === 'gr') {
		$pname_lang_stmt = 'p.surname_gr AS surname,';
		$tname_lang_stmt = 't.short_name_gr AS team_name,';
		
		$p2name_lang_stmt = 'p2.surname_gr AS extra_surname,';
		$t2name_lang_stmt = 't2.short_name_gr AS extra_team ';
	}
	else {
		die('Invalid language');
	}

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
$event_data = array();

// The query

$sql = 
'SELECT ge.uid, ge.event_id, ge.minute, e.template_text,' . $pname_lang_stmt . $tname_lang_stmt . 
'extra_p.extra_surname, extra_p.extra_team
FROM `game_event` ge
JOIN player p ON ge.player_id = p.id
JOIN event_info e ON ge.event_id = e.id
JOIN team t ON p.team_id = t.id
LEFT JOIN (
	SELECT p2.id AS extra_id,' . $p2name_lang_stmt . $t2name_lang_stmt .
	'FROM player p2 
	JOIN team t2 ON p2.team_id = t2.id) AS extra_p
ON extra_p.extra_id = ge.additional_player_id
WHERE championship_id = :cid AND round_id = :rid AND game_id = :gid
ORDER BY ge.last_modified DESC
LIMIT 3';


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
	$event = array();
	
	$event['uid']			= $row['uid'];
	$event['event_id']		= $row['event_id'];
	$event['minute']		= $row['minute'];
	$event['template_text']	= $row['template_text'];
	$event['surname']		= $row['surname'];
	$event['team_name']		= $row['team_name'];
	$event['extra_surname']	= $row['extra_surname'];
	$event['extra_team']	= $row['extra_team'];
	
	$event_data[$row['uid']] = $event;
}

// Print the data as JSON
echo json_encode($event_data);

// Disconnect from the database
unset($dbh);

?>
