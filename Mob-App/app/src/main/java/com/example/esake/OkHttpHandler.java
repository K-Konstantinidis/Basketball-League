package com.example.esake;

import android.os.StrictMode;

import org.json.JSONArray;
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

	//code for matches
	ArrayList<Game> getDataforMatches(String url) throws Exception {
		ArrayList<Game> game = new ArrayList<>();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		String data = response.body().string();
		//System.out.println("My Response: " + data);
		try {
			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();
			while(keys.hasNext()) {
//                String brand = keys.next();
//                String models = json.get(brand).toString();
				//String images = json.getJSONObject(brand).getString("images").toString();
				//cbList.add(new CarBrand(brand, models, images));
				//CODE FOR IMAGES
				// cbList.add(new CarBrand(brand, models));
				//delete this if you add image code

				String gameid = keys.next();
				String home_team = json.getJSONObject(gameid).getString("home_team");
				String home_teamscore = json.getJSONObject(gameid).getString("home_team_score");
				String away_team = json.getJSONObject(gameid).getString("away_team");
				String away_teamscore = json.getJSONObject(gameid).getString("away_team_score");

				int homeScore = 0;
				int awayScore = 0;
				homeScore = Integer.parseInt(home_teamscore);
				awayScore = Integer.parseInt(away_teamscore);
				Game newGame = new Game(gameid,homeScore,awayScore,home_team,away_team);

				game.add(newGame);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return game;
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
			JSONObject jObj = new JSONObject(data);
			JSONArray jsonArray = jObj.getJSONArray("rounds");
			for (int i=0; i<jsonArray.length(); i++){
				JSONObject obj = jsonArray.getJSONObject(i);
				String round = obj.getString("round_id");
				round = changeString(round);
				GameWeek week = new GameWeek(round);
				weeks.add(week);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return weeks;
	}

	//function to change the String
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
		//System.out.println("My Response: " + data);
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
				LeagueRank rank = new LeagueRank(logo_path,name_en,	Integer.parseInt(MatchesPlayed),
					Integer.parseInt(Points),Integer.parseInt(Wins),Integer.parseInt(Losses));
				Ranking.add(rank);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return Ranking;
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
				String logo = json.getJSONObject(id).getString("team_logo");
				String name= json.getJSONObject(id).getString("surname");
				String pos = json.getJSONObject(id).getString("position");
				String points = json.getJSONObject(id).getString("total_points");
				String rating = json.getJSONObject(id).getString("rating");


				//Code to add from Json to Screen
				Top5 newTop5 = new Top5(name,pos,Integer.parseInt(rating),Integer.parseInt(points));
				top5.add(newTop5);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return top5;
	}

	//function for Finished Matches
	ArrayList<PlayerStats> getDataForFinishedMatches(String url) throws Exception {
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
//				String logo = json.getJSONObject(id).getString("logo");
				String surname= json.getJSONObject(id).getString("surname");
				String total_points = json.getJSONObject(id).getString("total_points");
				String rating = json.getJSONObject(id).getString("rating");
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
				PlayerStats pStats = new PlayerStats(surname,Integer.parseInt(total_points),
					Integer.parseInt(rating),
					Integer.parseInt(shots_made),Integer.parseInt(perc_2_in),
					Integer.parseInt(perc_3_in),Integer.parseInt(perc_freethrows_in),
					Integer.parseInt(total_rebounds),Integer.parseInt(total_offensive_rebounds),
					Integer.parseInt(total_defensive_rebounds),Integer.parseInt(total_assists),
					Integer.parseInt(total_blocks),Integer.parseInt(total_steals),
					Integer.parseInt(total_turnovers),
					Integer.parseInt(total_fouls));
				playerStats.add(pStats);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return playerStats;
	}



}