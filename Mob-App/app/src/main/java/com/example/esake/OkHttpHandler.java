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

	//code for matches
	ArrayList<Championship> populateDropDown(String url) throws Exception {
		ArrayList<Championship> matchList = new ArrayList<>();
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
				matchList.add(new Championship(gameid,home_team,home_teamscore,away_team,away_teamscore));

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return matchList;
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



	public void logHistory(String url) throws Exception {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		Response response = client.newCall(request).execute();
		System.out.println("My Response: " + response);
	}
}