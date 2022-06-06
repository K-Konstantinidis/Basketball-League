<?php
	//GET parameters: rid (round_id)
	
	data = array();
	$round_id = $_GET["rid"];

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="basketball_db";
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
			
	$sql = "SELECT h_team_info.rid AS round, h_team_info.gid AS game, 
		h_team_info.logo AS home_logo, h_team_info.name as home_team, h_team_info.total_score AS home_team_score, 
		a_team_info.logo AS away_logo, a_team_info.name as away_team, a_team_info.total_score AS away_team_score, h_team_info.status AS game_status
FROM 
    (SELECT q_score.round_id AS rid, q_score.game_id AS gid, h_team.name_gr AS name, h_team.logo_path AS logo, SUM(quarter_score) AS total_score, g.status AS status
	FROM `team_score_per_quarter` AS q_score
	JOIN team h_team ON q_score.team_id = h_team.id
	JOIN game g ON (g.championship_id = q_score.championship_id AND g.round_id = q_score.round_id AND g.id = q_score.game_id AND g.home_team_id = q_score.team_id)
	WHERE q_score.round_id = " . $round_id . "
	GROUP BY q_score.round_id, q_score.game_id, q_score.team_id
	ORDER BY q_score.round_id, q_score.game_id, q_score.team_id, q_score.quarter) AS h_team_info

JOIN 
    (SELECT q_score.round_id AS rid, q_score.game_id AS gid, a_team.name_gr AS name, a_team.logo_path AS logo, SUM(quarter_score) AS total_score
	FROM `team_score_per_quarter` AS q_score
	JOIN team a_team ON q_score.team_id = a_team.id
	JOIN game g ON (g.championship_id = q_score.championship_id AND g.round_id = q_score.round_id AND g.id = q_score.game_id AND g.away_team_id = q_score.team_id)
	WHERE q_score.round_id = " . $round_id . "
	GROUP BY q_score.round_id, q_score.game_id, q_score.team_id
	ORDER BY q_score.round_id, q_score.game_id, q_score.team_id, q_score.quarter) AS a_team_info

ON (h_team_info.rid=a_team_info.rid AND h_team_info.gid=a_team_info.gid)";

	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) { 
		$nested_data = array();
		$nested_data['round'] = $row['round'];
		$nested_data['game'] = $row['game'];
		
		$nested_data['home_logo'] = $row['home_logo'];
		$nested_data['home_team'] = $row['home_team'];
		$nested_data['home_team_score'] = $row['home_team_score'];
		
		$nested_data['away_logo'] = $row['away_logo'];
		$nested_data['away_team'] = $row['away_team'];
		$nested_data['away_team_score'] = $row['away_team_score'];
		
		$nested_data['game_status'] = $row['game_status'];
		
		$data[$row['game']] = $nested_data;
	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>