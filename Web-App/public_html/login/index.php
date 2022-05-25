<?php

session_start();
require_once '../../resources/config.php';

// If the user already logged in, he gets redirected at the admin page.
if(isset($_SESSION["logged_in"]) && $_SESSION["logged_in"] === true) {
	header('Location: ../admin/');
	die();
}

// Any errors that may occur during login
$login_err = '';

// Login code
if($_SERVER["REQUEST_METHOD"] == "POST") {
	$username = $password = '';
	$username_err = $password_err = $login_err = '';

	// Get the username
	if(empty(trim($_POST['username']))) {
		$username_err = 'Παρακαλώ, εισάγετε όνομα χρήστη';
	}
	else {
		$username = trim($_POST['username']);
	}

	// Get the password
	if(empty(trim($_POST['password']))) {
		$password_err = 'Παρακαλώ, εισάγετε συνθηματικό';
	}
	else {
		$password = trim($_POST['password']);
	}

	if(empty($username_err) && empty($password_err)) {
		$link = connectDB();
		$sql = 'SELECT username, password FROM user WHERE username = ?';
		
		if($stmt = $link->prepare($sql)) {
			// Bind and set the prepared statement
			$stmt->bind_param('s', $param_username);
			$param_username = $username;

			if($stmt->execute()) {
				$stmt->store_result();

				if($stmt->num_rows == 1) {
					$stmt->bind_result($username, $hashed_password);

					// Get the results
					if($stmt->fetch()) {
						//Correct credentials
						if(strcmp(hash('sha256', $password), $hashed_password) === 0) {
							session_start();
							$_SESSION['logged_in'] = true;
							$_SESSION['user'] = $username;
							header('Location: ../../');
						}
						else {
							// Password is invalid, display a generic error message
							$login_err = 'Λάθος όνομα χρήστη ή κωδικός';
						}
					}
					else {
						// Failed to fetch the results
						$login_err = 'Κάτι πήγε λάθος. Προσπαθήστε ξανά αργότερα';
					}
				}
				else {
					// Username doesn't exist, display a generic error message
					$login_err = 'Λάθος όνομα χρήστη ή κωδικός';
				}
			}
			else {
				// Failed to execute the prepared statement
				$login_err = 'Κάτι πήγε λάθος. Προσπαθήστε ξανά αργότερα';
			}

			$stmt->close();
			$link->close();
		}
	}
}

?>

<!doctype html>
<html lang="el">
	<head>
		<!-- Website settings -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="ESAKE Loggin Page">
		<title>ΕΣΑΚΕ - Σύνδεση</title>

		<!-- Bootstrap and other required CSS -->
		<link rel="stylesheet" href="../css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./css/login.css"/>
	</head>

	<body class="text-center">
	
	<main class="form-signin">

		<form method="post" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']) ?>">
			<img class="mb-4" src=<?php echo DIR_IMG . 'brand/esake-logo.jpg'?> alt="esake-logo" height="120">
			
			<?php
				if($_SERVER["REQUEST_METHOD"] == "GET") {
					// Check if the user was redirected - "Login Required"
					if(isset($_GET["lr"])) {
						displayErrorBanner('Πρέπει να συνδεθείτε για να χρησιμοποιήσετε την εφαρμογη.', '');
					}
				
					// Check if the user logged out - "Logged out"
					if(isset($_GET["lo"])) {
						displaySuccessBanner('Αποσυνδεθήκατε επιτυχώς!', '');
					}
				}

				if($login_err){
					displayErrorBanner($login_err, '');
				}
			?>

			<h1 class="h3 mb-3 fw-normal">Παρακαλώ συνδεθείτε</h1>

			<div class="form-floating">
				<input type="text" class="form-control" id="username" name="username" placeholder="">
				<label for="username">Όνομα χρήστη</label>
			</div>

			<div class="form-floating">
				<input type="password" class="form-control" id="password" name="password" placeholder="">
				<label for="password">Συνθηματικό</label>
			</div>

			<button class="w-100 btn btn-lg btn-primary" type="submit">Σύνδεση</button>
		</form>
		<p class="mt-5 mb-3 text-muted">Ανάπτυξη Εφαρμογών για Κινητές Συσκευές, 6ο Εξάμηνο</br>&copy; Ομάδα 1, 2021-<?php echo date('Y')?></p>
	</main>

	</body>
</html>
