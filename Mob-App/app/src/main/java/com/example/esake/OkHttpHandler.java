package com.example.esake;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandler {

	public OkHttpHandler() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	//Code for Game weeks
	ArrayList<GameWeek> getGameWeeks(String url) throws Exception {
		ArrayList<GameWeek> weeks = new ArrayList<>();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		try {
			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();

			//Getting json from WS

			while(keys.hasNext()) {
				String id = keys.next();
				String round = json.getJSONObject(id).getString("id").toString();

				round = changeString(round);

				GameWeek newWeek = new GameWeek(round);
				weeks.add(newWeek);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return weeks;
	}

	private String changeString(String s){
		return "Gameweek "+s;
	}

	//code for League Table
	ArrayList<LeagueRank> getDataforLeague(String url) throws Exception {
		ArrayList<LeagueRank> Ranking = new ArrayList<LeagueRank>();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		try {
			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();

			//Getting json from WS
			while(keys.hasNext()) {
				String id = keys.next();
				String logo_path = json.getJSONObject(id).getString("logo");
				String name_en = json.getJSONObject(id).getString("name");
				String MatchesPlayed = json.getJSONObject(id).getString("matches_played");
				String Points = json.getJSONObject(id).getString("points");
				String Wins = json.getJSONObject(id).getString("wins");
				String Losses = json.getJSONObject(id).getString("losses");

				//Code to add from Json to Screen
				LeagueRank rank = new LeagueRank(logo_path, name_en, Integer.parseInt(MatchesPlayed),
					Integer.parseInt(Points), Integer.parseInt(Wins), Integer.parseInt(Losses));
				Ranking.add(rank);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return Ranking;
	}

	ArrayList<GameWeek> getGameweekMatches(String url) throws Exception {
		ArrayList<GameWeek> gameWeeks = new ArrayList<>();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		try {
			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();

			//Getting json from WS

			while(keys.hasNext()) {
				String id = keys.next();
				String gameId = json.getJSONObject(id).getString("game");
				String logoHome = json.getJSONObject(id).getString("home_logo");
				String logoAway = json.getJSONObject(id).getString("away_logo");
				String homeScore= json.getJSONObject(id).getString("home_team_score");
				String awayScore = json.getJSONObject(id).getString("away_team_score");
				String gameStatus = json.getJSONObject(id).getString("game_status");

				//Code to add from Json to Screen
				GameWeek Gweek = new GameWeek(logoHome, logoAway, Integer.parseInt(gameId),Integer.parseInt(homeScore),
					Integer.parseInt(awayScore), Integer.parseInt(gameStatus));
				gameWeeks.add(Gweek);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return gameWeeks;
	}

	ArrayList<Top5> getDataForTop5(String url) throws Exception {
		ArrayList<Top5> top5 = new ArrayList<>();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		try {
			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();

			//Getting json from WS

			while(keys.hasNext()) {
				String id = keys.next();
				String image = json.getJSONObject(id).getString("image");
				String name= json.getJSONObject(id).getString("surname");
				String pos = json.getJSONObject(id).getString("position");
				String rating = json.getJSONObject(id).getString("rating");


				//Code to add from Json to Screen
				Top5 newTop5 = new Top5(name,pos,image,Integer.parseInt(rating));
				top5.add(newTop5);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return top5;
	}

	//function for Finished Matches Players
	ArrayList<PlayerStats> getDataForFPlayers(String url) throws Exception {
		ArrayList<PlayerStats> playerStats = new ArrayList<>();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		try {
			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();

			//Getting json from WS

			while(keys.hasNext()) {
				String id = keys.next();
				String logo = json.getJSONObject(id).getString("logo");
				String surname= json.getJSONObject(id).getString("surname");
				String avgTotalShots = json.getJSONObject(id).getString("avg_total_shots");
				String avgRating = json.getJSONObject(id).getString("avg_rating");
				String avgTwoPointsIn = json.getJSONObject(id).getString("avg_perc_2_in");
				String avgTwoPointsOut = json.getJSONObject(id).getString("avg_two_points_out");
				String avgThreePointsIn = json.getJSONObject(id).getString("avg_perc_3_in");
				String avgThreePointsOut = json.getJSONObject(id).getString("avg_three_points_out");
				String avgFreethrowsIn = json.getJSONObject(id).getString("avg_perc_freethrows_in");
				String avgFreethrowsOut = json.getJSONObject(id).getString("avg_freethrows_out");
				String avgTotalRebounds = json.getJSONObject(id).getString("avg_total_rebounds");
				String avgOffensiveRebounds = json.getJSONObject(id).getString("avg_offensive_rebounds");
				String avgDefensiveRebounds = json.getJSONObject(id).getString("avg_defensive_rebounds");
				String avgAssists = json.getJSONObject(id).getString("avg_assists");
				String avgBlocks = json.getJSONObject(id).getString("avg_blocks");
				String avgSteals = json.getJSONObject(id).getString("avg_steals");
				String avgTurnovers = json.getJSONObject(id).getString("avg_turnovers");
				String avgFouls = json.getJSONObject(id).getString("avg_fouls");

				//Code to add from Json to Screen
				PlayerStats pStats = new PlayerStats(logo, surname, Double.parseDouble(avgRating),
					Double.parseDouble(avgFreethrowsIn), Double.parseDouble(avgFreethrowsOut),
					Double.parseDouble(avgTwoPointsIn), Double.parseDouble(avgTwoPointsOut),
					Double.parseDouble(avgThreePointsIn), Double.parseDouble(avgThreePointsOut),
					Double.parseDouble(avgTotalShots), Double.parseDouble(avgTotalRebounds),
					Double.parseDouble(avgOffensiveRebounds), Double.parseDouble(avgDefensiveRebounds),
					Double.parseDouble(avgBlocks), Double.parseDouble(avgAssists), Double.parseDouble(avgSteals),
					Double.parseDouble(avgTurnovers), Double.parseDouble(avgFouls));
				playerStats.add(pStats);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return playerStats;
	}

	//function for Finished Matches
	ArrayList<TeamStats> getDataForFTeams(String url) throws Exception {
		ArrayList<TeamStats> teamStats = new ArrayList<>();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		try {
			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();

			//Getting json from WS

			while (keys.hasNext()) {
				String id = keys.next();
				String logo = json.getJSONObject(id).getString("logo");
				String name = json.getJSONObject(id).getString("name");
				String total_points = json.getJSONObject(id).getString("total_shots");
				String shots_made = json.getJSONObject(id).getString("shots_made");
				String perc_2_in = json.getJSONObject(id).getString("perc_2_in");
				String perc_3_in = json.getJSONObject(id).getString("perc_3_in");
				String perc_freethrows_in = json.getJSONObject(id).getString("perc_freethrows_in");
				String total_rebounds = json.getJSONObject(id).getString("total_rebounds");
				String total_offensive_rebounds = json.getJSONObject(id).getString("total_offensive_rebounds");
				String total_defensive_rebounds = json.getJSONObject(id).getString("total_defensive_rebounds");
				String total_assists = json.getJSONObject(id).getString("total_assists");
				String total_blocks = json.getJSONObject(id).getString("total_blocks");
				String total_steals = json.getJSONObject(id).getString("total_steals");
				String total_turnovers = json.getJSONObject(id).getString("total_turnovers");
				String total_fouls = json.getJSONObject(id).getString("total_fouls");

				//Code to add from Json to Screen
				TeamStats tStats = new TeamStats(logo, name, Double.parseDouble(total_points),
					Double.parseDouble(shots_made), Double.parseDouble(perc_2_in),
					Double.parseDouble(perc_3_in), Double.parseDouble(perc_freethrows_in),
					Double.parseDouble(total_rebounds), Double.parseDouble(total_offensive_rebounds),
					Double.parseDouble(total_defensive_rebounds), Double.parseDouble(total_assists),
					Double.parseDouble(total_blocks), Double.parseDouble(total_steals),
					Double.parseDouble(total_turnovers),
					Double.parseDouble(total_fouls));
				teamStats.add(tStats);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return teamStats;
	}

	//function for Finished Matches
	ArrayList<TeamStats> getDataForTeams(String url) throws Exception {
		ArrayList<TeamStats> teamStats = new ArrayList<>();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		try {
			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();

			//Getting json from WS
			while (keys.hasNext()) {
				String id = keys.next();
				String logo = json.getJSONObject(id).getString("logo");
				String name = json.getJSONObject(id).getString("name");
				String total_points = json.getJSONObject(id).getString("avg_total_points");
				String shots_made = json.getJSONObject(id).getString("avg_total_shots");
				String perc_2_in = json.getJSONObject(id).getString("avg_perc_2_in");
				String perc_3_in = json.getJSONObject(id).getString("avg_perc_3_in");
				String perc_freethrows_in = json.getJSONObject(id).getString("avg_perc_freethrows_in");
				String total_rebounds = json.getJSONObject(id).getString("avg_total_rebounds");
				String total_offensive_rebounds = json.getJSONObject(id).getString("avg_offensive_rebounds");
				String total_defensive_rebounds = json.getJSONObject(id).getString("avg_defensive_rebounds");
				String total_assists = json.getJSONObject(id).getString("avg_assists");
				String total_blocks = json.getJSONObject(id).getString("avg_blocks");
				String total_steals = json.getJSONObject(id).getString("avg_steals");
				String total_turnovers = json.getJSONObject(id).getString("avg_turnovers");
				String total_fouls = json.getJSONObject(id).getString("avg_fouls");

				//Code to add from Json to Screen
				TeamStats tStats = new TeamStats(logo, name, Double.parseDouble(total_points),
					Double.parseDouble(shots_made), Double.parseDouble(perc_2_in),
					Double.parseDouble(perc_3_in), Double.parseDouble(perc_freethrows_in),
					Double.parseDouble(total_rebounds), Double.parseDouble(total_offensive_rebounds),
					Double.parseDouble(total_defensive_rebounds), Double.parseDouble(total_assists),
					Double.parseDouble(total_blocks), Double.parseDouble(total_steals),
					Double.parseDouble(total_turnovers),
					Double.parseDouble(total_fouls));
				teamStats.add(tStats);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return teamStats;
	}

	//function for Finished Matches Score and team emblems
	Game getDataForFinishedMatchTeams(String url) throws Exception {
		Game g;
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		try {
			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();

			//Getting json from WS

			while (keys.hasNext()) {
				String homeTeamId = keys.next();
				String homeTeamLogo = json.getJSONObject(homeTeamId).getString("logo");
				String homeTeamScore = json.getJSONObject(homeTeamId).getString("total_score");

				String awayTeamId = keys.next();
				String awayTeamLogo = json.getJSONObject(awayTeamId).getString("logo");
				String awayTeamScore = json.getJSONObject(awayTeamId).getString("total_score");


				//Code to add from Json to Screen
				return new Game(Integer.parseInt(homeTeamId),homeTeamLogo,Integer.parseInt(homeTeamScore),
					Integer.parseInt(awayTeamId),awayTeamLogo,Integer.parseInt(awayTeamScore));

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	return null;
	}

	ArrayList<Player> getTeamPlayers(String url) throws Exception {
		ArrayList<Player> players = new ArrayList<>();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		try {
			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();

			//Getting json from WS

			while (keys.hasNext()) {
				String playerId = keys.next();
				String teamId = json.getJSONObject(playerId).getString("team_id");
				String surname = json.getJSONObject(playerId).getString("surname");

				//Code to add from Json to Screen
				players.add(new Player(surname, Integer.parseInt(playerId),Integer.parseInt(teamId)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return players;
	}

	Game getDataForFinishedScores(String url) throws Exception {
		Game game;
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		try {
			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();
			while(keys.hasNext()) {
				String id = keys.next();
				String homeTeamName = json.getJSONObject(id).getString("name");
				int homeTeamTotalScore = Integer.parseInt(json.getJSONObject(id).getString("total_score"));
				String homeTeamTotalscores = json.getJSONObject(id).getString("scores");

				id = keys.next();
				String awayTeamName = json.getJSONObject(id).getString("name");
				int awayTeamTotalScore = Integer.parseInt(json.getJSONObject(id).getString("total_score"));
				String awayTeamTotalscores = json.getJSONObject(id).getString("scores");

				return new Game(homeTeamName, homeTeamTotalScore, homeTeamTotalscores,
					awayTeamName, awayTeamTotalScore, awayTeamTotalscores);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	String getCurrentMinute(String url) throws Exception {
		ArrayList<Player> players = new ArrayList<>();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		try {
			JSONObject json = new JSONObject(data);
			String currentMinute = json.getString("current_minute");
			return currentMinute;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
