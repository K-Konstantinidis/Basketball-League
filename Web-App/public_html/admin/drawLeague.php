<?php

session_start();
require_once '../../resources/config.php';
require_once '../../resources/classes/leagueBuilder.php';

// If the user is not logged in, he gets redirected at the login page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: '. AREF_LOGIN .'?lr');
	die();
}

if(!isset($_SESSION['teams_in_league']) || !is_array( unserialize($_SESSION['teams_in_league']) )) {
	header('Location: ' . AREF_ADMIN_DISPLAY_LEAGUE . '?inv_param');
}
else {
	$builder = new LeagueBuilder(unserialize($_SESSION['teams_in_league']));

	$builder->generateDays();
	$matches = $builder->getFixtures();


	// TODO: Add the teams with SQL
	$new_league_id = 0; // This must be populated with the new league id


	// Unset the array with the participating teams
	unset($_SESSION['teams_in_league']);

	// Display the newly created league
	header('Location: ' . AREF_ADMIN_DISPLAY_LEAGUE . '?id=' . $new_league_id . '&nl');
}

?>