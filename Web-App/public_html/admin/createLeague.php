<?php
/* TODO:: 

- Add comments
- Only make it able to select 2 teams for each league? Idk how it works
- Enable checkmark if image is clicked

*/
session_start();
require_once '../../resources/config.php';

// Required for the navigation bar to load properly
$currPage = 'createLeague';

// If the user is not logged in, he gets redirected at the login page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: '. AREF_LOGIN .'?lr');
	die();
}

$warn = $err = '';

// Create the league
if($_SERVER['REQUEST_METHOD'] === 'POST') {
	if(!isset($_POST['t']) || !is_array($_POST['t'])) {
		$warn = 'Δεν ορίσθηκαν σωστά οι παράμετροι για τη δημιουργία του πρωταθλήματος.<br>Προσπαθήστε ξανά αργότερα.';
	}
	else {
		if(count($_POST['t']) % 2 != 0 || count($_POST['t']) < 4) {
			$err = 'Πρέπει να επιλέξετε τουλάχιστον 4 ομάδες, και το πλήθος των ομάδων να είναι ζυγός αριθμός.';
		}
		else {
			$_SESSION['teams_in_league'] = serialize($_POST['t']);
			header('Location: ./loadingLeague.php');
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
		<script src="./js/createLeague.js" defer></script>

		<!-- Unchecks all the checkboxes -->
		<script>
			function uncheck(){
				let checkboxes = document.querySelectorAll('input[type=checkbox]');

				checkboxes.forEach((checkbox) => {
					checkbox.checked = false;
				});
			}
			</script>
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
					displayErrorBanner($err);
				}

				if($warn) {
					displayWarrningBanner($warn);
				}
			?>

			<form class="row" method="POST" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']) ?>">
			<?php
				$conn = connectDB();
				$data = $conn->query("SELECT * FROM team")->fetchAll();
					
				if($data != null){
					$i = 0;

					foreach($data as $row) {
						echo '<div class="col-md-2 border pb-3 m-1 text-center">';
							echo '<div class="custom-control custom-checkbox image-checkbox">';
								echo '<input type="checkbox" name="t[]" value="' . $row['id'] . '" class="custom-control-input" id=' . $row['id'] . '>';
								echo '<span class="lead mb-3"> '. $row['name_en'] . '</span><br>';
								echo '<label class="custom-control-label" for="'. $row['id'] . '">';
									echo '<img src="https://source.unsplash.com/640x426/" alt="team- '. $row['id'] . '" class="img-fluid" />';  // Replace unsplash with  $row["logo_path"] once there is one -->
								echo '</label>';
							echo '</div>';
						echo '</div>';
					}

					echo '<div class="d-flex flex-grow-1 justify-content-center align-items-center">';
					echo '<a href="./" class="btn btn-secondary mt-5 me-3 btn-single-line" role="button">Αρχική</a>';
					echo '<button onclick="uncheck()" type="button" class="btn btn-danger mt-5 me-3">Εκκαθάριση Φόρμας</button>';
					echo '<button type="submit" class="btn btn-success mt-5 me-3">Δημιουργία Πρωταθλήματος</button>';
					echo '</div>';
				}
				else { //There are no teams in the database
					displayWarrningBanner('Δεν βρέθηκαν ομάδες για να συμπεριληφθούν στο πρωτάθλημα.' .
						'<br/><a class="alert-link" href='. AREF_ADMIN_CREATE_TEAM .'>Δημιουργήστε</a> ομάδες και ξαναπροσπαθήστε');
				}

				$conn = null;
			?>
			</form>
			<br><br>

		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>

	</body>
</html>
