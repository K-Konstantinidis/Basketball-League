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

	public Connector(String ip, String requestedWS){
		OkHttpHandler okHttpHandler = new OkHttpHandler();
		String url = "http://" + ip + "/ws/";

		try {
			switch(requestedWS){
				case "player-finished-stats":
					url += "getFinishedMatchPlayerStats.php?lang=gr&cid=1&rid=5&gid=2";
					fpstats = okHttpHandler.getDataForFPlayers(url);
					break;
				case "player-live-stats":
					// TODO: Add the parameters and WS name
					url += ".php";
					lpstats = okHttpHandler.getDataForFPlayers(url);
					break;
				case "player-stats":
					url += "getChampionshipPlayerStats.php?lang=gr&cid=1";
					pstats = okHttpHandler.getDataForFPlayers(url);
					break;
				case "team-finished-stats":
					url += "getFinishedMatchTeamStats.php?lang=gr&cid=1&rid=4&gid=2";
					ftstats = okHttpHandler.getDataForFTeams(url);
					break;
				case "team-stats":
					url += "getChampionshipTeamStats.php?lang=gr&cid=1";
					tstats = okHttpHandler.getDataForTeams(url);
					break;
				case "week-matches":
					url += "getCurrentGameweek.php?cid=1";
					matches = okHttpHandler.getGameweekMatches(url);
					break;
				case "gameweeks":
					url += "getAllGameweeks.php?cid=1";
					weeks = okHttpHandler.getGameWeeks(url);
					break;
				case "league":
					url += "getLeagueRanking.php?lang=gr&cid=1";
					Ranking = okHttpHandler.getDataforLeague(url);
					break;
				case "top5":
					url += "getRoundTop5.php?lang=gr&cid=1&rid=5";
					top5 = okHttpHandler.getDataForTop5(url);
					break;
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	//maybe we will use this to add the round instead of the above
	public Connector(String ip, String requestedWS, String param) {
		OkHttpHandler okHttpHandler = new OkHttpHandler();
		String url = "http://" + ip + "/ws/";

		try {
			switch (requestedWS) {
				case "player-finished-stats":
					url += param;
					fpstats = okHttpHandler.getDataForFPlayersTabbed(url);
					break;
				case "player-live-stats":
					url += param;
					lpstats = okHttpHandler.getDataForFPlayers(url);
					break;
				case "tabbed-User":
					url += param;
					finishedGame = okHttpHandler.getDataForFinishedMatchTeams(url);
					break;
				case "overview-stats":
					url += param;
					overViewGame = okHttpHandler.getDataForFinishedScores(url);
					break;
				case "team-finished-stats":
					url += param;
					ftstats = okHttpHandler.getDataForFTeams(url);
					break;
				case "week-matches":
					url += "getGameweekMatches.php?cid=1&rid=" + param;
					matches = okHttpHandler.getGameweekMatches(url);
					break;
				case "gameweeks":
					url += "getAllGameweeks.php?cid=1";
					weeks = okHttpHandler.getGameWeeks(url);
					break;
				case "league":
					url += "getLeagueRanking.php?lang=gr&cid=1";
					Ranking = okHttpHandler.getDataforLeague(url);
					break;
				case "GameweeksTop5":
					url += "getEligibleTop5Rounds.php?lang=gr&cid=1&rid=" + param;
					weeks = okHttpHandler.getGameWeeks(url);
					break;
				case "top5":
					url += "getRoundTop5.php?lang=gr&cid=1&rid=" + param;
					top5 = okHttpHandler.getDataForTop5(url);
					break;
				case "players":
					url += "getTeamPlayers.php?lang=gr&tid=" + param;
					players = okHttpHandler.getTeamPlayers(url);
					break;
				case "ongoing-game-minute":
					url += param;
					currentMinute = okHttpHandler.getCurrentMinute(url);
					break;
				case "newest-events":
					url += param;
					mostRecentEvents = okHttpHandler.getNewestEvents(url);
					break;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
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
