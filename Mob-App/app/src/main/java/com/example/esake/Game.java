package com.example.esake;

import java.util.ArrayList;

public class Game {

	private String gameid;
	private Team homeTeam, awayTeam;
	private String homeTeamName, awayTeamName;
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
//        this.teams = new ArrayList<>();
//        this.team1Id = team1Id;
//        this.team2Id = team2Id;
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
