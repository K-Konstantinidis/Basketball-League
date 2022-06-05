package com.example.esake;

public class Game {
    private int score1;
    private int score2;
//    private int team1Id;
//    private int team2Id;

    public Game(int score1, int score2) {
        this.score1 = score1;
        this.score2 = score2;
//        this.team1Id = team1Id;
//        this.team2Id = team2Id;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

//    public int getTeam1Id() {
//        return team1Id;
//    }
//
//    public int getTeam2Id() {
//        return team2Id;
//    }
}
