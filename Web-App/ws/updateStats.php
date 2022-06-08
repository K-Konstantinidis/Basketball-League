<?php

/**
 * Web service which updates the statistics of an ongoing match.
 * The data is returned as a JSON file.
 * 
 * As necessary parameters, it requires:
 * 		cid		-> The championship's ID in the database
 * 		rid		-> The round's ID in the database
 * 		gid		-> The game's ID in the database
 * 		pid		-> The player's ID in the database
 * 		q		-> The quarter in which the change was made
 * 
 * CAREFUL! You must pass the full value of the statistic
 *          and NOT the amount by which it fluctuated!
 * 
 * Eg.: The player had 10 assist and made one more,
 *      you will pass "a=11" and not "a=1".
 * 
 * The statistics which can be modified are:
 *		fti		-> 'freethrows_in'
 *		fto		-> 'freethrows_out'
 *		tpi		-> 'two_points_in'
 *		tpo		-> 'two_points_out'
 *		thpi	-> 'three_points_in'
 *		thpo	-> 'three_points_out '
 *		or		-> 'offensive_reboun'
 *		dr		-> 'defensive_reboun'
 *		a		-> 'assists '
 *		b		-> 'blocks'
 *		s		-> 'steals'
 *		t		-> 'turnovers'
 *		f		-> 'fouls'
*/

// Needed for the database connection
require_once '../resources/config.php';

// This Web Service returns it's data as a JSON, specify it
header("Content-Type: application/json");

// Check if all the necessary parameters are given
if(	isset($_GET['cid']) && !empty($_GET['cid']) &&
	isset($_GET['rid']) && !empty($_GET['rid']) &&
	isset($_GET['gid']) && !empty($_GET['gid']) &&
	isset($_GET['pid']) && !empty($_GET['pid']) &&
	isset($_GET['q'])   && !empty($_GET['q'])
) {
	$championship_id	= $_GET['cid'];
	$round_id			= $_GET['rid'];
	$game_id			= $_GET['gid'];
	$player_id			= $_GET['pid'];
	$quarter			= $_GET['q'];
}
else {
	die('Not all of the necessary parameters were passed');
}

// Array with the modifiable statistics
$stats = array(
	'fti'	=> 'freethrows_in',
	'fto'	=> 'freethrows_out',
	'tpi'	=> 'two_points_in',
	'tpo'	=> 'two_points_out',
	'thpi'	=> 'three_points_in',
	'thpo'	=> 'three_points_out',
	'or'	=> 'offensive_rebounds',
	'dr'	=> 'defensive_rebounds',
	'a'		=> 'assists',
	'b'		=> 'blocks',
	's'		=> 'steals',
	't'		=> 'turnovers',
	'f'		=> 'fouls'
);

// We extract the statistics that have been modified
$mod_stats = array();
foreach($stats as $short_name => $s) {
	if(isset($_GET[$short_name]) && !empty($_GET[$short_name])) {
		$mod_stats[$s] = $_GET[$short_name];
	}
}

// Get the number of the modified statistics
$num_modified = count($mod_stats);

// At least one statistic has been modified
if($num_modified) {
	$dbh = connectDB();
	
	$sql =
	'UPDATE ongoing_game_player_stats
	WHERE championship_id = :cid, round_id = :rid, game_id = :gid, player_id = :pid
	SET ';

	// For each statistic that was changed
	foreach($mod_stats as $stat => $stat_val) {
		// Add it to the query
		$sql .= $stat . ' = :' . $stat;

		// If it is the last statistic,
		// add ';', else add ',' at the end of the query
		$sql .= (--$num_modified) ? ', ': ';';
	}

	// Bind the parameters to the prepared statement
	$stmt = $dbh->prepare($sql);

	$stmt->bindParam(':cid', $championship_id,	PDO::PARAM_INT);
	$stmt->bindParam(':rid', $round_id,			PDO::PARAM_INT);
	$stmt->bindParam(':gid', $game_id,			PDO::PARAM_INT);
	$stmt->bindParam(':pid', $player_id,		PDO::PARAM_INT);

	foreach($mod_stats as $stat => $stat_val) {
		$stmt->bindParam(':' . $stat, $mod_stats[$stat]);
	}

	// Execute the statement
	try {
		$stmt->execute();
	}
	catch(PDOException $ex) {
		echo $ex->getMessage();
	}

	// Disconnect from the database
	unset($dbh);
}

?>
