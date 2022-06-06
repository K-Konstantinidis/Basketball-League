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
	SELECT t.short_name_".$lang. " AS name, SUM(q_t_score.quarter_score) AS total_score, GROUP_CONCAT(q_t_score.quarter_score) AS scores
	FROM `team_score_per_quarter` q_t_score
	JOIN team t ON t.id = q_t_score.team_id
	WHERE championship_id = " .$championship_id ." 
		AND round_id = " . $round_id ." 
		AND game_id = ". $game_id. "
	GROUP BY team_id
	ORDER BY round_id, game_id, team_id, quarter";

	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) { 
		$scores_data = array();
		
		$scores_data['name'] = $row['name'];
		$scores_data['total_score'] = $row['total_score'];
		$scores_data['scores'] = $row['scores'];
		
		$data[$row['name']] = $scores_data;
	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>