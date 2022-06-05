<?php
	session_start();
	require_once '../../resources/config.php';
	
	if(isset($_SESSION['logged_in'])) {
		unset($_SESSION['logged_in']);

		if(isset($_SESSION['user'])) {
			unset($_SESSION['user']);
		}
	}

	header('Location: ' . AREF_LOGIN . '?lo');
	exit;
?>