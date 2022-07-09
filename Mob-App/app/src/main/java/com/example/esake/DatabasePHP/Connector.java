package com.example.esake.DatabasePHP;

import com.example.esake.Game;
import com.example.esake.GameWeek;
import com.example.esake.LeagueRank;
import com.example.esake.Player;
import com.example.esake.PlayerStats;
import com.example.esake.TeamStats;
import com.example.esake.Top5;

import java.util.ArrayList;

public class Connector {
	//private String currentMinute;
	private String url;
	OkHttpHandler okHttpHandler = new OkHttpHandler();

	public Connector(String ip, String param) {
		this.url = "http://" + ip + "/ws/" + param;
	}

	public final ArrayList<Player> players() {
		return okHttpHandler.getTeamPlayers(url);
	}

	public final ArrayList<PlayerStats> finishedPlayerStats() {
		return okHttpHandler.getDataForFPlayersTabbed(url);
	}

	public final ArrayList<PlayerStats> livePlayerStats() {
		// TODO: Fix url when called and Add new method in OkHttpHandler class
		return okHttpHandler.getDataForFPlayers(url);
	}

	public final ArrayList<PlayerStats> playerStats() {
		return okHttpHandler.getDataForFPlayers(url);
	}

	public final Game TabbedUser() {
		return okHttpHandler.getDataForFinishedMatchTeams(url);
	}
	public final Game overviewStats() {
		return okHttpHandler.getDataForFinishedScores(url);
	}

	public final ArrayList<TeamStats> teamFinishedStats() {
		return okHttpHandler.getDataForFTeams(url);
	}

	public final ArrayList<TeamStats> teamStats() {
		return okHttpHandler.getDataForTeams(url);
	}

	public final ArrayList<GameWeek> weekMatches() {
		return okHttpHandler.getGameweekMatches(url);
	}

	public final ArrayList<LeagueRank> league() {
		return okHttpHandler.getDataforLeague(url);
	}

	public final ArrayList<Top5> top5() {
		return okHttpHandler.getDataForTop5(url);
	}

	public final String[] events() {
		return okHttpHandler.getNewestEvents(url);
	}

	public final ArrayList<String> gameWeeks() {
		ArrayList<String> temp = new ArrayList<>();
		ArrayList<GameWeek> weeks = okHttpHandler.getGameWeeks(url);
		for(int i = 0; i< weeks.size(); i++)
			temp.add(weeks.get(i).getGameweek());
		return temp;
	}

//	public final String gameMinute() throws Exception {
//		return okHttpHandler.getCurrentMinute(url);
//	}

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
