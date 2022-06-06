<?php
	//GET parameters: lang (language), cid (championship_id), rid (round_id), gid (game_id)
	
	$data = array();
	
	$lang = $_GET["lang"];
	$championship_id = $_GET["cid"];
	$round_id = $_GET["rid"];
	$game_id = $_GET["gid"];
	
	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="basketball_db";
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
			
	$sql = "
	SELECT t.short_name_".$lang. " AS name, 
		t.logo_path AS logo, 
		SUM(two_points_in+two_points_out+three_points_in+three_points_out) AS total_shots,
		SUM(two_points_in+three_points_in) AS shots_made,
		ROUND(100*(SUM(two_points_in)/SUM(two_points_in+two_points_out)),2) AS perc_2_in,
		ROUND(100*(SUM(three_points_in)/SUM(three_points_in+three_points_out)),2) AS perc_3_in,
		ROUND(100*(SUM(freethrows_in)/SUM(freethrows_in+freethrows_out)),2) AS perc_freethrows_in,
		SUM(offensive_rebounds+defensive_rebounds) AS total_rebounds,
		SUM(offensive_rebounds) AS total_offensive_rebounds, 
		SUM(defensive_rebounds) AS total_defensive_rebounds,
		SUM(assists) AS total_assists, 
		SUM(blocks) AS total_blocks,
		SUM(steals) AS total_steals, 
		SUM(turnovers) AS total_turnovers,
		SUM(fouls) AS total_fouls
	FROM `player_stats`
	JOIN team t ON t.id = player_stats.team_id
	WHERE championship_id = " .$championship_id ." 
		AND round_id = " . $round_id ." 
		AND game_id = ". $game_id. "
	GROUP BY (t.id)
	";

	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) { 
		$team_stats = array();
		
		$team_stats['name'] = $row['name'];
		$team_stats['logo'] = $row['logo'];
		$team_stats['total_shots'] = $row['total_shots'];
		$team_stats['shots_made'] = $row['shots_made'];
		$team_stats['perc_2_in'] = $row['perc_2_in'];
		$team_stats['perc_3_in'] = $row['perc_3_in'];
		$team_stats['perc_freethrows_in'] = $row['perc_freethrows_in'];
		$team_stats['total_rebounds'] = $row['total_rebounds'];
		$team_stats['total_offensive_rebounds'] = $row['total_offensive_rebounds'];
		$team_stats['total_defensive_rebounds'] = $row['total_defensive_rebounds'];
		$team_stats['total_assists'] = $row['total_assists'];
		$team_stats['total_blocks'] = $row['total_blocks'];
		$team_stats['total_steals'] = $row['total_steals'];
		$team_stats['total_turnovers'] = $row['total_turnovers'];
		$team_stats['total_fouls'] = $row['total_fouls'];
		
		$data[$row['name']] = $team_stats;
	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>