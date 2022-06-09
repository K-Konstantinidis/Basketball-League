<?php

// TODO: Display the image from DB at the team selection

session_start();
require_once '../../resources/config.php';

// Required for the navigation bar to load properly
$currPage = 'createLeague';

// If the user is not logged in, he gets redirected at the login page.
if(!isset($_SESSION['logged_in']) || !$_SESSION['logged_in'] === true) {
	header('Location: '. AREF_LOGIN .'?lr');
	die();
}

$warn = $err = '';

// Create the league
if($_SERVER['REQUEST_METHOD'] === 'POST') {
	// Invalid parameters were passed
	if(!isset($_POST['t']) || !is_array($_POST['t'])) {
		$warn = 'Δεν ορίσθηκαν σωστά οι παράμετροι για τη δημιουργία του πρωταθλήματος.<br>Προσπαθήστε ξανά αργότερα.';
	}
	else {
		// To create a league, at least 4 teams must participate and the number of
		//  participating teams must be an even number.
		if(count($_POST['t']) % 2 != 0 || count($_POST['t']) < 4) {
			$err = 'Πρέπει να επιλέξετε τουλάχιστον 4 ομάδες, και το πλήθος των ομάδων να είναι ζυγός αριθμός.';
		}
		else {
			// Serialize the participating teams array, and display the loading page
			$_SESSION['teams_in_league'] = serialize($_POST['t']);
			header('Location: '. AREF_ADMIN_LOADING_LEAGUE);
		}
	}
}

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
				// Display any errors that may have occurred when creating the league
				if($err) {
					displayErrorBanner($err);
				}
				if($warn) {
					displayWarningBanner($warn);
				}

				$conn = connectDB();
				$data = $conn->query("SELECT * FROM team")->fetchAll();
					
				if($data != null) {
					echo '<form class="row" method="POST" action="'. htmlspecialchars($_SERVER['PHP_SELF']) . '">' . "\n";

					foreach($data as $row) {
						echo '<div class="col-md-2 border pb-3 m-1 text-center">' . "\n";
						echo '	<div class="custom-control custom-checkbox image-checkbox">' . "\n";
						echo '		<input type="checkbox" name="t[]" value="' . $row['id'] . '" class="custom-control-input" id=' . $row['id'] . '>' . "\n";
						echo '		<span class="lead mb-3"> '. $row['name_en'] . '</span><br>' . "\n";
						echo '		<label class="custom-control-label" for="'. $row['id'] . '">' . "\n";
						echo '			<img src="'. $_SERVER['HTTP_HOST'] . $row['logo_path'] . '" alt="team- '. $row['id'] . '" class="img-fluid" />' . "\n";
						echo '		</label>' . "\n";
						echo '	</div>' . "\n";
						echo '</div>' . "\n";
					}

					echo '<div class="d-flex flex-grow-1 justify-content-center align-items-center">' . "\n";
					echo '	<a href="./" class="btn btn-secondary mt-5 me-3 btn-single-line" role="button">Αρχική</a>' . "\n";
					echo '	<button type="reset" class="btn btn-danger mt-5 me-3">Εκκαθάριση Φόρμας</button>' . "\n";
					echo '	<button type="submit" class="btn btn-success mt-5 me-3">Δημιουργία Πρωταθλήματος</button>' . "\n";
					echo '</div>' . "\n";
				}
				else { //There are no teams in the database
					displayWarningBanner('Δεν βρέθηκαν ομάδες για να συμπεριληφθούν στο πρωτάθλημα.' .
						'<br/><a class="alert-link" href='. AREF_ADMIN_CREATE_TEAM .'>Δημιουργήστε</a> ομάδες και ξαναπροσπαθήστε');
				}

				$conn = null;
			?>

			</form>
			<br><br>
		</div>

		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>

	</body>
</html>
