package com.example.esake;

import java.util.Arrays;
import java.util.List;

public class Game {
	private String homeTeamName, awayTeamName;
	private int homeTeamId, awayTeamId;
	private String homeTeamLogo, awayTeamLogo;
    private int score1;
    private int score2;
    private int Q1score1, Q1score2;
	private int Q2score1, Q2score2;
	private int Q3score1, Q3score2;
	private int Q4score1, Q4score2;

	public Game(String homeTeamName, int homeTeamTotalScore, String homeTeamQuarterScores,
				String awayTeamName, int awayTeamTotalScore, String awayTeamQuarterScores) {

		this.homeTeamName = homeTeamName;
		this.score1 = homeTeamTotalScore;
		List<String> scores = Arrays.asList(homeTeamQuarterScores.split(","));
		this.Q1score1 = Integer.parseInt(scores.get(0));
		this.Q2score1 = Integer.parseInt(scores.get(1));
		this.Q3score1 = Integer.parseInt(scores.get(2));
		this.Q4score1 = Integer.parseInt(scores.get(3));

		this.awayTeamName = awayTeamName;
		this.score2 = awayTeamTotalScore;
		List<String> scores2 = Arrays.asList(awayTeamQuarterScores.split(","));
		this.Q1score2 = Integer.parseInt(scores2.get(0));
		this.Q2score2 = Integer.parseInt(scores2.get(1));
		this.Q3score2 = Integer.parseInt(scores2.get(2));
		this.Q4score2 = Integer.parseInt(scores2.get(3));
	}

	public Game(int homeTeamId, String homeTeamLogo, int homeTeamScore, int awayTeamId, String awayTeamLogo, int awayTeamScore) {
		this.homeTeamId=homeTeamId;
		this.homeTeamLogo=homeTeamLogo;
		this.score1=homeTeamScore;

		this.awayTeamId=awayTeamId;
		this.awayTeamLogo=awayTeamLogo;
		this.score2=awayTeamScore;
	}

	//Getters
	public String getHomeTeamName() {
		return homeTeamName;
	}
	public String getAwayTeamName() {
		return awayTeamName;
	}
	public int getQ1score1() {return Q1score1; }
	public int getQ1score2() {return Q1score2; }
	public int getQ2score1() {return Q2score1;}
	public int getQ2score2() {return Q2score2; }
	public int getQ3score1() {return Q3score1;}
	public int getQ3score2() {return Q3score2; }
	public int getQ4score1() {return Q4score1;}
	public int getQ4score2() {return Q4score2; }
	public int getScore1() {return score1; }
    public int getScore2() {return score2; }
	public String getHomeTeamLogo() {
		return "http://"+ MyIP.getIp()+homeTeamLogo;
	}
	public String getAwayTeamLogo() {
		return "http://"+ MyIP.getIp()+awayTeamLogo;
	}
}
