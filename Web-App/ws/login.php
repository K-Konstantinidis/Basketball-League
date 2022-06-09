<?php

require_once '../resources/config.php';

// Login status codes:
// -1: Something went wrong (server side)
//  0: Invalid credentials
//  1: Successful authentication
$login_status = -1;

if($_SERVER['REQUEST_METHOD'] === 'POST') {
	if( isset($_POST['username']) && !empty($_POST['username']) &&
		isset($_POST['password_sha256']) &&  !empty($_POST['password_sha256'])
	) {
		$username = $_POST['username'];
		$password_sha256 = $_POST['password_sha256'];
		
		$link = connectDB();
		$sql = 'SELECT id FROM user WHERE username = :username AND password = :hashed_password';
		
		if($stmt = $link->prepare($sql)) {
			// Bind and set the prepared statement
			$stmt->bindParam(':username', 		 $username,			PDO::PARAM_STR);
			$stmt->bindParam(':hashed_password', $password_sha256,	PDO::PARAM_STR);

			if($stmt->execute()) {
				if($stmt->rowCount() == 1) {
					// Correct credentials
					$login_status = 1;
				}
				else {
					// Username doesn't exist
					$login_status = 0;
				}
			}
			else {
				// Failed to execute the prepared statement
				$login_status = -1;
			}
			unset($stmt);
		}
		unset($pdo);
	}
}

// Set the body of the page to be JSON
header('Content-Type: application/json');

// Create the JSON
$data = array();
$data['login_status'] = $login_status;

// Print it
echo json_encode($data);

?>