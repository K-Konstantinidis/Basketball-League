<?php

session_start();
require_once '../../resources/config.php';

// Required for the navigation bar to load properly
$currPage = 'drawLeague';

// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: ../login/?lr');
	die();
}

// Use these to display errors
$err = $suc = false;
$err_msg = 'Ένα μήνυμα σφάλματος';
$suc_msg = 'Το πρωτάθλημα δημιουργήθηκε επιτυχώς';

?>

<!doctype html>
<html lang="el" class="h-100">
	<head>
		<!-- Website settings -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<title>ΕΣΑΚΕ App - Κλήρωση Πρωταθλήματος</title>

		<!-- Bootstrap and other required CSS -->
		<link rel="stylesheet" href="../css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./css/base.css"/>
		<link rel="stylesheet" href="./css/displayLeague.css"/>
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
			<h1 class="mt-5">Κλήρωση Πρωταθλήματος</h1>
			<p class="lead">Οι παρακάτω ομάδες συμμετέχουν στο πρωτάθλημα:</p>
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

			<ul class="list-group">
				<?php
					// TODO display the teams
					for($i = 0; $i < 10; ++$i) { // Change the control variable
						echo '<li class="list-group-item">' . $team_name = 'pain' . '</li>'; // Remove the "= ''"
					}

				?>
			</ul>
		</div>
			
		<div class="d-flex flex-grow-1 justify-content-center align-items-center">
			<a href="./" class="btn btn-secondary mt-5 me-3 btn-single-line" role="button">Αρχική</a>
			<button type="button" class="btn btn-success mt-5 me-3">Κλήρωση Πρωταθλήματος</button>
		</div>

		<br><br>
		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>

	</body>
	
</html>
