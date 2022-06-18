package com.example.esake;

import java.util.ArrayList;

public class Connector {

	private ArrayList<GameWeek> matches = new ArrayList<>();
	private ArrayList<GameWeek> weeks = new ArrayList<>();
	private ArrayList<LeagueRank> Ranking = new ArrayList<>();
	private ArrayList<PlayerStats> fpstats = new ArrayList<>();
	private ArrayList<PlayerStats> lpstats = new ArrayList<>();
	private ArrayList<PlayerStats> pstats = new ArrayList<>();
	private ArrayList<TeamStats> ftstats = new ArrayList<>();
	private ArrayList<TeamStats> tstats = new ArrayList<>();
	private ArrayList<Top5> top5 = new ArrayList<>();
	private Game overViewGame;
	private Game finishedGame;
	private ArrayList<Player> players = new ArrayList<>();
	private String currentMinute;
	private String[] mostRecentEvents;

	public Connector(String ip, String string){

		String url;

		switch(string){
			case "player-finished-stats":
				url = "http://" + ip + "/ws/getFinishedMatchPlayerStats.php?lang=gr&cid=1&rid=5&gid=2";
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					fpstats = okHttpHandler.getDataForFPlayers(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "player-live-stats":
				url = "http://" + ip + "/ws/.php";
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					lpstats = okHttpHandler.getDataForFPlayers(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "player-stats":
				url = "http://" + ip + "/ws/getChampionshipPlayerStats.php?lang=gr&cid=1";
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					pstats = okHttpHandler.getDataForFPlayers(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "team-finished-stats":
				url = "http://" + ip + "/ws/getFinishedMatchTeamStats.php?lang=gr&cid=1&rid=4&gid=2";
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					ftstats = okHttpHandler.getDataForFTeams(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "team-stats":
				url = "http://" + ip + "/ws/getChampionshipTeamStats.php?lang=gr&cid=1";
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					tstats = okHttpHandler.getDataForTeams(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "week-matches":
				url = "http://" + ip + "/ws/getCurrentGameweek.php?cid=1";
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					matches = okHttpHandler.getGameweekMatches(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "gameweeks":
				url = "http://" + ip + "/ws/getAllGameweeks.php?cid=1";
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					weeks = okHttpHandler.getGameWeeks(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "league":
				url= "http://"+ip+"/ws/getLeagueRanking.php?lang=gr&cid=1";
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					Ranking = okHttpHandler.getDataforLeague(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "top5":
				url= "http://"+ip+"/ws/getRoundTop5.php?lang=gr&cid=1&rid=5";
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					top5 = okHttpHandler.getDataForTop5(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	//maybe we will use this to add the round instead of the above
	public Connector(String ip,String string, String param) {

		String url;

		switch(string) {
			case "player-finished-stats":
				url = "http://" + ip +"/ws/"+ param;
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					fpstats = okHttpHandler.getDataForFPlayersTabbed(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "player-live-stats":
				url = "http://" + ip + "/ws/"+param;
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					lpstats = okHttpHandler.getDataForFPlayers(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "tabbed-User":
				url = "http://" + ip + "/ws/"+param;
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					finishedGame = okHttpHandler.getDataForFinishedMatchTeams(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "overview-stats":
				url = "http://" + ip + "/ws/"+param;
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					overViewGame = okHttpHandler.getDataForFinishedScores(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "team-finished-stats":
				url = "http://" + ip + "/ws/"+param;
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					ftstats = okHttpHandler.getDataForFTeams(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "week-matches":
				url = "http://" + ip + "/ws/getGameweekMatches.php?cid=1&rid=" + param;
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					matches = okHttpHandler.getGameweekMatches(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "gameweeks":
				url = "http://" + ip + "/ws/getAllGameweeks.php?cid=1";
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					weeks = okHttpHandler.getGameWeeks(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "league":
				url = "http://" + ip + "/ws/getLeagueRanking.php?lang=gr&cid=1";
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					Ranking = okHttpHandler.getDataforLeague(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "GameweeksTop5":
				url = "http://" + ip + "/ws/getEligibleTop5Rounds.php?lang=gr&cid=1&rid="+param;
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					weeks = okHttpHandler.getGameWeeks(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "top5":
				url = "http://" + ip + "/ws/getRoundTop5.php?lang=gr&cid=1&rid="+param;
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					top5 = okHttpHandler.getDataForTop5(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "players":
				url = "http://" + ip + "/ws/getTeamPlayers.php?lang=gr&tid="+param;
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					players = okHttpHandler.getTeamPlayers(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "ongoing-game-minute":
				url = "http://" + ip + "/ws/"+param;
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					currentMinute = okHttpHandler.getCurrentMinute(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "newest-events":
				url = "http://" + ip + "/ws/"+param;
				try {
					OkHttpHandler okHttpHandler = new OkHttpHandler();
					mostRecentEvents = okHttpHandler.getNewestEvents(url);
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

	//GETTERS
	//Pass the List with the ranking
	//functions for game overview User
	public String getOverviewScore(int id){return String.valueOf(this.finishedGame.getScore1());}
	//Function for a game overview
	public Game getOverViewFinishedGame() { return this.overViewGame; }
	public ArrayList<LeagueRank> getRanking(){return Ranking;}
	//Pass the List with the player stats of a finished game
	public ArrayList<PlayerStats> getFinishedPlayerStats(){return fpstats;}
	//Pass the List with the player stats of a live game
	public ArrayList<PlayerStats> getLivePlayerStats(){return lpstats;}
	//Pass the List with all the player stats
	public ArrayList<PlayerStats> getPlayerStats(){return pstats;}
	//Pass the List with the team stats of a finished game
	public ArrayList<TeamStats> getFinishedTeamStats(){return ftstats;}
	//Pass the List with all the team stats
	public ArrayList<TeamStats> getTeamStats(){return tstats;}
	//Pass the List with the Top 5
	public ArrayList<Top5> getTop5(){return top5;}
	//Pass the List with the player games
	public ArrayList<GameWeek> getMatches(){return matches;}
	//Pass the List with the player games
	public Game getFinishedGame() { return this.finishedGame; }

	//functions for finished Team Stats
	public String getfinishedTeamName(int id) {return this.ftstats.get(id).getName();}
	public String getfinishedTeamTotal_points(int id) {return this.ftstats.get(id).getIntTotal_points();}
	public String getfinishedTeamShots_made(int id) {return this.ftstats.get(id).getIntShots_made();}
	public String getfinishedTeamPerc_2_in(int id) {return this.ftstats.get(id).getPerc_2_in();}
	public String getfinishedTeamPerc_3_in(int id) {return this.ftstats.get(id).getPerc_3_in();}
	public String getfinishedTeamPerc_freethrows_in(int id) {return this.ftstats.get(id).getPerc_freethrows_in();}
	public String getfinishedTeamTotal_rebounds(int id) {return this.ftstats.get(id).getIntTotal_rebounds();}
	public String getfinishedTeamTotal_offensive_rebounds(int id) {return this.ftstats.get(id).getIntTotal_offensive_rebounds();}
	public String getfinishedTeamTotal_defensive_rebounds(int id) {return this.ftstats.get(id).getIntTotal_defensive_rebounds();}
	public String getfinishedTeamTotal_assists(int id) {return this.ftstats.get(id).getIntTotal_assists();}
	public String getfinishedTeamTotal_blocks(int id) {return this.ftstats.get(id).getIntTotal_blocks();}
	public String getfinishedTeamTotal_steals(int id) {return this.ftstats.get(id).getIntTotal_steals();}
	public String getfinishedTeamTotal_turnovers(int id) {return this.ftstats.get(id).getIntTotal_turnovers();}
	public String getfinishedTeamTotal_fouls(int id) {return this.ftstats.get(id).getIntTotal_fouls();}
	public String getfinishedTeamLogo(int id) {return this.ftstats.get(id).getLogo();}

	public String getCurrentMinute(int gameStatus) {
		switch (gameStatus) {
			case 2:
				return "—";
			case 1:
				return this.currentMinute + "'";
			case 0:
				return "40'";
		}
		return null;
	}

	public ArrayList<Player> getTeamPlayers(int id) { return this.players; }

	public String[] getMostRecentEvents() {
		return mostRecentEvents;
	}
}
