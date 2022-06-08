<?php
	//GET parameters: lang (language), cid (championship_id)
	
	$data = array();
	$lang = $_GET["lang"];
	$championship_id = $_GET["cid"];

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="basketball_db";
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
			
	$sql = "
SELECT logo, name, matches_played, (2*wins+(matches_played-wins)) as points, wins, (matches_played-wins) AS losses
FROM
    (SELECT logo_path AS logo, name_".$lang. " AS name, matches_played, CASE WHEN matches_won IS NULL THEN 0 ELSE matches_won END As wins
    FROM team
    LEFT JOIN 
        (SELECT team_id, count(*) AS matches_played
        FROM 
            (SELECT DISTINCT g.round_id, t.game_id, t.team_id
            FROM game g
            JOIN team_score_per_quarter t ON g.round_id = t.round_id AND g.id = t.game_id
            WHERE game_status = 0
            ORDER BY g.round_id, g.id, t.team_id) AS all_played_matches
        GROUP BY team_id
        ORDER BY team_id) AS match_count 
	ON team.id = match_count.team_id
    LEFT JOIN 
        (SELECT winner, count(*) AS matches_won
        FROM
            (SELECT h_team.round_id AS round, h_team.game_id AS game, CASE WHEN h_team.total_score > a_team.total_score THEN h_team.team_id ELSE a_team.team_id END As winner
            FROM(
                (SELECT t.championship_id, t.round_id, t.game_id, t.team_id, SUM(t.quarter_score) AS total_score, g.away_team_id
                FROM `team_score_per_quarter` t
                JOIN game g ON t.round_id = g.round_id AND t.game_id = g.id AND t.team_id!=g.away_team_id
				WHERE t.championship_id = ".$championship_id." 
                GROUP BY round_id,game_id,team_id) AS h_team
            JOIN
                (SELECT t.round_id, t.game_id, t.team_id, SUM(t.quarter_score) AS total_score
                FROM `team_score_per_quarter` t
                JOIN game g ON t.round_id = g.round_id AND t.game_id = g.id AND t.team_id!=g.home_team_id
                GROUP BY round_id,game_id,team_id) AS a_team
			ON h_team.round_id = a_team.round_id AND h_team.game_id = a_team.game_id)) AS a
    GROUP BY winner) AS win_count ON team.id = win_count.winner) AS b
ORDER BY points DESC";

	$result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) { 
		$nested_data = array();
		$nested_data['logo'] = $row['logo'];
		$nested_data['name'] = $row['name'];
		
		$nested_data['matches_played'] = $row['matches_played'];
		$nested_data['points'] = $row['points'];
		$nested_data['wins'] = $row['wins'];
		$nested_data['losses'] = $row['losses'];
		
		$data[$row['name']] = $nested_data;
	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>