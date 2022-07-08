package com.example.esake;

import androidx.annotation.NonNull;
import java.util.ArrayList;

public class Connector {
	//private String currentMinute;
	private String url;
	OkHttpHandler okHttpHandler = new OkHttpHandler();

	public Connector(String ip, String param) {
		this.url = "http://" + ip + "/ws/" + param;
	}

	public final ArrayList<Player> players() throws Exception {
		return okHttpHandler.getTeamPlayers(url);
	}

	public final ArrayList<PlayerStats> finishedPlayerStats() throws Exception {
		return okHttpHandler.getDataForFPlayersTabbed(url);
	}

	public final ArrayList<PlayerStats> livePlayerStats() throws Exception {
		// TODO: Fix url when called and Add new method in OkHttpHandler class
		return okHttpHandler.getDataForFPlayers(url);
	}

	public final ArrayList<PlayerStats> playerStats() throws Exception {
		return okHttpHandler.getDataForFPlayers(url);
	}

	public final Game TabbedUser() throws Exception {
		return okHttpHandler.getDataForFinishedMatchTeams(url);
	}
	public final Game overviewStats() throws Exception {
		return okHttpHandler.getDataForFinishedScores(url);
	}

	public final ArrayList<TeamStats> teamFinishedStats() throws Exception {
		return okHttpHandler.getDataForFTeams(url);
	}

	public final ArrayList<TeamStats> teamStats() throws Exception {
		return okHttpHandler.getDataForTeams(url);
	}

	public final ArrayList<GameWeek> weekMatches() throws Exception {
		return okHttpHandler.getGameweekMatches(url);
	}

	public final ArrayList<LeagueRank> league() throws Exception {
		return okHttpHandler.getDataforLeague(url);
	}

	public final ArrayList<Top5> top5() throws Exception {
		return okHttpHandler.getDataForTop5(url);
	}

	public final String[] events() throws Exception {
		return okHttpHandler.getNewestEvents(url);
	}

	public final ArrayList<String> gameWeeks() throws Exception {
		ArrayList<String> temp = new ArrayList<>();
		ArrayList<GameWeek> weeks = okHttpHandler.getGameWeeks(url);
		for(int i = 0; i< weeks.size(); i++)
			temp.add(weeks.get(i).getGameweek());
		return temp;
	}

//	public final String gameMinute() throws Exception {
//		return okHttpHandler.getCurrentMinute(url);
//	}

	//functions for finished Team Stats
	//public String getfinishedTeamName(ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getName();}
	public String getfinishedTeamTotal_points(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getIntTotal_points();}
	public String getfinishedTeamShots_made(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getIntShots_made();}
	public String getfinishedTeamPerc_2_in(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getPerc_2_in();}
	public String getfinishedTeamPerc_3_in(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getPerc_3_in();}
	public String getfinishedTeamPerc_freethrows_in(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getPerc_freethrows_in();}
	public String getfinishedTeamTotal_rebounds(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getIntTotal_rebounds();}
	public String getfinishedTeamTotal_offensive_rebounds(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getIntTotal_offensive_rebounds();}
	public String getfinishedTeamTotal_defensive_rebounds(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getIntTotal_defensive_rebounds();}
	public String getfinishedTeamTotal_assists(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getIntTotal_assists();}
	public String getfinishedTeamTotal_blocks(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getIntTotal_blocks();}
	public String getfinishedTeamTotal_steals(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getIntTotal_steals();}
	public String getfinishedTeamTotal_turnovers(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getIntTotal_turnovers();}
	public String getfinishedTeamTotal_fouls(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getIntTotal_fouls();}
	public String getfinishedTeamLogo(@NonNull ArrayList<TeamStats> FnStats, int id) {return FnStats.get(id).getLogo();}

//	public String getCurrentMinute(int gameStatus) {
//
//		switch (gameStatus) {
//			case 2:
//				return "—";
//			case 1:
//				return this.currentMinute + "'";
//			case 0:
//				return "40'";
//		}
//		return null;
//	}
}
