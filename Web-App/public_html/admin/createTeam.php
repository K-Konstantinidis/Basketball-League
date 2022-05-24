<?php

session_start();
require_once '../../resources/config.php';

$currPage = 'createTeam';

// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: ../loggin/?r=true');
	die();
}

// Use these to display errors
$err = $suc = false;
$err_msg = 'Ενα μήνυμα σφάλματος';

$teamName_err = $teamCity_err = $teamLogo_err = $newCity_code_err = $newCity_name_err = false;

?>

<!doctype html>
<html lang="el" class="h-100">
	<head>
		<!-- Website settings -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<title>ΕΣΑΚΕ App - Δημιουργία Ομάδας</title>

		<!-- Bootstrap core CSS -->
		<link href="../css/bootstrap.min.css" rel="stylesheet"/>
		<script src="../js/bootstrap.bundle.min.js"></script>
		<style>
			li{
				margin-left: 10px;
			}

			@media (max-width: 520px) {
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
			<h1 class="mt-5">Δημιουργία Ομάδας</h1>
			<p class="lead">Συμπληρώστε τα ακόλουθα πεδία για να δημιουργήσετε μια νέα ομάδα.</p>
			
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
					echo 'Η ομάδα <strong>' . $_POST['teamName'] . '</strong> δημιουργήθηκε επιτυχώς.';
					echo '</div><br>';
				}
			?>

			<form action="" method="POST">
				<div class="form-floating">
					<input type="text" class="form-control <?php echo ($teamName_err) ? ' is-invalid' : '' ?>" id="teamName" placeholder="ΠΑΜΑΚιακός">
					<label for="teamName">Όνομα Ομάδας</label>
				</div>

				<div class="row mt-5">
					<p class="lead">Επιλέξτε την πόλη στην οποία βρίσκεται η ομάδα ή αν δεν υπάρχει, δημιουργήστε την.</p>
					<!-- Select from existing -->
					<div class="col-md-6">
						<label class="mb-1" for="teamCity">Υπάρχουσες πόλεις</label>
						<select class="form-select <?php echo ($teamCity_err) ? ' is-invalid' : '' ?>" id="teamCity_dropdown" required="">
							<option selected="" disabled="" value="">Επιλέξτε...</option>
							<option>...</option>
						</select>
					</div>
					<!-- Create New -->
					<div class="col-md-6 div-spacer">
						<label class="mb-1">Δημιουργία Πόλης</label>
						<div class="form-floating">
							<input type="text" class="form-control <?php echo ($newCity_name_err) ? ' is-invalid' : '' ?>" id="newCity_name" placeholder="Θεσσαλονίκη">
							<label for="newCity_name">Όνομα Νέας Πόλης</label>
						</div>
						<div class="form-floating">
							<input type="text" class="form-control mt-1 <?php echo ($newCity_code_err) ? ' is-invalid' : '' ?>" id="newCity_code" placeholder="Θεσσαλονίκη">
							<label for="newCity_code">Κωδικός Νέας Πόλης</label>
						</div>
					</div>
				</div>

				<label for="teamLogo" class="mt-5">Σήμα Ομάδας</label>
				<input type="file" class="form-control mt-3 <?php echo ($teamLogo_err) ? ' is-invalid' : '' ?>" id="teamLogo" accept="image/*">
				
				<div class="d-flex flex-grow-1 justify-content-center align-items-center">
					<a href="./" class="btn btn-secondary mt-5 me-3 btn-single-line" role="button">Αρχική</a>
					<button type="button" class="btn btn-danger mt-5 me-3">Εκκαθάριση Φόρμας</button>
					<button type="button" class="btn btn-success mt-5 me-3">Καταχώριση Ομάδας</button>
				</div>

			</form>


			<br>
			<br>
		</div>
		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>
	</body>
	
</html>
