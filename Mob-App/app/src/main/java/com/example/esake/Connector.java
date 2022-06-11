package com.example.esake;

import java.util.ArrayList;

public class Connector {

	private ArrayList<Game> match = new ArrayList<>();
	private ArrayList<GameWeek> weeks = new ArrayList<>();
	private ArrayList<LeagueRank> Ranking = new ArrayList<>();
	private ArrayList<PlayerStats> pstats = new ArrayList<>();
	private ArrayList<Top5> top5 = new ArrayList<>();

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
		else if(string.equals("top5")){
			String url= "http://"+ip+"/ws/getRoundTop5.php?lang=gr&cid=1&rid=5";
			try {
				OkHttpHandler okHttpHandler = new OkHttpHandler();
				top5 = okHttpHandler.getDataForTop5(url);
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

	public String getLeagueRankName(int teamid){
		return this.Ranking.get(teamid).getName();
	}

	public String getLeagueRankMatchesPlayed(int teamid){
		return this.Ranking.get(teamid).getMatchesPlayed();
	}
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

	//functions for top5
//	public String getTop5Logo(int id){
//		return this.top5.get(id).getlogo();
//	}

	public String getTop5Name(int id){
		return this.top5.get(id).getName();
	}
	public String getTop5Position(int id){ return this.top5.get(id).getPos(); }
	public String getTop5Rating(int id){
		return this.top5.get(id).getRating();
	}
	public String getTop5Points(int id){
		return this.top5.get(id).getPoints();
	}

}
