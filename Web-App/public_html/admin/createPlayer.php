<?php

session_start();

// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: ../loggin/?r=true');
	die();
}

// Use these to display errors
$err = $suc = false;
$err_msg = 'Ενα μήνυμα σφάλματος';

$playerName_err = $playerPos_err = $playerImg_err = $playerTeam_err = false;

?>

<!doctype html>
<html lang="el" class="h-100">
	<head>
		<!-- Website settings -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<title>ΕΣΑΚΕ App - Δημιουργία Παίκτη</title>

		<!-- Bootstrap core CSS -->
		<link href="../css/bootstrap.min.css" rel="stylesheet"/>
		<script src="../js/bootstrap.bundle.min.js"></script>
		<style>
			li{
				margin-left: 10px;
			}

			@media (max-width: 505px) {
				.btn-single-line {
					padding: 18px;
				}
			}

			@media (max-width: 767px) {
				.div-spacer {
					margin-top: 20px;
				}
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
						<a class="nav-link" aria-current="page" href="./">Αρχική</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./createLeague.php">Δημιουργία Πρωταθλήματος</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./createTeam.php">Δημιουργία Ομάδας</a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="#">Δημιουργία Παίκτη</a>
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
			<h1 class="mt-5">Δημιουργία Παίκτη</h1>
			<p class="lead">Συμπληρώστε τα ακόλουθα πεδία για να δημιουργήσετε έναν νέο παίκτη.</p>
			
			<br>

			<?php
				if($err) {
					echo '<div class="alert alert-danger fade show" role="alert">';
					echo '<strong>Σφάλμα!</strong><br>';
					echo $err_msg . '.';
					echo '</div><br>';
				}

				if($suc) {
					echo '<div class="alert alert-success fade show" role="alert">';
					echo '<strong>Επιτυχία!</strong><br>';
					echo 'Ο παίκτης <strong>' . $_POST['playerName'] . '</strong> δημιουργήθηκε επιτυχώς.';
					echo '</div><br>';
				}
			?>

			<form action="" method="POST">
				<div class="form-floating">
					<input type="text" class="form-control <?php echo ($playerName_err) ? ' is-invalid' : '' ?>" id="playerName" placeholder="">
					<label for="playerName">Όνομα Παίκτη</label>
				</div>

				<div class="mt-5">
					<label class="mb-1" for="playerPos">Θέση Παίκτη</label>
					<select class="form-select <?php echo ($playerPos_err) ? ' is-invalid' : '' ?>" id="playerPos">
						<option selected="" disabled="" value="">Επιλέξτε...</option>
						<option>...</option>
					</select>
				</div>

				<div class="mt-5">
					<label class="mb-1" for="playerTeam">Ομάδα Παίκτη</label>
					<select class="form-select <?php echo ($playerTeam_err) ? ' is-invalid' : '' ?>" id="playerTeam">
						<option selected="" disabled="" value="">Επιλέξτε...</option>
						<option>...</option>
					</select>
				</div>

				<label for="playerImg" class="mt-5">Φωτογραφία Παίκτη</label>
				<input type="file" class="form-control mt-3 <?php echo ($playerImg_err) ? ' is-invalid' : '' ?>" id="playerImg" accept="image/*">
				
				<div class="d-flex flex-grow-1 justify-content-center align-items-center">
					<a href="./" class="btn btn-secondary mt-5 me-3 btn-single-line" role="button">Αρχική</a>
					<button type="button" class="btn btn-danger mt-5 me-3">Εκκαθάριση Φόρμας</button>
					<button type="button" class="btn btn-success mt-5 me-3">Καταχώριση Παίκτη</button>
				</div>

			</form>


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