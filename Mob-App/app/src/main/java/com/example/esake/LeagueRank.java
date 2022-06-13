package com.example.esake;

public class LeagueRank {
	private String Logo_path;
	private String name;
	private int MatchesPlayed;
	private int Points;
	private int Wins;
	private int Losses;

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
		return "http://"+myIP.getIp()+this.Logo_path;
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
