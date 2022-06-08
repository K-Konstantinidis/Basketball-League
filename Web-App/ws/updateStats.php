<?php

/**
 * Web service which returns the statistics of a given ongoing match.
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
header("Content-Type: application/json");

// Check if all the necessary parameters are given
if(	isset($_GET['cid']) && isset($_GET['rid']) && isset($_GET['gid']) &&
	isset($_GET['q']) && isset($_GET['pid']))
{
	$championship_id	= $_GET['cid'];
	$round_id			= $_GET['rid'];
	$game_id			= $_GET['gid'];
	$quarter			= $_GET['q'];
	$player_id			= $_GET['pid'];
}
else {
	// An empty JSON will be returned if any needed
	//  parameter is not given.
	die();
}

$freethrows_in = (isset($_GET['freethrows_in'])) ? $_GET['freethrows_in'] : 0;

$sql = 'UPDATE ongoing_player_stats SET ';

if(isset($_GET['freethrows_in'])) {
	$sql .= 'freethrows_in = :freethrows_in ';
}


// 'freethrows_in'
// 'freethrows_out'
// 'two_points_in'
// 'two_points_out'
// 'three_points_in'
// 'three_points_out'
// 'offensive_rebounds'
// 'defensive_rebounds'
// 'assists'
// 'blocks'
// 'steals'
// 'turnovers'
// 'fouls'

?>