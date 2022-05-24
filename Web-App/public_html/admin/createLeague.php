<?php

session_start();
require_once '../../resources/config.php';

// Required for the navigation bar to load properly
$currPage = 'createLeague';

// If the user is not logged in, he gets redirected at the login page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: ../login/?lr');
	die();
}

// Use these to display errors
$err = $suc = false;
$err_msg = 'Ένα μήνυμα σφάλματος';
$suc_msg = 'Το πρωτάθλημα δημιουργήθηκε με επιτυχία';

?>

<!doctype html>
<html lang="el" class="h-100">
	<head>
		<!-- Website settings -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<title>ΕΣΑΚΕ App - Δημιουργία Πρωταθλήματος</title>

		<!-- Bootstrap and other required CSS -->
		<link rel="stylesheet" href="../css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./css/base.css"/>
		<link rel="stylesheet" href="./css/createLeague.css"/>
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
			<h1 class="mt-5">Δημιουργία Πρωταθλήματος</h1>
			<p class="lead">Επιλέξτε τις ομάδες για να συμμετάσχουν στο πρωτάθλημα.</p>
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

			<div class="row">
				<div class="col-md-2 border pb-3 m-1 text-center">
					<div class="custom-control custom-checkbox image-checkbox">
						<input type="checkbox" class="custom-control-input" id="ck1c"/> <!-- Change the ID! -->
						<span class="lead mb-3">Paok</span><br> <!-- Change the team name! -->
						<label class="custom-control-label" for="ck1c"> <!-- Same ID as the checkbox!!!! -->
							<img src="https://source.unsplash.com/640x426/?holiday" alt="#" class="img-fluid" /> <!-- Image path -->
						</label>
					</div>
				</div>

				<!-- Delete the rest, they are simply for showcase START-->
				<div class="col-md-2 border pb-3 m-1 text-center">
					<div class="custom-control custom-checkbox image-checkbox">
						<input type="checkbox" class="custom-control-input" id="ck1c"/> <!-- Change the ID! -->
						<span class="lead mb-3">Paok</span><br> <!-- Change the team name! -->
						<label class="custom-control-label" for="ck1c"> <!-- Same ID as the checkbox!!!! -->
							<img src="https://source.unsplash.com/640x426/?holiday" alt="#" class="img-fluid" /> <!-- Image path -->
						</label>
					</div>
				</div>

				<div class="col-md-2 border pb-3 m-1 text-center">
					<div class="custom-control custom-checkbox image-checkbox">
						<input type="checkbox" class="custom-control-input" id="ck1c"/> <!-- Change the ID! -->
						<span class="lead mb-3">Paok</span><br> <!-- Change the team name! -->
						<label class="custom-control-label" for="ck1c"> <!-- Same ID as the checkbox!!!! -->
							<img src="https://source.unsplash.com/640x426/?holiday" alt="#" class="img-fluid" /> <!-- Image path -->
						</label>
					</div>
				</div>

				<div class="col-md-2 border pb-3 m-1 text-center">
					<div class="custom-control custom-checkbox image-checkbox">
						<input type="checkbox" class="custom-control-input" id="ck1c"/> <!-- Change the ID! -->
						<span class="lead mb-3">Paok</span><br> <!-- Change the team name! -->
						<label class="custom-control-label" for="ck1c"> <!-- Same ID as the checkbox!!!! -->
							<img src="https://source.unsplash.com/640x426/?holiday" alt="#" class="img-fluid" /> <!-- Image path -->
						</label>
					</div>
				</div>

				<div class="col-md-2 border pb-3 m-1 text-center">
					<div class="custom-control custom-checkbox image-checkbox">
						<input type="checkbox" class="custom-control-input" id="ck1c"/> <!-- Change the ID! -->
						<span class="lead mb-3">Paok</span><br> <!-- Change the team name! -->
						<label class="custom-control-label" for="ck1c"> <!-- Same ID as the checkbox!!!! -->
							<img src="https://source.unsplash.com/640x426/?holiday" alt="#" class="img-fluid" /> <!-- Image path -->
						</label>
					</div>
				</div>

				<div class="col-md-2 border pb-3 m-1 text-center">
					<div class="custom-control custom-checkbox image-checkbox">
						<input type="checkbox" class="custom-control-input" id="ck1c"/> <!-- Change the ID! -->
						<span class="lead mb-3">Paok</span><br> <!-- Change the team name! -->
						<label class="custom-control-label" for="ck1c"> <!-- Same ID as the checkbox!!!! -->
							<img src="https://source.unsplash.com/640x426/?holiday" alt="#" class="img-fluid" /> <!-- Image path -->
						</label>
					</div>
				</div>
				<!-- Delete the rest, they are simply for showcase END -->
				<br>
			</div>
			
			<div class="d-flex flex-grow-1 justify-content-center align-items-center">
				<a href="./" class="btn btn-secondary mt-5 me-3 btn-single-line" role="button">Αρχική</a>
				<button type="button" class="btn btn-danger mt-5 me-3">Εκκαθάριση Φόρμας</button>
				<button type="button" class="btn btn-success mt-5 me-3">Δημιουργία Πρωταθλήματος</button>
			</div>

			<br><br>
		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>

	</body>
</html>
