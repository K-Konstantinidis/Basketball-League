<?php
	$data = array();
	$round_id = $_GET["round_id"];

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="esake_management_app";
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
			
	$sql = "SELECT h_team_info.rid AS round, h_team_info.gid AS game, 
		h_team_info.logo AS home_logo, h_team_info.Team_Name as home_team, h_team_info.score AS home_team_score, 
		a_team_info.logo AS away_logo, a_team_info.Team_Name as away_team, a_team_info.score AS away_team_score
FROM 
    (SELECT game.round_id AS rid, game.game_id AS gid, h_team.logo_path AS logo, h_team.name_gr AS team_name, SUM(3*h_team_stats.three_points_in+2*h_team_stats.two_points_in+h_team_stats.freethrows_in) AS score
    FROM game
    JOIN team h_team ON game.home_team_id = h_team.id
    JOIN player_stats h_team_stats ON (game.championship_id = h_team_stats.championship_id AND game.round_id = h_team_stats.round_id AND game.game_id = h_team_stats.game_id AND game.home_team_id = h_team_stats.team_id)
    WHERE game.round_id = " . $round_id . "
    GROUP BY team_id
    ORDER BY game.round_id, game.game_id) AS h_team_info

JOIN 
    (SELECT game.round_id AS rid, game.game_id AS gid, a_team.logo_path AS logo, a_team.name_gr AS team_name, SUM(3*a_team_stats.three_points_in+2*a_team_stats.two_points_in+a_team_stats.freethrows_in) AS score
    FROM game
    JOIN team a_team ON game.away_team_id = a_team.id
    JOIN player_stats a_team_stats ON (game.championship_id = a_team_stats.championship_id AND game.round_id = a_team_stats.round_id AND game.game_id = a_team_stats.game_id AND game.away_team_id = a_team_stats.team_id)
    WHERE game.round_id = " . $round_id . "
    GROUP BY team_id
    ORDER BY game.round_id, game.game_id) AS a_team_info

ON (h_team_info.rid=a_team_info.rid AND h_team_info.gid=a_team_info.gid)";

	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) { 
		$nested_data = array();
		$nested_data['round'] = $row['round'];
		$nested_data['game'] = $row['game'];
		
		//$nested_data['home_logo'] = $row['home_logo'];
		$nested_data['home_team'] = $row['home_team'];
		$nested_data['home_team_score'] = $row['home_team_score'];
		
		//$nested_data['away_logo'] = $row['away_logo'];
		$nested_data['away_team'] = $row['away_team'];
		$nested_data['away_team_score'] = $row['away_team_score'];
		
		$data[$row['game']] = $nested_data;
	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>