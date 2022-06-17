<?php

session_start();
require_once '../../resources/config.php';
require_once '../../resources/classes/leagueBuilder.php';

// If the user is not logged in, he gets redirected at the login page.
if(!isset($_SESSION['logged_in']) || !$_SESSION['logged_in'] === true) {
	header('Location: '. AREF_LOGIN .'?lr');
	die();
}

// Check if all the variables are set correctly
if(!isset($_SESSION['teams_in_league']) || !is_array( unserialize($_SESSION['teams_in_league']) ) ||
   !isset($_SESSION['new_championship_name']) || empty($_SESSION['new_championship_name'])
) {
	header('Location: ' . AREF_ADMIN_DISPLAY_LEAGUE . '?inv_param');
}
else {
	// Generate the game days
	$builder = new LeagueBuilder(unserialize($_SESSION['teams_in_league']));
	$builder->generateDays();
	$days = $builder->getFixtures();

	// Connect to the database
	$dbh = connectDB();

	// Create the new championship and get its ID
	try {
		// Create the championship
		$sql = 'INSERT INTO championship (name) VALUES (:c_name)';

		$stmt = $dbh->prepare($sql);
		$stmt->bindParam(':c_name', $_SESSION['new_championship_name'], PDO::PARAM_STR);
		$stmt->execute();

		// Select its ID
		$sql = 'SELECT id FROM championship WHERE name=:c_name';

		$stmt = $dbh->prepare($sql);
		$stmt->bindParam(':c_name', $_SESSION['new_championship_name'], PDO::PARAM_STR);
		$stmt->execute();
		$result = $stmt->fetch();

		// Store the ID
		$new_cid = (int) $result['id'];
		
		unset($result);
	}
	catch(PDOException $ex) {
		unsetParams();
		
		// Store the error in a session variable to display it
		$_SESSION['1'] = $ex->getMessage();
		header('Location: ' . AREF_ADMIN_DISPLAY_LEAGUE . '?db_err=1');
		die();
	}

	// Insert the rounds and games
	try {
		$num_days = count($days);
		for($i = 0; $i < $num_days; ++$i) {
			// Create the round
			$sql = "INSERT INTO round (championship_id) VALUES (:cid)";

			$stmt = $dbh->prepare($sql);
			$stmt->bindParam(':cid', $new_cid, PDO::PARAM_INT);
			$stmt->execute();

			// Create the round's games
			$num_matches = count($days[$i]);
			for($j = 0; $j < $num_matches; ++$j) {
				$sql =
				"INSERT INTO game (championship_id, round_id, id, home_team_id, away_team_id, game_status)
				VALUES (:cid, :rid, :gid, :htid, :atid, 2)";

				$stmt = $dbh->prepare($sql);
				
				$stmt->bindParam(':cid',	$new_cid,			PDO::PARAM_INT);
				$stmt->bindParam(':rid',	$i,					PDO::PARAM_INT);
				$stmt->bindParam(':gid',	$j,					PDO::PARAM_INT);
				$stmt->bindParam(':htid',	$days[$i][$j][0],	PDO::PARAM_INT);
				$stmt->bindParam(':atid',	$days[$i][$j][1],	PDO::PARAM_INT);

				$stmt->execute();
			}
		}
	}
	catch(PDOException $ex) {
		unsetParams();

		// Store the error in a session variable to display it
		$_SESSION['2'] = $ex->getMessage();
		header('Location: ' . AREF_ADMIN_DISPLAY_LEAGUE . '?db_err=2');
		die();
	}

	// Unset the array with the participating teams
	unset($_SESSION['teams_in_league']);

	// Display the newly created league
	header('Location: ' . AREF_ADMIN_DISPLAY_LEAGUE . '?id=' . $new_cid . '&nl');
}

/**
 * Unsets the given parameters.
 * It is should be called on every instance that you want to leave the page.
 */
function unsetParams() {
	if(isset($_SESSION['teams_in_league'])) {
		unset($_SESSION['teams_in_league']);
	}
	if(isset($_SESSION['new_championship_name'])) {
		unset($_SESSION['new_championship_name']);
	}
}

?>