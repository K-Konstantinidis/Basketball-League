<?php
/* TODO::

- Gray out fields if existing team is selected and likewise

*/

session_start();
require_once '../../resources/config.php';

// Data filtering
function filter_data($data) {
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}

// Required for the navigation bar to load properly
$currPage = 'createTeam';

// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: ../login/?lr');
	die();
}

$conn = connectDB();

// Binding vars to actual form inputs
$stmt = $conn->prepare("INSERT INTO team (city_id, name_en, name_gr, short_name_en, short_name_gr, logo_path)
VALUES (:team_city, :name_en, :name_gr, :short_name_en, :short_name_gr, :logo_path)");
$stmt->bindParam(':team_city', $teamCity);
$stmt->bindParam(':name_en', $teamNameEN);
$stmt->bindParam(':name_gr', $teamNameGR);
$stmt->bindParam(':short_name_en', $teamCodeEN);
$stmt->bindParam(':short_name_gr', $teamCodeGR);
$stmt->bindParam(':logo_path', $teamLogo);

// Use these to display errors
$err = $suc = false;
$err_msg = 'Ένα μήνυμα σφάλματος';

$teamNameGR = $teamNameEN = $teamCodeGR = $teamCodeEN = $teamCity = $newCity_nameGR = $newCity_nameEN = $teamLogo = "";
$teamNameGR_err = $teamNameEN_err = $teamCodeGR_err = $teamCodeEN_err = $teamCity_err = $newCity_nameGR_err = $newCity_nameEN_err = $teamLogo_err = false;

if ($_SERVER["REQUEST_METHOD"] == "POST") {

	$count = 0;

	// Name data filter
	if(empty($_POST["name_en"])) $teamNameEN_err = "Παρακαλώ συμπληρώστε το όνομα του παίκτη στα Αγγλικά"; 
		else { 
			$teamNameEN = filter_data($_POST["name_en"]);
			$count++;
	 	}
		 
	if(empty($_POST["name_gr"])) $teamNameGR_err = "Παρακαλώ συμπληρώστε το επίθετο του παίκτη στα Αγγλικά"; 
		else { 
			 $teamNameGR = filter_data($_POST["name_gr"]);
			 $count++;
		}
			
	if(empty($_POST["short_name_en"])) $teamCodeEN_err = "Παρακαλώ συμπληρώστε το όνομα στα Ελληνικά"; 
		else { 
			$teamCodeEN = filter_data($_POST["short_name_en"]);
			$count++;
		}

	if(empty($_POST["short_name_gr"])) $teamCodeGR_err = "Παρακαλώ συμπληρώστε το επίθετο του παίκτη στα Ελληνικά"; 
		else { 
			$teamCodeGR = filter_data($_POST["short_name_gr"]);
			$count++;
		}

	if(empty($_POST["team_city"])) $playerTeam_err = "Παρακαλώ επιλέξτε την πόλυ στην οποία βασίζεται η ομάδα";  // o 
		else { 
			$temp = $_POST["team_city"];

			$query = $conn->prepare('SELECT * FROM city WHERE name_gr LIKE ?');
			$query->bindValue(1, "$temp", PDO::PARAM_STR);
			$query->execute();

			try{
				$result = $query->fetch();
				$teamCity = $result['id'];
			}catch(PDOException $e){
				$e->getMessage();
			}
			$count++;
		}
		
	// if(empty($_POST["logo_path"])) $teamLogo_err = "Παρακαλώ ανεβάστε την εικόνα του παίκτη"; 
	// 	else { 
	// 		$teamLogo = $_POST["logo_path"];
	// 		$count++;
	// 	}

	if($count==5) $stmt->execute();

}

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
		<link rel="stylesheet" href="../css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./css/base.css"/>
		<link rel="stylesheet" href="./css/createTeam.css"/>
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
					echo $suc_msg . '.';
					echo '</div><br>';
				}
			?>

			<form method="POST" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']) ?>">
				<!-- Name (Greek) -->
				<div class="form-floating mb-5">
					<input type="text" name="name_gr" class="form-control <?php echo ($teamNameGR_err) ? ' is-invalid' : '' ?>" id="teamNameGR" placeholder="">
					<label for="teamNameGR">Όνομα Ομάδας (Ελληνικά)</label>
				</div>
				<!-- Name (English) -->
				<div class="form-floating mb-5">
					<input type="text" name="name_en" class="form-control <?php echo ($teamNameEN_err) ? ' is-invalid' : '' ?>" id="teamNameEN" placeholder="">
					<label for="teamNameEN">Όνομα Ομάδας (Αγγλικά)</label>
				</div>
				<!-- Team Code (English) -->
				<div class="form-floating mb-5">
					<input type="text" name="short_name_en" class="form-control <?php echo ($teamCodeEN_err) ? ' is-invalid' : '' ?>" id="teamCodeEN" placeholder="">
					<label for="teamCodeEN">Κωδικός ομάδας (Αγγλικά)</label>
				</div>
				<!-- Team Code (Greek) -->
				<div class="form-floating mb-5">
					<input type="text" name="short_name_gr" class="form-control <?php echo ($teamCodeGR_err) ? ' is-invalid' : '' ?>" id="teamCodeGR" placeholder="">
					<label for="teamCodeGR">Κωδικός ομάδας (Ελληνικά)</label>
				</div>

				<!-- City -->
				<div class="row mb-5">
					<p class="lead">Επιλέξτε την πόλη στην οποία βρίσκεται η ομάδα ή αν δεν υπάρχει, δημιουργήστε την.</p>
					
					<!-- Select from existing -->
					<div class="col-md-6">
						<label class="mb-1" for="teamCity_dropdown">Υπάρχουσες πόλεις</label>
						<select name="team_city" class="form-select <?php echo ($teamCity_err) ? ' is-invalid' : '' ?>" id="teamCity_dropdown">
							<option selected="" disabled="" value="">Επιλέξτε...</option>
							<?php
							$data = $conn->query("SELECT * FROM city")->fetchAll();
							if($data!=null){
								foreach($data as $row) {
									echo '<option>' . $row["name_gr"] . ' </option>'; // Greek
									// echo '<option>' . $row["name_en"] . ' </option>'; // English
								}
						}
						?>
						</select>
					</div>
					
					<!-- Create New -->
					<div class="col-md-6 div-spacer">
						<label class="mb-1">Δημιουργία Πόλης</label>
						
						<!-- New city name (Greek) -->
						<div class="form-floating mb-3">
							<input type="text" class="form-control <?php echo ($newCity_nameGR_err) ? ' is-invalid' : '' ?>" id="newCity_nameGR" placeholder="">
							<label for="newCity_nameGR">Όνομα Νέας Πόλης (Ελληνικά)</label>
						</div>
						<!-- New city name (English) -->
						<div class="form-floating mb-3">
							<input type="text" class="form-control <?php echo ($newCity_nameEN_err) ? ' is-invalid' : '' ?>" id="newCity_nameEN" placeholder="">
							<label for="newCity_nameEN">Όνομα Νέας Πόλης (Αγγλικά)</label>
						</div>
					</div>
				</div>

				<!-- Logo -->
				<label for="teamLogo" class="mb-1">Σήμα Ομάδας</label>
				<input type="file" name="logo_path" class="form-control mb-5 <?php echo ($teamLogo_err) ? ' is-invalid' : '' ?>" id="teamLogo" accept="image/*">
				
				<div class="d-flex flex-grow-1 justify-content-center align-items-center mb-3">
					<a href="./" class="btn btn-secondary me-3 btn-single-line" role="button">Αρχική</a>
					<button type="button" class="btn btn-danger me-3">Εκκαθάριση Φόρμας</button>
					<button type="submit" class="btn btn-success me-3">Καταχώριση Ομάδας</button>
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
