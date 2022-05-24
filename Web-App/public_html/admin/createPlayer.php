<?php

session_start();
require_once '../../resources/config.php';

// Required for the navigation bar to load properly
$currPage = 'createPlayer';

// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: ../login/?lr');
	die();
}

// Use these to display errors
$err = $suc = false;
$err_msg = 'Ένα μήνυμα σφάλματος';
$suc_msg = 'Ο παίκτης <strong>' . /*$_POST['playerNameGR'] .*/ '</strong> δημιουργήθηκε επιτυχώς';

// Use them to indicate errors on the input field
$playerNameGR_err = $playerSurnameGR_err = $playerNameEN_err = $playerSurnameEN_err = $playerPos_err = $playerTeam_err = $playerImg_err = false;

?>

<!doctype html>
<html lang="el" class="h-100">
	<head>
		<!-- Website settings -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<title>ΕΣΑΚΕ App - Δημιουργία Παίκτη</title>

		<!-- Bootstrap and other required CSS -->
		<link rel="stylesheet" href="../css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./css/base.css"/>
		<link rel="stylesheet" href="./css/createPlayer.css"/>
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
					echo $suc_msg . '.';
					echo '</div><br>';
				}
			?>

			<form method="POST" action="<?php htmlspecialchars($_SERVER['PHP_SELF']) ?>">
				<!-- Name (in Greek) -->
				<div class="form-floating mb-5">
					<input type="text" class="form-control <?php echo ($playerNameGR_err) ? ' is-invalid' : '' ?>" id="playerNameGR" placeholder="">
					<label for="playerNameGR">Όνομα Παίκτη (Ελληνικά)</label>
				</div>
				<!-- Surname (in Greek) -->
				<div class="form-floating mb-5">
					<input type="text" class="form-control <?php echo ($playerSurnameGR_err) ? ' is-invalid' : '' ?>" id="playerSurnameGR" placeholder="">
					<label for="playerSurnameGR">Επώνυμο Παίκτη (Ελληνικά)</label>
				</div>

				<!-- Name (in English) -->
				<div class="form-floating mb-5">
					<input type="text" class="form-control <?php echo ($playerNameEN_err) ? ' is-invalid' : '' ?>" id="playerNameEN" placeholder="">
					<label for="playerNameEN">Όνομα Παίκτη (Αγγλικά)</label>
				</div>
				<!-- Surname (in English) -->
				<div class="form-floating mb-5">
					<input type="text" class="form-control <?php echo ($playerSurnameEN_err) ? ' is-invalid' : '' ?>" id="playerSurnameEN" placeholder="">
					<label for="playerSurnameEN">Επώνυμο Παίκτη (Αγγλικά)</label>
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
