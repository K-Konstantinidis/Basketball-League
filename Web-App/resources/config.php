<?php

define('ROOT_PATH', $_SERVER['DOCUMENT_ROOT']);

// Path constants
define('DIR_BASE',			dirname( dirname( __FILE__ ) ) . '/');
define('DIR_HTML',			DIR_BASE . 'public_html/');

define('DIR_ADMIN',			DIR_HTML . 'admin/');
define('DIR_IMG',			DIR_HTML . 'img/');
define('DIR_JS',			DIR_HTML . 'js/');
define('DIR_CSS',			DIR_HTML . 'css/');
define('BOOTSTRAP_CSS',		DIR_CSS . 'bootstrap.min.css');
define('DIR_LOGIN',			DIR_HTML . 'login/');
define('FILE_LOGOUT',		DIR_LOGIN . 'logout.php');
define('DIR_RESOURCES',		DIR_BASE . 'resources/');
define('ADMIN_NAVIGATION',	DIR_RESOURCES . 'admin_navbar.php');
define('MAIN_FOOTER',		DIR_RESOURCES . 'main_footer.php');

?>