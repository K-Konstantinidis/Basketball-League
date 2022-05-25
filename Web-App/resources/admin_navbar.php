<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="<?php echo ADMIN_DIR_AREF ?>">ΕΣΑΚΕ Managment App</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav me-auto mb-2 mb-md-0">
				<li class="nav-item">
					<a class="nav-link <?php if($currPage === 'start') { echo ' active'; }?>" aria-current="page" href="<?php echo ADMIN_DIR_AREF ?>">Αρχική</a>
				</li>
				<li class="nav-item">
					<a class="nav-link <?php if($currPage === 'createLeague') { echo ' active'; }?>" href="<?php echo ADMIN_CREATE_LEAGUE_AREF ?>">Δημιουργία Πρωταθλήματος</a>
				</li>
				<li class="nav-item">
					<a class="nav-link <?php if($currPage === 'createTeam') { echo ' active'; }?>" href="<?php echo ADMIN_CREATE_TEAM_AREF ?>">Δημιουργία Ομάδας</a>
				</li>
				<li class="nav-item">
					<a class="nav-link <?php if($currPage === 'createPlayer') { echo ' active'; }?>" href="<?php echo ADMIN_CREATE_PLAYER_AREF ?>">Δημιουργία Παίκτη</a>
				</li>
				<li class="nav-item">
					<a class="nav-link <?php if($currPage === 'drawLeague') { echo ' active'; }?>" href="<?php echo ADMIN_DRAW_LEAGUE_AREF ?>">Κλήρωση Πρωταθλήματος</a>
				</li>
			</ul>
			<form class="d-flex" action="../logout.php">
				<a class="btn btn-outline-danger" href="<?php echo LOGOUT_AREF . '?lo' ?>">Αποσύνδεση</a>
			</form>
		</div>
	</div>
</nav>