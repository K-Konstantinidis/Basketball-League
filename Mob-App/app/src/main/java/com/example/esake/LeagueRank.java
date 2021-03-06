package com.example.esake;

import com.example.esake.DatabasePHP.MyIP;

public class LeagueRank {
	private String Logo_path, name;
	private int MatchesPlayed, Points, Wins, Losses;

	//For FragmentLeagueTable
	public LeagueRank(String Logo_path, String name,int MatchesPlayed,int Points,int Wins,int Losses){
		this.Logo_path=Logo_path;
		this.name=name;
		this.MatchesPlayed=MatchesPlayed;
		this.Points=Points;
		this.Wins=Wins;
		this.Losses=Losses;
	}

	public String getTeamlogo() {
		return "http://"+ MyIP.getIp()+this.Logo_path;
	}
	public String getName() {
		return name;
	}
	public String getMatchesPlayed() {
		return String.valueOf(MatchesPlayed);
	}
	public String getPoints() {
		return String.valueOf(Points);
	}
	public String getWins() {
		return String.valueOf(Wins);
	}
	public String getLosses() {
		return String.valueOf(Losses);
	}
}
