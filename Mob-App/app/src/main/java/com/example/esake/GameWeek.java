package com.example.esake;

public class GameWeek {
	private String gameweek;
//	private int round;
	private int gameId;
	private String homeLogo, awayLogo;
	private int homeScore, awayScore;
	private int gameStatus;

	public GameWeek(String gameweek) {this.gameweek = gameweek;}

	public GameWeek(String homeLogo, String awayLogo, int gameId, int homeScore, int awayScore, int gameStatus) {
		this.homeLogo = homeLogo;
		this.awayLogo = awayLogo;
		this.gameId = gameId;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.gameStatus = gameStatus;
	}

	public String getGameweek() {return gameweek;}

//	public int getRound() {return round;}
	public String getGameId() {return String.valueOf(gameId);}
	public String getHomeLogo() {return "http://"+ MyIP.getIp()+this.homeLogo;}
	public String getAwayLogo() {return "http://"+ MyIP.getIp()+this.awayLogo;}
	public String getHomeScore() {return String.valueOf(homeScore);}
	public String getAwayScore() {return String.valueOf(awayScore);}
	public int getGameStatus() {return gameStatus;}
}