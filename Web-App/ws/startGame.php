<?php

/**
 * Web service which prepares the "ongoing_game_player_stats" table
 * and the "team_scores_per_quarter" table for a game that is starting.
 * It also updates the status field in the "game" table.
 * 
 * As parameters, it requires:
 * 		cid		-> The championship's ID in the database
 * 		rid		-> The round's ID in the database
 * 		gid		-> The game's ID in the database
 * 		thid	-> The team home ID in the database
 * 		taid	-> The away team ID in the database
 * 
 * This web service does not return anything back.
 * If the body of the page is not black, then an error has occurred.
*/


// Needed for the database connection
require_once '../resources/config.php';

// Check if all the necessary parameters are given
if(	isset($_GET['cid'])  && !empty($_GET['cid']) &&
	isset($_GET['rid'])  && !empty($_GET['rid']) &&
	isset($_GET['gid'])  && !empty($_GET['gid']) &&
	isset($_GET['thid']) && !empty($_GET['thid']) &&
	isset($_GET['taid']) && !empty($_GET['taid'])
) {
	$championship_id = $_GET['cid'];
	$round_id		 = $_GET['rid'];
	$game_id		 = $_GET['gid'];
	$team_home_id	 = $_GET['thid'];
	$team_away_id	 = $_GET['taid'];
}
else {
	die('Not all of the necessary parameters were passed');
}

// Connect to the database
$dbh = connectDB();

// Arrays to store the returned values
$players_home_team = array();
$players_away_team = array();

// Get all the players from the home team
$sql = 'SELECT id FROM `player` WHERE team_id = :home_id ORDER BY id ASC;';
$stmt = $dbh->prepare($sql);
$stmt->bindParam(':home_id', $team_home_id, PDO::PARAM_INT);

try {
	$stmt->execute();
	$players_home_team = $stmt->fetchAll();
}
catch(PDOException $ex) {
	echo 'ERROR while fetching the players of the home team. Reason: ' . $ex->getMessage();
	die();
}

// Get all the players from the home team
$sql = 'SELECT id FROM `player` WHERE team_id = :away_id ORDER BY id ASC;';
$stmt = $dbh->prepare($sql);
$stmt->bindParam(':away_id', $team_away_id, PDO::PARAM_INT);

try {
	$stmt->execute();
	$players_away_team = $stmt->fetchAll();
}
catch(PDOException $ex) {
	echo 'ERROR while fetching the players of the away team. Reason: ' . $ex->getMessage();
	die();
}

for($quarter = 1; $quarter <= 4; ++$quarter) {
	// Creates the rows to store the statistics per quarter of the game,
	//  for each player, for the home team.
	foreach($players_home_team as $player) {
		$sql = 
		"INSERT INTO `ongoing_game_player_stats` 
		(`championship_id`, `round_id`, `game_id`, `quarter`, `team_id`, `player_id`,
		`freethrows_in`, `freethrows_out`, `two_points_in`, `two_points_out`,
		`three_points_in`, `three_points_out`, `offensive_rebounds`, `defensive_rebounds`,
		`assists`, `blocks`, `steals`, `turnovers`, `fouls`)
		VALUES
		(:cid, :rid, :gid, :q, :thid, :pid, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);";

		$stmt = $dbh->prepare($sql);
		$stmt->bindParam(':cid',  $championship_id,	PDO::PARAM_INT);
		$stmt->bindParam(':rid',  $round_id,		PDO::PARAM_INT);
		$stmt->bindParam(':gid',  $game_id,			PDO::PARAM_INT);
		$stmt->bindParam(':q',    $quarter,			PDO::PARAM_INT);
		$stmt->bindParam(':thid', $team_home_id,	PDO::PARAM_INT);
		$stmt->bindParam(':pid',  $player['id'],	PDO::PARAM_INT);

		try {
			$stmt->execute();
		}
		catch(PDOException $ex) {
			echo 'ERROR while fetching the results. Reason: ' . $ex->getMessage();
			die();
		}
	}

	// Creates the rows to store the statistics per quarter of the game,
	//  for each player, for the away team.
	foreach($players_away_team as $player) {
		$sql = 
		"INSERT INTO `ongoing_game_player_stats` 
		(`championship_id`, `round_id`, `game_id`, `quarter`, `team_id`, `player_id`,
		`freethrows_in`, `freethrows_out`, `two_points_in`, `two_points_out`,
		`three_points_in`, `three_points_out`, `offensive_rebounds`, `defensive_rebounds`,
		`assists`, `blocks`, `steals`, `turnovers`, `fouls`)
		VALUES
		(:cid, :rid, :gid, :q, :taid, :pid, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);";

		$stmt = $dbh->prepare($sql);
		$stmt->bindParam(':cid',  $championship_id,	PDO::PARAM_INT);
		$stmt->bindParam(':rid',  $round_id,		PDO::PARAM_INT);
		$stmt->bindParam(':gid',  $game_id,			PDO::PARAM_INT);
		$stmt->bindParam(':q',    $quarter,			PDO::PARAM_INT);
		$stmt->bindParam(':taid', $team_away_id,	PDO::PARAM_INT);
		$stmt->bindParam(':pid',  $player['id'],	PDO::PARAM_INT);

		try {
			$stmt->execute();
		}
		catch(PDOException $ex) {
			echo 'ERROR while fetching the results. Reason: ' . $ex->getMessage();
			die();
		}
	}

	// Creates the rows for the score of each team per quarter, initially 0
	$sql =
	"INSERT INTO `team_score_per_quarter`
	(`championship_id`, `round_id`, `game_id`, `quarter`, `team_id`, `quarter_score`)
	VALUES
	(:cid, :rid, :gid, :q, :team_id, 0);";

	$stmt = $dbh->prepare($sql);

	$team = -1;
	$stmt->bindParam(':cid',	 $championship_id,	PDO::PARAM_INT);
	$stmt->bindParam(':rid',	 $round_id,			PDO::PARAM_INT);
	$stmt->bindParam(':gid',	 $game_id,			PDO::PARAM_INT);
	$stmt->bindParam(':q',		 $quarter,			PDO::PARAM_INT);
	$stmt->bindParam(':team_id', $team,				PDO::PARAM_INT);

	try {
		$team = $team_home_id;
		$stmt->execute();
		$team = $team_away_id;
		$stmt->execute();
	}
	catch(PDOException $ex) {
		echo 'ERROR while fetching the results. Reason: ' . $ex->getMessage();
		die();
	}
}

// Changes the game's status to "playing now"
$sql = "UPDATE `game` SET `game_status`= 1 WHERE id = :gid";
$stmt = $dbh->prepare($sql);
$stmt->bindParam(':gid', $game_id, PDO::PARAM_INT);

try {
	$stmt->execute();
}
catch(PDOException $ex) {
	echo 'ERROR while fetching the results. Reason: ' . $ex->getMessage();
	die();
}

?>
