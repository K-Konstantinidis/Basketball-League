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
	ArrayList<Day> getGameWeeks(String url) throws Exception {
		ArrayList<Day> weeks = new ArrayList<>();
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
				Day week = new Day(round);
				weeks.add(week);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return weeks;
	}

	Game getScoreDataForMatch(String url) throws Exception {
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


	public void logHistory(String url) throws Exception {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		System.out.println("My Response: " + response);
	}
}