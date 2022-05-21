<?php

session_start();

// If the user is logged in, he gets redirected at the administration dashboard.
if(isset($_SESSION["logged_in"]) && $_SESSION["logged_in"] === true) {
	header('Location: ./public_html/admin/');
	die();
}
else { // Otherwise, he is prompted to login
	header('Location: ./public_html/loggin/');
	die();
}

?>