package com.example.esake;

import java.util.ArrayList;

public class Championship {
	private String name;
	private ArrayList<Day> game_days;

	private String id;

	private String home_team;
	private String home_teamscore;

	private String away_team;
	private String away_teamscore;

	private ArrayList<String> Scores = new ArrayList<>();
	private ArrayList<String> gameweeks = new ArrayList<>();

	public Championship(String id, String ht, String hts, String at, String ats) {
		this.id = id;
		home_team = ht;
		home_teamscore = hts;
		away_team = at;
		away_teamscore = ats;
	}

	public String getTeamScore(boolean isHomeTeam){
		if (isHomeTeam)
			return home_teamscore;
		else
			return away_teamscore;
	}


}
