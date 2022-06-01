<?php

session_start();
require_once './resources/config.php';

// If the user is logged in, he gets redirected at the administration dashboard.
if(isset($_SESSION["logged_in"]) && $_SESSION["logged_in"] === true) {
	header('Location: ' . AREF_DIR_ADMIN);
	die();
}
else { // Otherwise, he is prompted to login
	header('Location: ' . AREF_LOGIN);
	die();
}

?>