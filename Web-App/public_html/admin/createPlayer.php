<?php

session_start();
require_once '../../resources/config.php';

$currPage = 'createPlayer';

// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: ' . DIR_LOGIN . '?r');
	die();
}

// Use these to display errors
$err = $suc = false;
$err_msg = 'Ενα μήνυμα σφάλματος';

$playerNameGR_err = $playerNameEN_err = $playerPos_err = $playerImg_err = $playerTeam_err = false;

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
			<?php require_once MAIN_NAVIGATION ?>
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

				echo ROOT_PATH;
			?>

			<form action="" method="POST">
				<!-- Name (in Greek) -->
				<div class="form-floating mb-5">
					<input type="text" class="form-control <?php echo ($playerNameGR_err) ? ' is-invalid' : '' ?>" id="playerNameGR" placeholder="">
					<label for="playerNameGR">Όνομα Παίκτη (Ελληνικά)</label>
				</div>

				<!-- Name (in English) -->
				<div class="form-floating mb-5">
					<input type="text" class="form-control <?php echo ($playerNameEN_err) ? ' is-invalid' : '' ?>" id="playerNameEN" placeholder="">
					<label for="playerNameEN">Όνομα Παίκτη (Λατινικά)</label>
				</div>

				<!-- Player Position -->
				<div class="mb-5">
					<label class="mb-1" for="playerPos">Θέση Παίκτη</label>
					<select class="form-select <?php echo ($playerPos_err) ? ' is-invalid' : '' ?>" id="playerPos">
						<option selected="" disabled="" value="">Επιλέξτε...</option>
						<option>...</option>
					</select>
				</div>

				<!-- Player Team -->
				<div class="mb-5">
					<label class="mb-1" for="playerTeam">Ομάδα Παίκτη</label>
					<select class="form-select <?php echo ($playerTeam_err) ? ' is-invalid' : '' ?>" id="playerTeam">
						<option selected="" disabled="" value="">Επιλέξτε...</option>
						<option>...</option>
					</select>
				</div>

				<!-- Player Image Selection -->
				<div class="mb-5">
					<label for="playerImg">Φωτογραφία Παίκτη</label>
					<input type="file" class="form-control mt-1 <?php echo ($playerImg_err) ? ' is-invalid' : '' ?>" id="playerImg" accept="image/*">
				</div>
				
				<!-- Buttons -->
				<div class="d-flex flex-grow-1 justify-content-center align-items-center mb-5">
					<a href="./" class="btn btn-secondary me-3 btn-single-line" role="button">Αρχική</a>
					<button type="button" class="btn btn-danger me-3">Εκκαθάριση Φόρμας</button>
					<button type="submit" class="btn btn-success me-3">Καταχώριση Παίκτη</button>
				</div>
			</form>

		</div>
		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>
	</body>
	
</html>
