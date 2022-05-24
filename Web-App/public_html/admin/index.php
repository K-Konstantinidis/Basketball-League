<?php

session_start();
require_once '../../resources/config.php';

// Required for the navigation bar to load properly
$currPage = 'start';

// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: ../login/?lr');
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

		<!-- Bootstrap and other required CSS -->
		<link rel="stylesheet" href="../css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./css/base.css"/>
		<link rel="stylesheet" href="./css/index.css"/>
		<script src="../js/bootstrap.bundle.min.js"></script>
  	</head>

	<body class="d-flex flex-column h-100">
	
		<header>
			<!-- Fixed navbar -->
			<?php require_once ADMIN_NAVIGATION ?>
		</header>

		<!-- Begin page content -->
		<main>
		<div class="container">
			<br>
				<h1 class="mt-5">Εφαρμογή Διαχείρισης Αγώνων</h1>
				<p class="lead">Μέσω της παρούσας εφαρμογής, μπορείτε να δημιουργήσετε πρωταθλήματα, ομάδες, παίκτες καθώς και να κληρώσετε το πρωτάθλημα!</p>
			<br>

			<div class="mb-5">
				<p>Προτού ξεκινήσετε οτιδήποτε, θα πρέπει να έχετε δημιουργήσει το πρωτάθλημά σας</p>
				<a href="./createLeague.php" class="btn btn-success" role="button">Δημιουργία Πρωταθλήματος</a>
			</div>

			<div class="mb-5">
				<p>Αφετέρου, πρέπει να δημιουργήσετε παίκτες που έπειτα θα υπάγονται σε ομάδες</p>
				<a href="./createPlayer.php" class="btn btn-primary" role="button">Δημιουργία Παίκτη</a>
			</div>

			<div class="mb-5">
				<p>Έπειτα, πρέπει να δημιουργήσετε τις ομάδες</p>
				<a href="./createTeam.php" class="btn btn-primary" role="button">Δημιουργία Ομάδας</a>
			</div>

			<div class="mb-5">
			<p>Και τέλος, θα μπορείτε να κληρώσετε το πρωτάθλημα!</p>
					<a href="./drawLeague.php" class="btn btn-warning" role="button">Κλήρωση Πρωταθλήματος</a>
			</div>

			<br>
			<br>
		</div>
		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>

	</body>
	
</html>
