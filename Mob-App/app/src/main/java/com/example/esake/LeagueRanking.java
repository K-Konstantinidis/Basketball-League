package com.example.esake;

import java.util.ArrayList;

public class LeagueRanking {
	private ArrayList<LeagueRank> Ranking = new ArrayList<>();

	//For FragmentLeagueTable
	public LeagueRanking(String ip){
		String url= "http://"+ip+"/ws/getLeagueRanking.php?lang=gr&cid=1";
		try {
			OkHttpHandler okHttpHandler = new OkHttpHandler();
			Ranking = okHttpHandler.getDataforLeague(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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


}
