<?php

// Path constants

define('DIR_BASE',          dirname( dirname( __FILE__ ) ) . '/');
define('DIR_HTML',          DIR_BASE . 'public_html/');
define('DIR_ADMIN',         DIR_HTML . 'admin/');
define('DIR_IMG',           DIR_HTML . 'img/');
define('DIR_IMG_AREF',      getBasePath() . 'public_html/img/');
define('DIR_JS',            DIR_HTML . 'js/');
define('DIR_CSS',           DIR_HTML . 'css/');
define('BOOTSTRAP_CSS',     DIR_CSS . 'bootstrap.min.css');
define('DIR_LOGIN',         DIR_HTML . 'login/');
define('DIR_RESOURCES',     DIR_BASE . 'resources/');
define('ADMIN_NAVIGATION',  DIR_RESOURCES . 'admin_navbar.php');
define('MAIN_FOOTER',       DIR_RESOURCES . 'main_footer.php');

// Path constants for the admin's navigation bar
define('ADMIN_DIR_AREF',            getBasePath() . 'public_html/admin/');
define('ADMIN_CREATE_LEAGUE_AREF',  'createLeague.php');
define('ADMIN_CREATE_PLAYER_AREF',  'createPlayer.php');
define('ADMIN_CREATE_TEAM_AREF',    'createTeam.php');
define('ADMIN_DISPLAY_LEAGUE_AREF', 'displayLeague.php');
define('ADMIN_DRAW_LEAGUE_AREF',    'drawLeague.php');

define('LOGIN_AREF',    getBasePath() . 'public_html/login/');
define('LOGOUT_AREF',   getBasePath() . 'public_html/login/logout.php');



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

function getBasePath() {
    $php_self = $_SERVER['PHP_SELF'];

    if($php_self === '/') {
        return $php_self;
    }
    else {
        $path = '/';

        $i = 1;
        while($php_self[$i] !== '/') {
            $path .= $php_self[$i++];
        }

        return $path . '/';
    }
}

?>