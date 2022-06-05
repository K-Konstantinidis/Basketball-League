<?php

session_start();
require_once '../../resources/config.php';

// If the user is not logged in, he gets redirected at the login page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: '. AREF_LOGIN .'?lr');
	die();
}

?>

<!DOCTYPE html>
<html lang="el">
	<head>
		<!-- Website settings -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<title>ΕΣΑΚΕ Δημιουργία Πρωταθλήματος</title>

		<!-- Bootstrap and other required CSS -->
		<link rel="stylesheet" href="../css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./css/base.css"/>
		<link rel="stylesheet" href="./css/createLeague.css"/>
		<script src="../js/bootstrap.bundle.min.js"></script>
	</head>
<body>
	<main class="text-center">
		<br><br>
		
		<div class="spinner-border spinner-border-lg text-primary" role="status">
			<span class="visually-hidden">Loading...</span>
		</div>

		<br>
		
		<h3>Δημιουργούνται οι αγωνιστικές, παρακαλώ περιμένετε...</h1>
		<p class="lead">Αυτό μπορεί να διαρκέσει μέχρι και 30 δευτερόλεπτα!</p>

		<br>

		<p class="text-muted">Αν έχετε απενεργοποιημένη τη JavaScript, κάντε κλικ <a href="./drawLeague.php">ΕΔΩ</a></p>

		<script> window.location = "./drawLeague.php"; </script>

	</main>
</body>
</html>
