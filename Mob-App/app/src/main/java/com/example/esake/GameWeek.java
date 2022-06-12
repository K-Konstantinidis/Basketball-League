package com.example.esake;

public class GameWeek {

	private String gameweek;
//	private int round;
	private int gameId;
//	private String homeLogo, awayLogo;
	private int homeScore, awayScore;
	private int gameStatus;

	public GameWeek(String gameweek) {
		this.gameweek = gameweek;
	}

	public GameWeek(int gameId, int homeScore, int awayScore, int gameStatus) {
		this.gameId = gameId;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.gameStatus = gameStatus;
	}

	public String getGameweek() {
		return gameweek;
	}

//	public int getRound() {return round;}
	public String getGameId() {return String.valueOf(gameId);}
//	public String getHomeLogo() {return homeLogo;}
//	public String getAwayLogo() {return awayLogo;}
	public String getHomeScore() {return String.valueOf(homeScore);}
	public String getAwayScore() {return String.valueOf(awayScore);}
	public int getGameStatus() {return gameStatus;}
}