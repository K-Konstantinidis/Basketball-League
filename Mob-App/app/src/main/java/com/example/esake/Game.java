package com.example.esake;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

	private String gameid;
	private Team homeTeam, awayTeam;
	private String homeTeamName, awayTeamName;
	private int homeTeamId, awayTeamId;
	private String homeTeamLogo, awayTeamLogo;
    private int score1;
    private int score2;
    private int Q1score1, Q1score2;
	private int Q2score1, Q2score2;
	private int Q3score1, Q3score2;
	private int Q4score1, Q4score2;
    private ArrayList<Team> teams;
//    private int team1Id;
//    private int team2Id;

	//constructor

	public Game(String gameid, int score1, int score2, String homeTeam, String awayTeam) {
		this.gameid = gameid;
		this.score1 = score1;
		this.score2 = score2;
		this.homeTeamName = awayTeam;
		this.awayTeamName = homeTeam;
//	    this.teams = new ArrayList<>();
//        this.team1Id = team1Id;
//        this.team2Id = team2Id;
	}

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
		scores = Arrays.asList(awayTeamQuarterScores.split(","));
		this.Q1score2 = Integer.parseInt(scores.get(0));
		this.Q2score2 = Integer.parseInt(scores.get(1));
		this.Q3score2 = Integer.parseInt(scores.get(2));
		this.Q4score2 = Integer.parseInt(scores.get(3));
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
	public int getQ1score1() {return Q1score1; }

	public int getQ1score2() {return Q1score2; }

	public int getQ2score1() {return Q2score1;}

	public int getQ2score2() {return Q2score2; }

	public int getQ3score1() { return Q3score1;}

	public int getQ3score2() {return Q3score2; }

	public int getQ4score1() { return Q4score1;}

	public int getQ4score2() {return Q4score2; }

	public ArrayList<Team> getTeams() {return teams;}

	public int getScore1() {return score1; }

    public int getScore2() {
        return score2;
    }

    public int getScore(boolean isHomeTeam){

    	score1 = getScore1();
    	score2 = getScore2();

    	if(isHomeTeam)
    		return score1;
    	else
    		return score2;
	}

	public void setScores(String score1, String score2){

		this.score1 = Integer.parseInt(score1);
		this.score2 = Integer.parseInt(score2);

	}

	public String getTeamName(boolean isHomeTeam){
		if (isHomeTeam)
			return homeTeamName;
		else
			return awayTeamName;
	}

	public int getHomeTeamId() {
		return homeTeamId;
	}

	public int getAwayTeamId() {
		return awayTeamId;
	}

	public String getHomeTeamLogo() {
		return "http://"+myIP.getIp()+homeTeamLogo;
	}

	public String getAwayTeamLogo() {
		return "http://"+myIP.getIp()+awayTeamLogo;
	}

	//	public void setNameTeams(String team1, String team2){
//    	this.awayTeam.setName(team1);
//    	this.homeTeam.setName(team2);
//	}

//    public int getTeam1Id() {
//        return team1Id;
//    }
//
//    public int getTeam2Id() {
//        return team2Id;
//    }
}
