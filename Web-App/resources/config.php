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

define('AREF_DIR_ADMIN',            AREF_HTML . 'admin/');
define('AREF_ADMIN_CREATE_LEAGUE',  AREF_DIR_ADMIN . 'createLeague.php');
define('AREF_ADMIN_CREATE_PLAYER',  AREF_DIR_ADMIN . 'createPlayer.php');
define('AREF_ADMIN_CREATE_TEAM',    AREF_DIR_ADMIN . 'createTeam.php');
define('AREF_ADMIN_DISPLAY_LEAGUE', AREF_DIR_ADMIN . 'displayLeague.php');
define('AREF_ADMIN_DRAW_LEAGUE',    AREF_DIR_ADMIN . 'drawLeague.php');



function connectDB() {
	// Probably the most insecure way to connect to the database.
	// I know the proper way is to have another config file outside "/var/www/"
	// (or "/htdocs/" if you are on windows with XAMPP) with permissions set at
	// 600 (aka rw-------), but this is a university assignment that requires to be
	// 100% portable. Sorry.
	$db_tmp = new mysqli('localhost', 'root', '', 'esake_management_app');

	if($db_tmp->connect_error) {
		echo 'Failed to connect to the database!';
		die();
	}

	return $db_tmp;
}

function displayErrorBanner(string $text, string $title = 'Σφάλμα!') {
	echo '<div class="alert alert-danger fade show" role="alert">';
	if(!empty($title)) {
		echo '<strong>'. $title . '</strong><br>';
	}	
	echo $text;
	echo '</div><br>';
}

function displayWarrningBanner($text, $title = 'Προσοχή!') {
	echo '<div class="alert alert-warrning fade show" role="alert">';
	if(!empty($title)) {
		echo '<strong>'. $title . '</strong><br>';
	}	
	echo $text;
	echo '</div><br>';
}

function displaySuccessBanner($text, $title = 'Επιτυχία!') {
	echo '<div class="alert alert-success fade show" role="alert">';
	if(!empty($title)) {
		echo '<strong>'. $title . '</strong><br>';
	}	
	echo $text;
	echo '</div><br>';
}

/**
 * 
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