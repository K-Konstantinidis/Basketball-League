<?php
/* TODO::

- Make the short name input only 3-4 chars long

*/

session_start();
require_once '../../resources/config.php';

// Required for the navigation bar to load properly
$currPage = 'createTeam';

// If the user is not logged in, he gets redirected at the login page.
if(!isset($_SESSION['logged_in']) || !$_SESSION['logged_in'] === true) {
	header('Location: ' . AREF_LOGIN . '?lr');
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

// Use these to display messages
$err_msg = $suc_msg = '';
$default ='Επιλέξτε...';

$teamNameGR = $teamNameEN = $teamCodeGR = $teamCodeEN = $teamCity = $newCity_nameGR = $newCity_nameEN = $teamLogo = '';

$teamCity_err = $newCity_err = false;
$teamNameGR_err = $teamNameEN_err = $teamCodeGR_err = $teamCodeEN_err = $city_err = $teamLogo_err = '';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	// Number of fields that are ready to be added
	$count = 0;

	// Check the name GR
	if(!filter_data($_POST['name_gr'])) {
		$teamNameGR_err = "Παρακαλώ συμπληρώστε το πεδίο";
	}
	else {
		$teamNameGR = filter_data($_POST['name_gr']);
		$count++;
	}

	// Check the name EN
	if(!filter_data($_POST['name_en'])) {
		$teamNameEN_err = 'Παρακαλώ συμπληρώστε το πεδίο';
	}
	else {
		$teamNameEN = filter_data($_POST['name_en']);
		$count++;
	}
	
	// Check the short name GR
	if(!filter_data($_POST['short_name_gr'])) {
		$teamCodeGR_err = "Παρακαλώ συμπληρώστε το πεδίο";
	}
	else {
		// Checks the length of the given code
		$len = strlen( filter_data($_POST['short_name_gr']) );

		if($len < 3 || $len > 4) {
			$teamCodeGR_err = "Ο κωδικός της ομάδας πρέπει να είναι 3 εως 4 χαρακτήρες";
		}
		else {
			$teamCodeGR = filter_data($_POST['short_name_gr']);
			$count++;
		}
	}

	// Check the short name EN
	if(!filter_data($_POST['short_name_en'])) {
		$teamCodeEN_err = "Παρακαλώ συμπληρώστε το πεδίο";
	}
	else {
		// Checks the length of the given code
		$len = strlen( filter_data($_POST['short_name_en']) );

		if($len < 3 || $len > 4) {
			$teamCodeEN_err = "Ο κωδικός της ομάδας πρέπει να είναι 3 εως 4 χαρακτήρες";
		}
		else {
			$teamCodeEN = filter_data($_POST['short_name_en']);
			$count++;
		}
	}

	// Flag, used for the creation of the new city.
	// If we don't have it, every time the user tries to create a new team but
	//  has incorrectly filled out the fields, and has selected to create a new city,
	//  the new city will be added to the DB, creating duplicates.
	// Don't ask me how I know...
	$created_new_city = false;

	// Check if the user selected an existing city
	if(isset($_POST['team_city']) && filter_data($_POST['team_city'])) { 
		$teamCity = filter_data($_POST['team_city']);
		$count++;
	}
	// If he hasn't, check if he created a new city
	else if(isset($_POST['newCity_gr']) && isset($_POST['newCity_en']) &&
			filter_data($_POST['newCity_gr']) && filter_data($_POST['newCity_en'])
	) {
		$created_new_city = true;
		$count++;
	}
	// If we reach here, then he didn't specify the city
	else {
		$city_err = 'Επιλέξτε μια υπάρχουσα πόλη, είτε δημιουργήστε μια νέα πόλη';
		$teamCity_err = $newCity_err = true;
	}
	
	// Check the logo
	// Error 4 means the file variable is set, but no file was uploaded
	if(!isset($_FILES['logo']) || $_FILES['logo']['error'] == 4) {
		$teamLogo_err = 'Παρακαλώ ανεβάστε την εικόνα της ομάδας';
	}
	else {
		$img_name		= $_FILES['logo']['name'];
		$img_tmp_name	= $_FILES['logo']['tmp_name'];
		$img_error		= $_FILES['logo']['error'];
		$img_ext		= pathinfo($img_name, PATHINFO_EXTENSION);

		// No errors were encountered, procceed
		if($img_error == 0) {
			// The following line of code may cause problems.
			// It fetches the database to find the largest image number.
			// The problems are:
			//		1. What if the user didn't populate the database with the example data?
			//		2. What if the team with the largest image number gets deleted, but their logo doesn't?
			// That's why the "uniqid" is used.
			//
			// Get the max number that exists
			//$max_img_num_db = $conn
			//	->query("SELECT MAX(CAST(SUBSTRING(logo_path,27,4) AS INT)) AS MAX_NUM FROM team")
			//	->fetch();
			//
			//$img_final_name = canonicalizeStrNumber((int) $max_img_num_db['MAX_NUM'] + 1, 4);

			$img_final_name = uniqid("team-");
			$img_final_name .= '.' . strtolower($img_ext); //Add the image's extension

			// Prepare the path to be added in the database
			$teamLogo = DIR_TEAM_IMAGES . $img_final_name;
			$count++;
		}
		else {
			// An error occurred
			$teamLogo_err = fileUploadErrorMessages($img_error);
		}
	}

	// All fields were completed successfully
	if($count == 6) {
		// If the user has created a new city, add it now...
		if($created_new_city) {
			// Prepare the new city to be added
			$newCity_nameGR = filter_data($_POST['newCity_gr']);
			$newCity_nameEN = filter_data($_POST['newCity_en']);

			// Add the new city
			$city_stmt = $conn->prepare("INSERT INTO city (name_en, name_gr) VALUES (:name_en, :name_gr)");
			$city_stmt->bindParam(':name_gr', $newCity_nameGR);
			$city_stmt->bindParam(':name_en', $newCity_nameEN);
			$city_stmt->execute();

			// Select the ID of the newly created city
			$city_stmt = $conn->prepare('SELECT * FROM city WHERE name_gr LIKE ?');
			$city_stmt->bindValue(1, "$newCity_nameGR", PDO::PARAM_STR);
			$city_stmt->execute();
			try {
				$result = $city_stmt->fetch();
				$teamCity = $result['id'];
			}
			catch(PDOException $e) {
				$e->getMessage();
			}
		}

		// Create the team
		move_uploaded_file($img_tmp_name,  $teamLogo);
		$stmt->execute();
		$suc_msg = 'Η ομάδα <strong>' . filter_data($_POST['name_gr']) . '</strong> δημιουργήθηκε επιτυχώς';
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
		<title>ΕΣΑΚΕ App - Δημιουργία Ομάδας</title>

		<!-- Bootstrap core CSS -->
		<link rel="stylesheet" href="../css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./css/base.css"/>
		<link rel="stylesheet" href="./css/createTeam.css"/>
		<script src="../js/bootstrap.bundle.min.js"></script>
		<script src="./js/createTeam.js" defer></script>
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
				if($err_msg) {
					displayErrorBanner($err_msg);
				}

				if($suc_msg) {
					displaySuccessBanner($suc_msg);
				}
			?>

			<form method="POST" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']) ?>">
				<!-- Name (Greek) -->
				<div class="form-floating mb-5">
					<input
						type="text"
						name="name_gr"
						class="form-control <?php echo ($teamNameGR_err) ? ' is-invalid' : '' ?>"
						id="teamNameGR"
						placeholder=""
						value="<?php echo (isset($_POST['name_gr'])) ? htmlspecialchars($_POST['name_gr']) : '' ?>"
					>
					<label for="teamNameGR">Όνομα Ομάδας (Ελληνικά)</label>
					<?php if($teamNameGR_err) formInvalidFeedback($teamNameGR_err) ?>
				</div>
				<!-- Name (English) -->
				<div class="form-floating mb-5">
					<input
						type="text"
						name="name_en"
						class="form-control <?php echo ($teamNameEN_err) ? ' is-invalid' : '' ?>"
						id="teamNameEN"
						placeholder=""
						value="<?php echo (isset($_POST['name_en'])) ? htmlspecialchars($_POST['name_en']) : '' ?>"
					>
					<label for="teamNameEN">Όνομα Ομάδας (Αγγλικά)</label>
					<?php if($teamNameEN_err) formInvalidFeedback($teamNameEN_err) ?>
				</div>
				<!-- Team Code (English) -->
				<div class="form-floating mb-5">
					<input
						type="text"
						name="short_name_en"
						class="form-control <?php echo ($teamCodeEN_err) ? ' is-invalid' : '' ?>"
						id="teamCodeEN"
						placeholder=""
						value="<?php echo (isset($_POST['short_name_en'])) ? htmlspecialchars($_POST['short_name_en']) : '' ?>"
					>
					<label for="teamCodeEN">Κωδικός ομάδας (Αγγλικά)</label>
					<?php if($teamCodeEN_err) formInvalidFeedback($teamCodeEN_err) ?>
				</div>
				<!-- Team Code (Greek) -->
				<div class="form-floating mb-5">
					<input
						type="text"
						name="short_name_gr"
						class="form-control <?php echo ($teamCodeGR_err) ? ' is-invalid' : '' ?>"
						id="teamCodeGR"
						placeholder=""
						value="<?php echo (isset($_POST['short_name_gr'])) ? htmlspecialchars($_POST['short_name_gr']) : '' ?>"
					>
					<label for="teamCodeGR">Κωδικός ομάδας (Ελληνικά)</label>
					<?php if($teamCodeGR_err) formInvalidFeedback($teamCodeGR_err) ?>
				</div>

				<!-- City -->
				<div class="row mb-5">
					<p class="lead">Επιλέξτε την πόλη στην οποία βρίσκεται η ομάδα ή αν δεν υπάρχει, δημιουργήστε την.</p>
					
					<?php echo '<p class="lead text-danger">' . $city_err . '</p>' ?>
					
					<!-- Select from existing -->
					<div class="col-md-6">
						<label class="mb-1" for="teamCity_dropdown">Υπάρχουσες πόλεις</label>
						<select
							name="team_city"
							class="form-select <?php echo ($teamCity_err) ? ' is-invalid' : '' ?>"
							id="teamCity_dropdown"
							onchange="grayOut()"
						>
							<option selected disabled value="">Επιλέξτε...</option>
							<?php
								// Retrieve the existing cities
								$data = $conn->query("SELECT * FROM city ORDER BY name_gr")->fetchAll();

								// Add them in the dropdown
								if($data != null) {
									foreach($data as $row) {
										// Remember the user selection, if he has made one
										$selected = '';
										if(isset($_POST['team_city']) && $row['id'] == $_POST['team_city']) {
											$selected = 'selected';
										}

										echo '<option value=' . $row['id'] . ' ' . $selected . '>' . $row['name_gr'] . '</option>' . "\n";
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
							<input
								type="text"
								name="newCity_gr"
								class="newCity form-control <?php echo ($newCity_err) ? ' is-invalid' : '' ?>"
								id="newCity_nameGR"
								placeholder=""
								value="<?php echo (isset($_POST['newCity_gr'])) ? htmlspecialchars($_POST['newCity_gr']) : '' ?>"
							>
							<label for="newCity_nameGR">Όνομα Νέας Πόλης (Ελληνικά)</label>
						</div>
						<!-- New city name (English) -->
						<div class="form-floating mb-3">
							<input
								type="text"
								name="newCity_en"
								class="newCity form-control <?php echo ($newCity_err) ? ' is-invalid' : '' ?>"
								id="newCity_nameEN"
								placeholder=""
								value="<?php echo (isset($_POST['newCity_en'])) ? htmlspecialchars($_POST['newCity_en']) : '' ?>"
							>
							<label for="newCity_nameEN">Όνομα Νέας Πόλης (Αγγλικά)</label>
						</div>
					</div>
				</div>

				<!-- Logo -->
				<label for="teamLogo" class="mb-1">Σήμα Ομάδας</label>
				<input
					type="file"
					name="logo"
					class="form-control mb-5 <?php echo ($teamLogo_err) ? ' is-invalid' : '' ?>"
					id="teamLogo"
					accept="image/*"
				>
				
				<div class="d-flex flex-grow-1 justify-content-center align-items-center mb-3">
					<a href="./" class="btn btn-secondary me-3 btn-single-line" role="button">Αρχική</a>
					<a href="<?php echo htmlspecialchars($_SERVER['PHP_SELF']) ?>" class="btn btn-danger me-3" role="button">Εκκαθάριση Φόρμας</a>
					<button type="submit" class="btn btn-success me-3">Καταχώριση Ομάδας</button>
				</div>

			</form>

			<br>
			<br>
		</div>
		</main>
		<script>

			

		</script>

		<!-- Footer -->
		<?php require_once MAIN_FOOTER ?>

	</body>
	
</html>
