<?php

session_start();
require_once '../../resources/config.php';

// Required for the navigation bar to load properly
$currPage = 'displayLeague';

// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: ../login/?lr');
	die();
}

// Use these to display errors
$err = false;
$err_msg = 'Ένα μήνυμα σφάλματος';

?>

<!doctype html>
<html lang="el" class="h-100">
	<head>
		<!-- Website settings -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<title>ΕΣΑΚΕ App - Προβολή Πρωταθλήματος</title>

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
			<h1 class="mt-5">Προβολή Πρωταθλήματος</h1>
			<p class="lead">Οι παρακάτω ομάδες συμμετέχουν στο πρωτάθλημα:</p>
			<br>

			<?php
				if($err) {
					echo '<div class="alert alert-danger fade show" role="alert">';
					echo '<strong>Σφάλμα!</strong><br>';
					echo $err_msg . '.';
					echo '</div><br>';
				}

				if($_SERVER['REQUEST_METHOD'] == 'GET' && isset($_GET['id'])) {
					// TODO
					// Display the league.

					echo '<div class="row align-self-center">';
						echo '<div class="col-md-4 text-center">';
						
						for($league_num = 0; $league_num <= 999; ++$league_num) {
							echo '<div class="border p-3 m-3">';
								echo '<h5>Αγωνιστική ' . $league_num . '</h5>';
								echo '<hr>';
								echo '<span>Team A - Team B</span><br>';
								echo '<span>Team A - Team B</span><br>';
								echo '<span>Team A - Team B</span><br>';
								echo '<span>Team A - Team B</span><br>';
								echo '<span>Team A - Team B</span><br>';
							echo '</div>';
						}
							
						echo '</div>';
					echo '</div>';
				}
				else {
					echo '<div class="alert alert-warning fade show" role="alert">';
					echo '<strong>Προσοχή!</strong><br>';
					echo 'Δεν ορίσθηκε αγωνιστική προς εμφάνιση.';
					echo '</div><br>';
				}
			?>
		</div>
			
		<div class="d-flex flex-grow-1 justify-content-center align-items-center">
			<a href="./" class="btn btn-secondary mb-5 me-3" role="button">Αρχική</a>
		</div>

		<br><br>

		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>

	</body>
	
</html>
