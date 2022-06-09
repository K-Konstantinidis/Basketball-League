<?php

// Path constants
define('DIR_BASE',          dirname( dirname( __FILE__ ) ) . '/');
define('DIR_HTML',          DIR_BASE . 'public_html/');
define('DIR_ADMIN',         DIR_HTML . 'admin/');
define('DIR_IMG',           DIR_HTML . 'img/');

define('DIR_JS',            DIR_HTML . 'js/');
define('DIR_CSS',           DIR_HTML . 'css/');
define('BOOTSTRAP_CSS',     DIR_CSS . 'bootstrap.min.css');
define('DIR_LOGIN',         DIR_HTML . 'login/');
define('DIR_RESOURCES',     DIR_BASE . 'resources/');
define('ADMIN_NAVIGATION',  DIR_RESOURCES . 'admin_navbar.php');
define('MAIN_FOOTER',       DIR_RESOURCES . 'main_footer.php');

// Paths for the anchor's element ref attribute (aka HTML's <a>)
define('AREF_BASE',         findBasePathToDir($_SERVER['PHP_SELF'], 'public_html'));
define('AREF_HTML',         AREF_BASE . 'public_html/');
define('AREF_LOGIN',        AREF_HTML . 'login/');
define('AREF_LOGOUT',       AREF_LOGIN . 'logout.php');
define('AREF_DIR_IMG',      AREF_HTML . 'img/');

define('AREF_DIR_ADMIN',            	AREF_HTML . 'admin/');
define('AREF_ADMIN_AVAILABLE_LEAGUES',  AREF_DIR_ADMIN . 'availableLeagues.php');
define('AREF_ADMIN_CREATE_LEAGUE',  	AREF_DIR_ADMIN . 'createLeague.php');
define('AREF_ADMIN_CREATE_PLAYER',  	AREF_DIR_ADMIN . 'createPlayer.php');
define('AREF_ADMIN_CREATE_TEAM',    	AREF_DIR_ADMIN . 'createTeam.php');
define('AREF_ADMIN_DISPLAY_LEAGUE', 	AREF_DIR_ADMIN . 'displayLeague.php');
define('AREF_ADMIN_DRAW_LEAGUE',    	AREF_DIR_ADMIN . 'drawLeague.php');
define('AREF_ADMIN_LOADING_LEAGUE', 	AREF_DIR_ADMIN . 'loadingLeague.php');


/**
 * The database connector.
 * 
 * @return db_lnk A PDO connection to the application's database
 */
function connectDB() {
	// Probably the most insecure way to connect to the database.
	// I know the proper way is to have another config file outside "/var/www/"
	// (or "/htdocs/" if you are on windows with XAMPP) with permissions set at
	// 600 (aka rw-------), but this is a university assignment that requires to be
	// 100% portable. Sorry. - Iosif
	// 👆 True - Vaggos
	try{
		$db_lnk = new PDO('mysql:dbname=basketball_db;host=localhost', 'root', '');
		// Set the PDO error mode to exception
		$db_lnk->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	}
	catch(PDOException $e){
		die("ERROR: Could not connect. " . $e->getMessage());
	}

	return $db_lnk;
}

/**
 * Displays a red error banner, with a defined message.
 * 
 * @param text The text at the body of the banner
 * @param title The title of the banner, by default "Σφάλμα!"
 * @uses The bootstrap CSS
 */
function displayErrorBanner(string $text, string $title = 'Σφάλμα!') {
	echo '<div class="alert alert-danger fade show" role="alert">';
	echo '<strong>'. $title . '</strong><br>';
	echo $text;
	echo '</div><br>';
}

/**
 * Displays an orange warning banner, with a defined message.
 * 
 * @param text The text at the body of the banner
 * @param title The title of the banner, by default "Προσοχή!"
 * @uses The bootstrap CSS
 */
function displayWarningBanner(string $text, string $title = 'Προσοχή!') {
	echo '<div class="alert alert-warning fade show" role="alert">';
	echo '<strong>'. $title . '</strong><br>';
	echo $text;
	echo '</div><br>';
}

/**
 * Displays a green success banner, with a defined message.
 * 
 * @param text The text at the body of the banner
 * @param title The title of the banner, by default "Επιτυχία!"
 * @uses The bootstrap CSS
 */
function displaySuccessBanner(string $text, string $title = 'Επιτυχία!') {
	echo '<div class="alert alert-success fade show" role="alert">';
	echo '<strong>'. $title . '</strong><br>';
	echo $text;
	echo '</div><br>';
}

/**
 * Displays an error message for the form's input field.
 * Make sure you call it under the field!
 * 
 * @param msg The error message
 * @uses The bootstrap CSS
 */
function formInvalidFeedback(string $msg) {
	echo '<div class="invalid-feedback">';
	echo $msg;
	echo '</div>' . "\n";
}

/**
 * Generic function which removes spaces, slashes and converts
 * the special characters of HTML, to prevent XSS attacks.
 * 
 * @param data The data of the form
 * @return data The filtered data
 */
function filter_data($data) {
	$data = trim($data);
	$data = stripslashes($data);
	$data = htmlspecialchars($data);
	return $data;
}

/**
 * Returns the relative path from the engine's "www" folder.
 * @param php_self The returned value of $_SERVER['PHP_SELF']
 * @param to The folder at which you want to ge the path
 */
function findBasePathToDir($php_self, $to) {
	$rpos = strrpos($php_self, $to);
	if($rpos === false) {
		return false;
	}
	else {
		return substr($php_self, 0, $rpos);
	}
}

?>