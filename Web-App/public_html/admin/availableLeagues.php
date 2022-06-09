<?php

session_start();
require_once '../../resources/config.php';

// Required for the navigation bar to load properly
$currPage = 'availableLeagues';

// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION['logged_in']) || !$_SESSION['logged_in'] === true) {
	header('Location: ' . AREF_LOGIN . '/?lr');
	die();
}

?>

<!doctype html>
<html lang="el" class="h-100">
	<head>
		<!-- Website settings -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<title>ΕΣΑΚΕ App - Πρωταθλήματα</title>

		<!-- Bootstrap and other required CSS -->
		<link rel="stylesheet" href="../css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./css/base.css"/>
		<link rel="stylesheet" href="./css/index.css"/>
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
			<h1 class="mt-5">Διαθέσιμα Πρωταθλήματα</h1>
			<p class="lead">Προβολή και τροποποίηση των πρωταθλημάτων.</p>
			<br>	

			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th class="w-50">Όνομα Πρωταθλήματος</th>
						<th class="w-50">Εργαλεία</th>
					</tr>
				</thead>
				<tbody>
					<?php

					try {
						$dbh = connectDB();

						$sql = 'SELECT * FROM championship';

						$data = $dbh->query($sql)->fetchAll();
						
						$i = 1;
						foreach($data as $row) {
							echo '<th><h4 class="pt-2">' . $i++ . '</h4></th>' . "\n";
							echo '<td><h4 class="pt-2">' . $row['name'] . '</h4></td>' . "\n";
							echo '<td><a href="'. AREF_ADMIN_DISPLAY_LEAGUE .'?cid='. $row['id'] .'" class="btn btn-success me-2 mt-1 mb-1" role="button">Προβολή</a>
							 		  <a href="./" class="btn btn-warning me-2 mt-1 mb-1 disabled" role="button">Επεξεργασία</a>
							 		  <a href="./" class="btn btn-danger me-2 mt-1 mb-1 disabled" role="button">Διαγραφή</a></td>';
						}
					}
					catch(PDOException $ex) {
						echo 'Failed to fetch the championships. Reason: ' . $ex->getMessage();
					}
					
					?>
				</tbody>
			</table>

			<div class="d-flex flex-grow-1 justify-content-center align-items-center">
				<a href="<?php echo AREF_DIR_ADMIN ?>" class="btn btn-primary mt-5 mb-5" role="button">Αρχική</a>
			</div>

		</div>
		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>

	</body>
	
</html>
