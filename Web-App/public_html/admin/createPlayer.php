<?php

/*TODO:: 

- Add comments
- Figure out what's wrong with images / ask
- Show Verbose (Line: 202-203-204) but add code only in db
- Add success / error message (Line: 51)
- Add button to only light up if all inputs are done
- Add potential language check for inputs (?)
- Clear form button functionality

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
$currPage = 'createPlayer';

// If the user is not logged in, he gets redirected at the loggin page.
if(!isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] === true) {
	header('Location: ../login/?lr');
	die();
}

$conn = connectDB();

// Binding vars to actual form inputs
$stmt = $conn->prepare("INSERT INTO player (name_en, surname_en, name_gr, surname_gr, team_id, player_position_code, img_path)
VALUES (:name_en, :surname_en, :name_gr, :surname_gr, :team_id, :player_position_code, :img_path)");
$stmt->bindParam(':name_en', $playerNameEN);
$stmt->bindParam(':surname_en', $playerSurnameEN);
$stmt->bindParam(':name_gr', $playerNameGR);
$stmt->bindParam(':surname_gr', $playerSurnameGR);
$stmt->bindParam(':team_id', $playerTeam);
$stmt->bindParam(':player_position_code', $playerPos);
$stmt->bindParam(':img_path', $playerImg);


// Use these to display errors
$err = $suc = false;
$err_msg = 'Ένα μήνυμα σφάλματος';
// $suc_msg = 'Ο παίκτης <strong>' . $_POST['playerNameGR'] . '</strong> δημιουργήθηκε επιτυχώς'; - Commented because of warning, fix

// Variables
$playerNameGR = $playerSurnameGR = $playerNameEN = $playerSurnameEN = $playerPos = $playerTeam = $playerImg = "";

// Use them to indicate errors on the input field
$playerNameGR_err = $playerSurnameGR_err = $playerNameEN_err = $playerSurnameEN_err = $playerPos_err = $playerTeam_err = $playerImg_err = "";


if ($_SERVER["REQUEST_METHOD"] == "POST") {

	$count = 0;

	// Name data filter
	if(empty($_POST["name_en"])) $playerNameEN_err = "Παρακαλώ συμπληρώστε το όνομα του παίκτη στα Αγγλικά"; 
		else { 
			$playerNameEN = filter_data($_POST["name_en"]);
			$count++;
	 	}
		 
	if(empty($_POST["surname_en"])) $playerSurnameEN_err = "Παρακαλώ συμπληρώστε το επίθετο του παίκτη στα Αγγλικά"; 
		else { 
			 $playerSurnameEN = filter_data($_POST["surname_en"]);
			 $count++;
		}
			
	if(empty($_POST["name_gr"])) $playerNameGR_err = "Παρακαλώ συμπληρώστε το όνομα στα Ελληνικά"; 
		else { 
			$playerNameGR = filter_data($_POST["name_gr"]);
			$count++;
		}

	if(empty($_POST["surname_gr"])) $playerSurnameGR_err = "Παρακαλώ συμπληρώστε το επίθετο του παίκτη στα Ελληνικά"; 
		else { 
			$playerSurnameGR = filter_data($_POST["surname_gr"]);
			$count++;
		}
		
	if(empty($_POST["player_team"])) $playerTeam_err = "Παρακαλώ επιλέξτε την ομάδα που θα συμμετάσχει o παίκτης";  // o 
		else { 
			$temp = $_POST["player_team"];

			$query = $conn->prepare('SELECT * FROM team WHERE name_gr LIKE ?');
			$query->bindValue(1, "$temp", PDO::PARAM_STR);
			$query->execute();

			try{
				$result = $query->fetch();
				$playerTeam = $result['id'];
			}catch(PDOException $e){
				$e->getMessage();
			}

			$count++;
		}

	if(empty($_POST["player_position_code"])) $playerPos_err = "Παρακαλώ επιλέξτε την θέση του παίκτη"; 
		else { 
			$playerPos = $_POST["player_position_code"];
			$count++;
		}

	// if(empty($_POST["img_path"])) $playerImg_err = "Παρακαλώ ανεβάστε την εικόνα του παίκτη"; 
	// 	else { 
	// 		$playerImg = $_POST["img_path"];
	// 		$count++;
	// 	}
	
	if($count==6) $stmt->execute();
}
?>

<!doctype html>
<html lang="el" class="h-100">
	<head>
		<!-- Website settings -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<title>ΕΣΑΚΕ App - Δημιουργία Παίκτη</title>

		<!-- Bootstrap and other required CSS -->
		<link rel="stylesheet" href="../css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./css/base.css"/>
		<link rel="stylesheet" href="./css/createPlayer.css"/>
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
			<h1 class="mt-5">Δημιουργία Παίκτη</h1>
			<p class="lead">Συμπληρώστε τα ακόλουθα πεδία για να δημιουργήσετε έναν νέο παίκτη.</p>
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

			<form method="POST" action="<?php htmlspecialchars($_SERVER['PHP_SELF']) ?>">
				<!-- Name (in Greek) -->
				<div class="form-floating mb-5">
					<input type="text" name="name_gr" class="form-control <?php echo ($playerNameGR_err) ? ' is-invalid' : '' ?>" id="playerNameGR" placeholder="">
					<label for="playerNameGR">Όνομα Παίκτη (Ελληνικά)</label>
				</div>
				<!-- Surname (in Greek) -->
				<div class="form-floating mb-5">
					<input type="text" name="surname_gr" class="form-control <?php echo ($playerSurnameGR_err) ? ' is-invalid' : '' ?>" id="playerSurnameGR" placeholder="">
					<label for="playerSurnameGR">Επώνυμο Παίκτη (Ελληνικά)</label>
				</div>

				<!-- Name (in English) -->
				<div class="form-floating mb-5">
					<input type="text" name="name_en" class="form-control <?php echo ($playerNameEN_err) ? ' is-invalid' : '' ?>" id="playerNameEN" placeholder="">
					<label for="playerNameEN">Όνομα Παίκτη (Αγγλικά)</label>
				</div>
				<!-- Surname (in English) -->
				<div class="form-floating mb-5">
					<input type="text" name="surname_en" class="form-control <?php echo ($playerSurnameEN_err) ? ' is-invalid' : '' ?>" id="playerSurnameEN" placeholder="">
					<label for="playerSurnameEN">Επώνυμο Παίκτη (Αγγλικά)</label>
				</div>

				<!-- Player Position -->
				<div class="mb-5">
					<label class="mb-1" for="playerPos">Θέση Παίκτη</label>
					<select name="player_position_code" class="form-select <?php echo ($playerPos_err) ? ' is-invalid' : '' ?>" id="playerPos">
						<option selected="" disabled="" value="">Επιλέξτε...</option>
						<?php
							$data = $conn->query("SELECT * FROM player_position")->fetchAll();
							if($data!=null){
								foreach($data as $row) {
									// echo '<option>' . $row["position_name"] .' </option>'; // Verbose
									echo '<option>' . $row["position_code"] . ' </option>'; // Code only
									// echo '<option>' . $row["position_code"] .' - '. $row["position_name"] . ' </option>'; // Both
								}
						}
						?>
					</select>
				</div>

				<!-- Player Team -->
				<div class="mb-5">
					<label class="mb-1" for="playerTeam">Ομάδα Παίκτη</label>
					<select name="player_team" class="form-select <?php echo ($playerTeam_err) ? ' is-invalid' : '' ?>" id="playerTeam">
						<option selected="" disabled="" value="">Επιλέξτε...</option>
						<?php
							$data = $conn->query("SELECT * FROM team")->fetchAll();
							if($data!=null){
								foreach($data as $row) {
									echo '<option>' . $row["name_gr"] .' </option>'; 
								}
						}
						?>
					</select>
				</div>

				<!-- Player Image Selection -->
				<div class="mb-5">
					<label for="playerImg">Φωτογραφία Παίκτη</label>
					<input type="file" name="img" class="form-control mt-1 <?php echo ($playerImg_err) ? ' is-invalid' : '' ?>" id="playerImg" accept="image/*">
				</div>
			
				<!-- Buttons -->
				<div class="d-flex flex-grow-1 justify-content-center align-items-center mb-5">
					<a href="./" class="btn btn-secondary me-3 btn-single-line" role="button">Αρχική</a>
					<button type="button" class="btn btn-danger me-3">Εκκαθάριση Φόρμας</button>
					<button type="submit" class="btn btn-success me-3">Καταχώριση Παίκτη</button>
				</div>
			</form>

		</div>
		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>
		
	</body>
	
</html>


