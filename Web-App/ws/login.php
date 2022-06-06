<?php

require_once '../resources/config.php';

// Login status codes:
// -1: Something went wrong (server side)
//  0: Invalid credentials
//  1: Successful authentication
$login_status = -1;

if($_SERVER['REQUEST_METHOD'] === 'POST') {
	if(isset($_POST['username']) && isset($_POST['password_sha256']) && !empty($_POST['username']) && !empty($_POST['password_sha256']))
	{
		$username = $_POST['username'];
		$password_sha256 = $_POST['password_sha256'];
		
		$link = connectDB();
		$sql = 'SELECT username, password FROM user WHERE username = :username';
		
		if($stmt = $link->prepare($sql)) {
			// Bind and set the prepared statement
			$stmt->bindParam(':username', $param_username, PDO::PARAM_STR);
			
			$param_username = $username;

			if($stmt->execute()) {
				if($stmt->rowCount() == 1) {
					// Get the results
					if($row = $stmt->fetch()) {
						$hashed_password = $row['password'];

						//Correct credentials
						if($password_sha256 === $hashed_password) {
							$login_status = 1;
						}
						else {
							// Password is invalid
							$login_status = 0;
						}
					}
					else {
						// Failed to fetch the results
						$login_status = -1;
					}
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
header("Content-Type: application/json");

// Create the JSON
$data = array();
$data['login_status'] = $login_status;

// Print it
echo json_encode($data);

?>