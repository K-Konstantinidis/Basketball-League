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
				<?php
				$sql = "SELECT * FROM team";
				if($result = mysqli_query(connectDB(), $sql)){
					if(mysqli_num_rows($result) > 0){
						while($row = mysqli_fetch_array($result)){
							echo '<div class="col-md-2 border pb-3 m-1 text-center">';
								echo '<div class="custom-control custom-checkbox image-checkbox">';
									echo '<input type="checkbox" class="custom-control-input" id='. $row["short_name_en"] . '-cb />';
									echo '<span class="lead mb-3">' . $row["name_en"] . '</span><br>';
									echo '<label class="custom-control-label" for="'. $row["short_name_en"] . '-cb">';
										echo '<img src="https://source.unsplash.com/640x426/" alt="team-'. $row["id"] .'" class="img-fluid" />'; // replace unsplash with  $row["logo_path"] once there is one
									echo '</label>';
								echo '</div>';
							echo '</div>';
						}
						mysqli_free_result($result);
					}else{
						echo '<div class="alert alert-danger"><em>No available records of teams found.</em></div>';
					}
				}else{
					echo "Oops! Something went wrong. Please try again later.";
				}

				mysqli_close(connectDB());
				?>

				<br>
			</div>

			<script>

				// Unchecks all the checkboxes
				function uncheck(){
    				checkboxes.forEach((checkbox) => {
        				checkbox.checked = false;
 				   });
				}

				const checkboxes = document.querySelectorAll('input[type=checkbox]');
			</script>
			
			<div class="d-flex flex-grow-1 justify-content-center align-items-center">
				<a href="./" class="btn btn-secondary mt-5 me-3 btn-single-line" role="button">Αρχική</a>
				<button onclick="uncheck()" type="button" class="btn btn-danger mt-5 me-3">Εκκαθάριση Φόρμας</button>
				<button type="button" class="btn btn-success mt-5 me-3">Δημιουργία Πρωταθλήματος</button>
			</div>

			<br><br>
		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>

	</body>
</html>
