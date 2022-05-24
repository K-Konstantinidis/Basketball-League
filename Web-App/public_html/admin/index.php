<?php

session_start();
require_once '../../resources/config.php';

$currPage = 'start';



// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	echo 'Location: ' . DIR_LOGIN;
	header('Location: ' . DIR_LOGIN . 'index.php');
	die();
}

?>

<!doctype html>
<html lang="el" class="h-100">
	<head>
		<!-- Website settings -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<title>ΕΣΑΚΕ App - Dashboard</title>

		<!-- Bootstrap core CSS -->
		<link href="../css/bootstrap.min.css" rel="stylesheet"/>
		<script src="../js/bootstrap.bundle.min.js"></script>
		<style>
			li{
				margin-left: 10px;
			}
		</style>
  	</head>

	<body class="d-flex flex-column h-100">
	
		<header>
			<?php require_once MAIN_NAVIGATION ?>
		</header>

		<!-- Begin page content -->
		<main>
		<div class="container">
			<br>
			<h1 class="mt-5">Εφαρμογή Διαχείρισης Αγώνων</h1>
			<p class="lead">Μέσω της παρούσας εφαρμογής, μπορείτε να δημιουργήσετε πρωταθλήματα, ομάδες, παίκτες καθώς και να κληρώσετε το πρωτάθλημα!</p>
			
			<br>
			<p>Προτού ξεκινήσετε οτιδήποτε, θα πρέπει να έχετε δημιουργήσει το πρωτάθλημά σας</p>
			<button type="button" class="btn btn-success">Δημιουργία Πρωταθλήματος</button>
			

			<p class="mt-5">Αφετέρου, πρέπει να δημιουργήσετε παίκτες που έπειτα θα υπάγονται σε ομάδες</p>
			<button type="button" class="btn btn-primary">Δημιουργία Παίκτη</button>

			<p class="mt-5">Έπειτα, πρέπει να δημιουργήσετε τις ομάδες</p>
			<button type="button" class="btn btn-primary">Δημιουργία Ομάδας</button>

			<p class="mt-5">Και τέλος, θα μπορείτε να κληρώσετε το πρωτάθλημα!</p>
			<button type="button" class="btn btn-warning">Κλήρωση Πρωταθλήματος</button>
			<br>
			<br>
		</div>
		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>
	</body>
	
</html>
