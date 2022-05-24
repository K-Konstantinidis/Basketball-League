<?php

session_start();

// Display an alert message, if prompted from another page
$user_redirected = false;

// Display an alert message, if logged out successfuly
$user_logged_out = false;


if($_SERVER["REQUEST_METHOD"] == "GET") {
	// Check if the user was redirected
	if(isset($_GET["r"]) && $_GET["r"] === 'true') {
		$user_redirected = true;
	}

	// Check if the user logged out
	if(isset($_GET["r"]) && $_GET["r"] === 'loggout') {
		$user_logged_out = true;
	}


	// Used for debugging!
	if(isset($_GET["l"]) && $_GET["l"] === 'true') {
		$_SESSION['logged_in'] = true;
	}
	else {
		if(isset($_GET["l"]) && isset($_SESSION['logged_in'])) {
			unset($_SESSION['logged_in']);
		}
	}
}

// Loggin code
if($_SERVER["REQUEST_METHOD"] == "POST") {

}

?>

<!doctype html>
<html lang="el">
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="ESAKE Loggin Page">
	<title>ΕΣΑΚΕ - Σύνδεση</title>

	<!-- Bootstrap core CSS -->
	<link href="../css/bootstrap.min.css" rel="stylesheet">

	<style>
		.bd-placeholder-img {
			font-size: 1.125rem;
			text-anchor: middle;
			-webkit-user-select: none;
			-moz-user-select: none;
			user-select: none;
		}

		@media (min-width: 768px) {
			.bd-placeholder-img-lg {
				font-size: 3.5rem;
			}
		}
	</style>

	
	<!-- Custom styles for this template -->
	<link href="signin.css" rel="stylesheet">
	</head>
	<body class="text-center">
	
	<main class="form-signin">
		<form method="POST">
			<img class="mb-4" src="../img/brand/esake-logo.jpg" alt="" height="120">
			
			<?php echo ($user_redirected) ? '<div class="alert alert-danger fade show" role="alert">Πρέπει να συνδεθείτε για να χρησιμοποιήσετε την εφαρμογη.</div>' : '' ?>
			<?php echo ($user_logged_out) ? '<div class="alert alert-success fade show" role="alert">Αποσυνδεθήκατε επιτυχώς!</div>' : '' ?>
			
			<h1 class="h3 mb-3 fw-normal">Παρακαλώ συνδεθείτε</h1>

			<div class="form-floating">
				<input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
				<label for="floatingInput">Όνομα χρήστη</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword" placeholder="Password">
				<label for="floatingPassword">Συνθηματικό</label>
			</div>
			<div class="checkbox mb-3">
				<label>
					<input type="checkbox" value="remember-me"> Θυμήσου με
				</label>
			</div>
			<button class="w-100 btn btn-lg btn-primary" type="submit">Σύνδεση</button>
		</form>
		<p class="mt-5 mb-3 text-muted">Ανάπτυξη Εφαρμογών για Κινητές Συσκευές, 6ο Εξάμηνο</br>&copy; Ομάδα 1, 2021-2022</p>
	</main>

	</body>
</html>
