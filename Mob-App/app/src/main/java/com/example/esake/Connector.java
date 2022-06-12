package com.example.esake;

import java.util.ArrayList;

public class Connector {

	private ArrayList<Game> match = new ArrayList<>();
	private ArrayList<Day> weeks = new ArrayList<>();
	private ArrayList<LeagueRank> Ranking = new ArrayList<>();
	private ArrayList<PlayerStats> pstats = new ArrayList<>();

	public Connector(String ip, String string){
		if(string.equals("player-stats")) {
			String url = "http://" + ip + "/ws/.php";
			try {
				OkHttpHandler okHttpHandler = new OkHttpHandler();
			//	weeks = okHttpHandler.getGameWeeks(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(string.equals("player-live-stats")) {
			String url = "http://" + ip + "/ws/.php";
			try {
				OkHttpHandler okHttpHandler = new OkHttpHandler();
				//weeks = okHttpHandler.getGameWeeks(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(string.equals("team-stats")) {
			String url = "http://" + ip + "/ws/.php";
			try {
				OkHttpHandler okHttpHandler = new OkHttpHandler();
				//weeks = okHttpHandler.getGameWeeks(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(string.equals("gameweeks")){
			String url = "http://" + ip + "/ws/populateRounds.php";
			try {
				OkHttpHandler okHttpHandler = new OkHttpHandler();
				weeks = okHttpHandler.getGameWeeks(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(string.equals("league")){
			String url= "http://"+ip+"/ws/getLeagueRanking.php?lang=gr&cid=1";
			try {
				OkHttpHandler okHttpHandler = new OkHttpHandler();
				Ranking = okHttpHandler.getDataforLeague(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(string.equals("match")){
			String url= "http://"+ip+"/ws/getGameweekMatches.php?round_id=2";
			try {
				OkHttpHandler okHttpHandler = new OkHttpHandler();
				match = okHttpHandler.getDataforMatches(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	//functions for game weeks

	public ArrayList<String> getWeeks() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i=0; i<weeks.size(); i++) {
			temp.add(weeks.get(i).getGameweek());
		}
		return temp;
	}

	//functions for league

	public String getLeagueRankTeamLogo(int teamid){

		return this.Ranking.get(teamid).getTeamlogo();
	}

	public ArrayList<LeagueRank> getRanking(){return Ranking;}
	public String getLeagueRankName(int teamid){
		return this.Ranking.get(teamid).getName();
	}
	public String getLeagueRankMatchesPlayed(int teamid){ return this.Ranking.get(teamid).getMatchesPlayed(); }
	public String getLeagueRankPoints(int teamid){
		return this.Ranking.get(teamid).getPoints();
	}
	public String getLeagueRankWins(int teamid){
		return this.Ranking.get(teamid).getWins();
	}
	public String getLeagueRankLosses(int teamid){
		return this.Ranking.get(teamid).getLosses();
	}

	//functions for matches

	public String getTeamList(int matchIndex, boolean isHomeTeam){
		return this.match.get(matchIndex).getTeamName(isHomeTeam);
	}

	public String getScoreList(int matchIndex, boolean isHomeTeam){
		return String.valueOf(this.match.get(matchIndex).getScore(isHomeTeam));
	}
}
