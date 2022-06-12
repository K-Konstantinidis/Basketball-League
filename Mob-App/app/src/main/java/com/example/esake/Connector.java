package com.example.esake;

import java.util.ArrayList;

public class Connector {

	private ArrayList<Game> match = new ArrayList<>();
	private ArrayList<GameWeek> weeks = new ArrayList<>();
	private ArrayList<LeagueRank> Ranking = new ArrayList<>();
	private ArrayList<PlayerStats> fpstats = new ArrayList<>();
	private ArrayList<Top5> top5 = new ArrayList<>();

	public Connector(String ip, String string){
		if(string.equals("player-finished-stats")) {
			String url = "http://" + ip + "/ws/getFinishedMatchPlayerStats.php?lang=gr&cid=1&rid=5&gid=2";
			try {
				OkHttpHandler okHttpHandler = new OkHttpHandler();
				fpstats = okHttpHandler.getDataForFinishedMatches(url);
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
		if(string.equals("team-finished-stats")) {
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

	//functions for finished Player Stats
	public String getfinishedPlayerSurname(int id) {return this.fpstats.get(id).getSurname();}
	public String getfinishedPlayerTotal_points(int id) {return this.fpstats.get(id).getTotal_points();}
	public String getfinishedPlayerRating(int id) {return this.fpstats.get(id).getPRating();}
	public String getfinishedPlayerShots_made(int id) {return this.fpstats.get(id).getShots_made();}
	public String getfinishedPlayerPerc_2_in(int id) {return this.fpstats.get(id).getPerc_2_in();}
	public String getfinishedPlayerPerc_3_in(int id) {return this.fpstats.get(id).getPerc_3_in();}
	public String getfinishedPlayerPerc_freethrows_in(int id) {return this.fpstats.get(id).getPerc_freethrows_in();}
	public String getfinishedPlayerTotal_rebounds(int id) {return this.fpstats.get(id).getTotal_rebounds();}
	public String getfinishedPlayerTotal_offensive_rebounds(int id) {return this.fpstats.get(id).getTotal_offensive_rebounds();}
	public String getfinishedPlayerTotal_defensive_rebounds(int id) {return this.fpstats.get(id).getTotal_defensive_rebounds();}
	public String getfinishedPlayerTotal_assists(int id) {return this.fpstats.get(id).getTotal_assists();}
	public String getfinishedPlayerTotal_blocks(int id) {return this.fpstats.get(id).getTotal_blocks();}
	public String getfinishedPlayerTotal_steals(int id) {return this.fpstats.get(id).getTotal_steals();}
	public String getfinishedPlayerTotal_turnovers(int id) {return this.fpstats.get(id).getTotal_turnovers();}
	public String getfinishedPlayerTotal_fouls(int id) {return this.fpstats.get(id).getTotal_fouls();}
}
