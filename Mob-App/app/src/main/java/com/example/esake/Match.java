package com.example.esake;

import java.util.ArrayList;

public class Match {
	private ArrayList<Game> match = new ArrayList<>();

	public Match(String ip){
		String url= "http://"+ip+"/ws/getGameweekMatches.php?round_id=2";
		try {
			OkHttpHandler okHttpHandler = new OkHttpHandler();
			match = okHttpHandler.getDataforMatches(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTeamList(int matchIndex, boolean isHomeTeam){
		return this.match.get(matchIndex).getTeamName(isHomeTeam);
	}

	public String getScoreList(int matchIndex, boolean isHomeTeam){
		return String.valueOf(this.match.get(matchIndex).getScore(isHomeTeam));
	}


}
