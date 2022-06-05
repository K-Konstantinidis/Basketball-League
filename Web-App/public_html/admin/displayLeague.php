<?php

session_start();
require_once '../../resources/config.php';

// Required for the navigation bar to load properly
$currPage = 'displayLeague';

// If the user is not logged in, he gets redirected at the login page.
if(!isset($_SESSION['logged_in']) || !$_SESSION['logged_in'] === true) {
	header('Location: ' . AREF_LOGIN . '?lr');
	die();
}

// Use this to display error messages
$err_msg = '';

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
				if($err_msg) {
					displayErrorBanner($err_msg);
				}

				// Newly created league
				if(isset($_GET['nl'])) {
					displaySuccessBanner('Η αγωνιστική δημιουργήθηκε επιτυχώς.');
				}

				if(isset($_GET['id'])) {
					// TODO
					// Display the league.

					echo "\n" . '<div class="row align-self-center">' . "\n";
						
					for($league_num = 0; $league_num <= 10; ++$league_num) {
						echo '<div class="col-md-4 text-center">' . "\n";
							echo '<div class="border p-3 m-3">' . "\n";
								echo '<h5>Αγωνιστική ' . $league_num . '</h5>' . "\n";
								echo '<hr>' . "\n";
								echo '<span>Team A - Team B</span><br>' . "\n";
								echo '<span>Team A - Team B</span><br>' . "\n";
								echo '<span>Team A - Team B</span><br>' . "\n";
								echo '<span>Team A - Team B</span><br>' . "\n";
								echo '<span>Team A - Team B</span><br>' . "\n";
							echo '</div>' . "\n";
						echo '</div>' . "\n";
					}
							
					echo '</div>' . "\n";
				}
				elseif(isset($_GET['inv_param'])) {
					displayWarningBanner('Κάτι δεν πήγε καλά. Προσπαθήστε ξανά αργότερα');
				}
				else {
					displayWarningBanner('Δεν ορίσθηκε αγωνιστική προς εμφάνιση.');
				}
			?>
		</div>
			
		<div class="d-flex flex-grow-1 justify-content-center align-items-center">
			<a href="./" class="btn btn-primary mb-5 me-3" role="button">Αρχική</a>
		</div>

		<br><br>

		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>

	</body>
	
</html>
