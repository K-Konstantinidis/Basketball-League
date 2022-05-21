<?php

session_start();

// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: ../loggin/?r=true');
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
			<!-- Fixed navbar -->
			<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
				<div class="container-fluid">
				<a class="navbar-brand" href="#">ΕΣΑΚΕ Managment App</a>
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarCollapse">
					<ul class="navbar-nav me-auto mb-2 mb-md-0">
					
					<li class="nav-item">
						<a class="nav-link active" aria-current="page" href="#">Αρχική</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./createLeague.php">Δημιουργία Πρωταθλήματος</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./createTeam.php">Δημιουργία Ομάδας</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./createPlayer.php">Δημιουργία Παίκτη</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./drawLeague.php">Κλήρωση Πρωταθλήματος</a>
					</li>
					
					</ul>
					<form class="d-flex" action="../logout.php">
						<button class="btn btn-outline-danger" type="submit">Αποσύνδεση</button>
					</form>
				</div>
				</div>
			</nav>
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
		<footer class="footer mt-auto py-3 bg-dark">
			<div class="container">
				<span class="text-muted">Εργασία Εξαμήνου, Ανάπτυξη Εφαρμογών για Κινητές Συσκευές - 6ο Εξάμηνο.</span><br>
				<span class="text-muted">&copy; Copyright Ομάδα 1, 2021-2022 .</span>
			</div>
		</footer>
	</body>
	
</html>
