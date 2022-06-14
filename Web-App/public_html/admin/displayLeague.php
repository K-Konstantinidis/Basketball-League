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

				if(isset($_GET['cid']) && !empty($_GET['cid'])) {
					$championship_id = $_GET['cid'];

					$dbh = connectDB();

					try {
						// Get the number of games
						$sql = 'SELECT COUNT(id) AS num_games FROM round WHERE championship_id = :cid;';

						$stmt = $dbh->prepare($sql);
						$stmt->bindParam(':cid', $championship_id, PDO::PARAM_INT);
						$stmt->execute();

						$results = $stmt->fetchAll();
						$num_games = (int) $results[0]['num_games'];
					}
					catch(PDOException $ex) {
						echo 'ERROR while fetching the number of games. Reason: ' . $ex->getMessage();
						die();
					}

					// Get the individual games
					$sql =
					'SELECT g.round_id AS round, h_team.name_gr AS home_team, a_team.name_gr AS away_team
					FROM game g
					JOIN team h_team on h_team.id = g.home_team_id
					JOIN team a_team on a_team.id = g.away_team_id
					WHERE g.championship_id = :cid
					ORDER BY g.round_id;';

					try {
						$stmt = $dbh->prepare($sql);
						$stmt->bindParam(':cid', $championship_id, PDO::PARAM_INT);
						$stmt->execute();

						$results = array_values($stmt->fetchAll());
					}
					catch(PDOException $ex) {
						echo 'ERROR while fetching the results. Reason: ' . $ex->getMessage();
						die();
					}

					$matches_per_round = count($results) / $num_games;

					echo "\n" . '<div class="row align-self-center mb-5">' . "\n";

					for($i = 0; $i < $num_games; ++$i) {
						echo '<div class="col-lg-4 text-center">' . "\n";
							echo '<div class="border p-3 m-3">' . "\n";
								echo '<h5>Αγωνιστική ' . ($i + 1) . '</h5>' . "\n";
								echo '<hr>' . "\n";

								for($j = 0; $j < $matches_per_round; ++$j) {
									echo '<span> ' . $results[$j + $i*$matches_per_round]['home_team'] .
										 ' - ' . $results[$j + $i*$matches_per_round]['away_team'] . "<br>\n";
								}

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
			<a href="<?php echo AREF_DIR_ADMIN ?>" class="btn btn-primary mb-5 me-3" role="button">Αρχική</a>
			<a href="<?php echo AREF_ADMIN_AVAILABLE_LEAGUES ?>" class="btn btn-success mb-5 me-3" role="button">Διαθέσιμα Πρωταθλήματα</a>
		</div>

		<br><br>

		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>

	</body>
	
</html>
