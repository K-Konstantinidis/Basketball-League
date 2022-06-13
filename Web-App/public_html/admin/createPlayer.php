<?php

session_start();
require_once '../../resources/config.php';

// Connect to the database
$conn = connectDB();

// Required for the navigation bar to load properly
$currPage = 'createPlayer';

// If the user is not logged in, he gets redirected at the login page.
if(!isset($_SESSION['logged_in']) || !$_SESSION['logged_in'] === true) {
	header('Location: ' . AREF_LOGIN . '?lr');
	die();
}

// Use these to display errors
$err_msg = $suc_msg = '';

// Variables
$playerNameGR = $playerSurnameGR = $playerNameEN = $playerSurnameEN = $playerPos = $playerTeam = $playerImg = "";

// Use them to indicate errors on the input field
$playerNameGR_err = $playerSurnameGR_err = $playerNameEN_err = $playerSurnameEN_err = $playerPos_err = $playerTeam_err = $playerImg_err = "";

if ($_SERVER["REQUEST_METHOD"] === "POST") {
	// Number of fields that are ready to be added
	$count = 0;

	// Binding vars to actual form inputs
	$stmt = $conn->prepare(
		"INSERT INTO player (name_en, surname_en, name_gr, surname_gr, team_id, player_position_code, img_path)
		VALUES (:name_en, :surname_en, :name_gr, :surname_gr, :team_id, :player_position_code, :img_path)"
	);
	$stmt->bindParam(':name_en', $playerNameEN);
	$stmt->bindParam(':surname_en', $playerSurnameEN);
	$stmt->bindParam(':name_gr', $playerNameGR);
	$stmt->bindParam(':surname_gr', $playerSurnameGR);
	$stmt->bindParam(':team_id', $playerTeam);
	$stmt->bindParam(':player_position_code', $playerPos);
	$stmt->bindParam(':img_path', $playerImg);

	// Check name EN
	if(!isset($_POST['name_en']) || !filter_data($_POST['name_en'])) {
		$playerNameEN_err = 'Παρακαλώ συμπληρώστε το όνομα του παίκτη στα Αγγλικά';
	}
	else {
		$playerNameEN = filter_data($_POST['name_en']);
		$count++;
	}

	// Check surname EN
	if(!isset($_POST['surname_en']) || !filter_data($_POST['surname_en'])) {
		$playerSurnameEN_err = 'Παρακαλώ συμπληρώστε το επίθετο του παίκτη στα Αγγλικά';
	}
	else {
		$playerSurnameEN = filter_data($_POST['surname_en']);
		$count++;
	}
	
	// Check name GR
	if(!isset($_POST['name_gr']) || !filter_data($_POST['name_gr'])) {
		$playerNameGR_err = 'Παρακαλώ συμπληρώστε το όνομα στα Ελληνικά';
	}
	else {
		$playerNameGR = filter_data($_POST['name_gr']);
		$count++;
	}

	// Check surname GR
	if(!isset($_POST['surname_gr']) || !filter_data($_POST['surname_gr'])) {
		$playerSurnameGR_err = 'Παρακαλώ συμπληρώστε το επίθετο του παίκτη στα Ελληνικά';
	}
	else {
		$playerSurnameGR = filter_data($_POST["surname_gr"]);
		$count++;
	}
	
	// Check selected player team
	if(!isset($_POST['player_team']) || !filter_data($_POST['player_team'])) {
		$playerTeam_err = 'Παρακαλώ επιλέξτε την ομάδα που θα συμμετάσχει o παίκτης';
	}
	else {
		$playerTeam = filter_data($_POST['player_team']);
		$count++;
	}

	// Check selected player position
	if(!isset($_POST['player_position_code']) || !filter_data($_POST['player_position_code'])) {
		$playerPos_err = 'Παρακαλώ επιλέξτε την θέση του παίκτη';
	}
	else {
		$playerPos = filter_data($_POST['player_position_code']);
		$count++;
	}

	// Check if there is an image uploaded.
	// Error 4 means the file variable is set, but no file was uploaded
	if(!isset($_FILES['img']) || $_FILES['img']['error'] == 4) {
		$playerImg_err = 'Παρακαλώ ανεβάστε την εικόνα του παίκτη';
	}
	else {
		$img_name		= $_FILES['img']['name'];
		$img_tmp_name	= $_FILES['img']['tmp_name'];
		$img_error		= $_FILES['img']['error'];
		$img_ext		= pathinfo($img_name, PATHINFO_EXTENSION);

		// No errors were encountered, procceed
		if($img_error == 0) {
			// Get the max number that exists
			$max_img_num_db = $conn
				->query("SELECT MAX(CAST(SUBSTRING(img_path,27,4) AS INT)) AS MAX_NUM FROM player")
				->fetch();

			// The following line of code may cause problems.
			// It fetches the database to find the largest image number.
			// The problems are:
			//		1. What if the user didn't populate the database with the example data?
			//		2. What if the player with the largest image number gets deleted, but their file doesn't?
			// That's why the "uniqid" is used.
			//
			//$img_final_name = canonicalizeStrNumber((int) $max_img_num_db['MAX_NUM'] + 1, 4);

			$img_final_name = uniqid("player-");
			$img_final_name .= '.' . strtolower($img_ext); //Add the image's extension

			// Prepare the path to be added in the database
			$playerImg = DIR_PLAYER_IMAGES . $img_final_name;
			$count++;
		}
		else {
			// An error occurred
			$playerImg_err = fileUploadErrorMessages($img_error);
		}
	}
	
	// If all fields were filled successfully, insert the new player
	if($count == 7) {
		move_uploaded_file($img_tmp_name,  $playerImg);
		$stmt->execute();
		$suc_msg = 'Ο παίκτης <strong>' . filter_data($_POST['name_gr']) . '</strong> δημιουργήθηκε επιτυχώς';
		unset($_POST);
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
				if($err_msg) {
					displayErrorBanner($err_msg);
				}

				if($suc_msg) {
					displaySuccessBanner($suc_msg);
				}
			?>

			<form method="POST" action="<?= htmlspecialchars($_SERVER['PHP_SELF']) ?>" enctype="multipart/form-data">
				<!-- Name (in Greek) -->
				<div class="form-floating mb-5">
					<input
						type="text"
						name="name_gr"
						class="form-control <?php echo ($playerNameGR_err) ? ' is-invalid' : '' ?>"
						id="playerNameGR"
						placeholder=""
						value="<?php echo (isset($_POST['name_gr'])) ? filter_data($_POST['name_gr']) : '' ?>"
					>
					<label for="playerNameGR">Όνομα Παίκτη (Ελληνικά)</label>
					<?php if($playerNameGR_err) formInvalidFeedback($playerNameGR_err) ?>
				</div>
				<!-- Surname (in Greek) -->
				<div class="form-floating mb-5">
					<input
						type="text"
						name="surname_gr"
						class="form-control <?php echo ($playerSurnameGR_err) ? ' is-invalid' : '' ?>"
						id="playerSurnameGR"
						placeholder=""
						value="<?php echo (isset($_POST['surname_gr'])) ? filter_data($_POST['surname_gr']) : '' ?>"
					>
					<label for="playerSurnameGR">Επώνυμο Παίκτη (Ελληνικά)</label>
					<?php if($playerSurnameGR_err) formInvalidFeedback($playerSurnameGR_err) ?>
				</div>

				<!-- Name (in English) -->
				<div class="form-floating mb-5">
					<input
						type="text"
						name="name_en"
						class="form-control <?php echo ($playerNameEN_err) ? ' is-invalid' : '' ?>"
						id="playerNameEN"
						placeholder=""
						value="<?php echo (isset($_POST['name_en'])) ? filter_data($_POST['name_en']) : '' ?>"
					>
					<label for="playerNameEN">Όνομα Παίκτη (Αγγλικά)</label>
					<?php if($playerNameEN_err) formInvalidFeedback($playerNameEN_err) ?>
				</div>
				<!-- Surname (in English) -->
				<div class="form-floating mb-5">
					<input
						type="text"
						name="surname_en"
						class="form-control <?php echo ($playerSurnameEN_err) ? ' is-invalid' : '' ?>"
						id="playerSurnameEN"
						placeholder=""
						value="<?php echo (isset($_POST['surname_en'])) ? filter_data($_POST['surname_en']) : '' ?>"
					>
					<label for="playerSurnameEN">Επώνυμο Παίκτη (Αγγλικά)</label>
					<?php if($playerSurnameEN_err) formInvalidFeedback($playerSurnameEN_err) ?>
				</div>

				<!-- Player Position -->
				<div class="mb-5">
					<label class="mb-1" for="playerPos">Θέση Παίκτη</label>
					<select
						name="player_position_code"
						class="form-select <?php echo ($playerPos_err) ? ' is-invalid' : '' ?>"
						id="playerPos"
					>
						<option selected disabled value="">Επιλέξτε...</option>
						<?php
							// Retrieve the player positions
							$data = $conn->query("SELECT * FROM player_position")->fetchAll();

							// Add them in the dropdown
							if($data != null) {
								foreach($data as $row) {
									// Remember the user selection, if he has made one
									$selected = '';
									if(isset($_POST['player_position_code']) && $row["code"] == $_POST['player_position_code']) {
										$selected = 'selected';
									}
									
									echo '<option value="' . $row["code"] . '" ' . $selected . '>' . $row["name"] . '</option>' . "\n";
								}
							}

							// Free the allocated memory
							unset($data);
						?>
					</select>
					<?php if($playerPos_err) formInvalidFeedback($playerPos_err) ?>
				</div>

				<!-- Player Team -->
				<div class="mb-5">
					<label class="mb-1" for="playerTeam">Ομάδα Παίκτη</label>
					<select
						name="player_team"
						class="form-select <?php echo ($playerTeam_err) ? ' is-invalid' : '' ?>"
						id="playerTeam"
					>
						<option selected="" disabled="" value="">Επιλέξτε...</option>
						<?php
							// Retrieve the teams
							$data = $conn->query("SELECT * FROM team ORDER BY name_gr")->fetchAll();
							
							// Add them in the dropdown
							if($data != null) {
								foreach($data as $row) {
									// Remember the user selection, if he has made one
									$selected = '';
									if(isset($_POST['player_team']) && $row['id'] == $_POST['player_team']) {
										$selected = 'selected';
									}
									echo '<option value=' . $row['id'] . ' ' . $selected . '>' . $row['name_gr'] . '</option>' . "\n";
								}
							}

							// Free the allocated memory
							unset($data);
						?>
					</select>
					<?php if($playerTeam_err) formInvalidFeedback($playerTeam_err) ?>
				</div>

				<!-- Player Image Selection -->
				<div class="mb-5">
					<label for="playerImg">Φωτογραφία Παίκτη</label>
					<input type="file"
							name="img"
							class="form-control mt-1 <?php echo ($playerImg_err) ? ' is-invalid' : '' ?>"
							id="playerImg"
							accept="image/*"
						>
					<?php if($playerImg_err) formInvalidFeedback($playerImg_err) ?>
				</div>
			
				<!-- Buttons -->
				<div class="d-flex flex-grow-1 justify-content-center align-items-center mb-5">
					<a href="<?= AREF_DIR_ADMIN ?>" class="btn btn-secondary me-3 btn-single-line" role="button">Αρχική</a>
					<a href="<?= htmlspecialchars($_SERVER['PHP_SELF']) ?>" class="btn btn-danger me-3" role="button">Εκκαθάριση Φόρμας</a>
					<button type="submit" class="btn btn-success me-3">Καταχώριση Παίκτη</button>
				</div>
			</form>

		</div>
		</main>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>
		
	</body>
	
</html>


