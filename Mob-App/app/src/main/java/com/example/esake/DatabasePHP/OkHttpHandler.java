package com.example.esake.DatabasePHP;

import android.os.StrictMode;

import com.example.esake.Game;
import com.example.esake.GameWeek;
import com.example.esake.LeagueRank;
import com.example.esake.Player;
import com.example.esake.PlayerStats;
import com.example.esake.TeamStats;
import com.example.esake.Top5;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	//Method to get the Game Weeks
	ArrayList<GameWeek> getGameWeeks(String url) {
		ArrayList<GameWeek> weeks = new ArrayList<>();
		String data = getDataFromWebService(url);
		if (data != null) {
			try {
				JSONObject json = new JSONObject(data);
				Iterator<String> keys = json.keys();

				//Getting json from WS
				while (keys.hasNext()) {
					String id = keys.next();
					String round = json.getJSONObject(id).getString("id");

					round = changeString(round);
					GameWeek newWeek = new GameWeek(round);
					weeks.add(newWeek);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return weeks;
		}
		return null;
	}

	//Method to change the Game Week
	private String changeString(String round) {
		return "Gameweek " + round;
	}

	//Method to get the League Table
	ArrayList<LeagueRank> getDataforLeague(String url) {
		ArrayList<LeagueRank> Ranking = new ArrayList<>();
		String data = getDataFromWebService(url);
		if (data != null) {
			try {
				JSONObject json = new JSONObject(data);
				Iterator<String> keys = json.keys();

				//Getting json from WS
				while (keys.hasNext()) {
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
		return null;
	}

	//Method to get the matches of a Game Week
	ArrayList<GameWeek> getGameweekMatches(String url) {
		ArrayList<GameWeek> gameWeeks = new ArrayList<>();
		String data = getDataFromWebService(url);
		if (data != null) {
			try {
				JSONObject json = new JSONObject(data);
				Iterator<String> keys = json.keys();

				//Getting json from WS
				while (keys.hasNext()) {
					String id = keys.next();
					String gameId = json.getJSONObject(id).getString("game");
					String logoHome = json.getJSONObject(id).getString("home_logo");
					String logoAway = json.getJSONObject(id).getString("away_logo");
					String homeScore = json.getJSONObject(id).getString("home_team_score");
					String awayScore = json.getJSONObject(id).getString("away_team_score");
					String gameStatus = json.getJSONObject(id).getString("game_status");

					//Code to add from Json to Screen
					GameWeek gweek = new GameWeek(logoHome, logoAway, Integer.parseInt(gameId), Integer.parseInt(homeScore),
						Integer.parseInt(awayScore), Integer.parseInt(gameStatus));
					gameWeeks.add(gweek);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return gameWeeks;
		}
		return null;
	}

	//Method to get the Top5
	ArrayList<Top5> getDataForTop5(String url) {
		ArrayList<Top5> top5 = new ArrayList<>();
		String data = getDataFromWebService(url);
		if (data != null) {
			try {
				JSONObject json = new JSONObject(data);
				Iterator<String> keys = json.keys();

				//Getting json from WS
				while (keys.hasNext()) {
					String id = keys.next();
					String image = json.getJSONObject(id).getString("image");
					String name = json.getJSONObject(id).getString("surname");
					String pos = json.getJSONObject(id).getString("position");
					String rating = json.getJSONObject(id).getString("rating");

					//Code to add from Json to Screen
					Top5 newTop5 = new Top5(name, pos, image, Integer.parseInt(rating));
					top5.add(newTop5);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return top5;
		}
		return null;
	}

	//Method to get the data for the players of a finished match
	ArrayList<PlayerStats> getDataForFPlayers(String url) {
		ArrayList<PlayerStats> playerStats = new ArrayList<>();
		String data = getDataFromWebService(url);
		if (data != null) {
			try {
				JSONObject json = new JSONObject(data);
				Iterator<String> keys = json.keys();

				//Getting json from WS
				while (keys.hasNext()) {
					String id = keys.next();
					String logo = json.getJSONObject(id).getString("logo");
					String surname = json.getJSONObject(id).getString("surname");
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
					PlayerStats pStats = new PlayerStats(logo, surname, Double.parseDouble(avgRating), Double.parseDouble(avgFreethrowsIn),
						Double.parseDouble(avgFreethrowsOut), Double.parseDouble(avgTwoPointsIn), Double.parseDouble(avgTwoPointsOut),
						Double.parseDouble(avgThreePointsIn), Double.parseDouble(avgThreePointsOut), Double.parseDouble(avgTotalShots),
						Double.parseDouble(avgTotalRebounds), Double.parseDouble(avgOffensiveRebounds), Double.parseDouble(avgDefensiveRebounds),
						Double.parseDouble(avgBlocks), Double.parseDouble(avgAssists), Double.parseDouble(avgSteals),
						Double.parseDouble(avgTurnovers), Double.parseDouble(avgFouls));
					playerStats.add(pStats);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return playerStats;
		}
		return null;
	}

	//Method to get the data for the players of a finished match for the Tabbed View
	ArrayList<PlayerStats> getDataForFPlayersTabbed(String url) {
		ArrayList<PlayerStats> playerStats = new ArrayList<>();
		String data = getDataFromWebService(url);
		if (data != null) {
			try {
				JSONObject json = new JSONObject(data);
				Iterator<String> keys = json.keys();

				//Getting json from WS
				while (keys.hasNext()) {
					String id = keys.next();
					String logo = json.getJSONObject(id).getString("logo");
					String surname = json.getJSONObject(id).getString("surname");
					String rating = json.getJSONObject(id).getString("rating");
					String totalPoints = json.getJSONObject(id).getString("total_points");
					String twoPointsIn = json.getJSONObject(id).getString("perc_2_in");
					String threePointsIn = json.getJSONObject(id).getString("perc_3_in");
					String freethrowsIn = json.getJSONObject(id).getString("perc_freethrows_in");
					String totalRebounds = json.getJSONObject(id).getString("total_rebounds");
					String assists = json.getJSONObject(id).getString("total_assists");
					String blocks = json.getJSONObject(id).getString("total_blocks");
					String steals = json.getJSONObject(id).getString("total_steals");
					String turnovers = json.getJSONObject(id).getString("total_turnovers");
					String fouls = json.getJSONObject(id).getString("total_fouls");

					//Code to add from Json to Screen
					PlayerStats pStats = new PlayerStats(logo, surname, Integer.parseInt(totalPoints), Integer.parseInt(rating),
						Integer.parseInt(twoPointsIn), Integer.parseInt(threePointsIn), Integer.parseInt(freethrowsIn),
						Integer.parseInt(totalRebounds), Integer.parseInt(assists),	Integer.parseInt(blocks),
						Integer.parseInt(steals), Integer.parseInt(turnovers), Integer.parseInt(fouls));
					playerStats.add(pStats);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return playerStats;
		}
		return null;
	}

	//Method to get the data for the teams of a finished match
	ArrayList<TeamStats> getDataForFTeams(String url) {
		ArrayList<TeamStats> teamStats = new ArrayList<>();
		String data = getDataFromWebService(url);
		if (data != null) {
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
					TeamStats tStats = createTeamStats(logo, name, Double.parseDouble(total_points), Double.parseDouble(shots_made),
						Double.parseDouble(perc_2_in), Double.parseDouble(perc_3_in), Double.parseDouble(perc_freethrows_in),
						Double.parseDouble(total_rebounds), Double.parseDouble(total_offensive_rebounds), Double.parseDouble(total_defensive_rebounds),
						Double.parseDouble(total_assists), Double.parseDouble(total_blocks), Double.parseDouble(total_steals),
						Double.parseDouble(total_turnovers), Double.parseDouble(total_fouls));
					teamStats.add(tStats);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return teamStats;
		}
		return null;
	}

	//Method to get the data for the teams
	ArrayList<TeamStats> getDataForTeams(String url) {
		ArrayList<TeamStats> teamStats = new ArrayList<>();
		String data = getDataFromWebService(url);
		if (data != null) {
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
					TeamStats tStats = createTeamStats(logo, name, Double.parseDouble(total_points), Double.parseDouble(shots_made),
						Double.parseDouble(perc_2_in), Double.parseDouble(perc_3_in), Double.parseDouble(perc_freethrows_in),
						Double.parseDouble(total_rebounds), Double.parseDouble(total_offensive_rebounds), Double.parseDouble(total_defensive_rebounds),
						Double.parseDouble(total_assists), Double.parseDouble(total_blocks), Double.parseDouble(total_steals),
						Double.parseDouble(total_turnovers), Double.parseDouble(total_fouls));
					teamStats.add(tStats);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return teamStats;
		}
		return null;
	}

	//Method to create the team stats
	private TeamStats createTeamStats(String logo, String name, Double total_points, Double shots_made, Double perc_2_in, Double perc_3_in,
				Double perc_freethrows_in, Double total_rebounds, Double total_offensive_rebounds, Double total_defensive_rebounds,
				Double total_assists, Double total_blocks, Double total_steals, Double total_turnovers, Double total_fouls) {

		return new TeamStats(logo, name, total_points, shots_made, perc_2_in, perc_3_in, perc_freethrows_in, total_rebounds,
			total_offensive_rebounds, total_defensive_rebounds, total_assists, total_blocks, total_steals, total_turnovers, total_fouls);
	}

	//Method to get the finished matches score and team images
	Game getDataForFinishedMatchTeams(String url) {
		String data = getDataFromWebService(url);
		if (data != null) {
			try {
				JSONObject json = new JSONObject(data);
				Iterator<String> keys = json.keys();

				//Getting json from WS
				if (keys.hasNext()) {
					String homeTeamId = keys.next();
					String homeTeamLogo = json.getJSONObject(homeTeamId).getString("logo");
					String homeTeamScore = json.getJSONObject(homeTeamId).getString("total_score");

					String awayTeamId = keys.next();
					String awayTeamLogo = json.getJSONObject(awayTeamId).getString("logo");
					String awayTeamScore = json.getJSONObject(awayTeamId).getString("total_score");

					//Code to add from Json to Screen
					return new Game(Integer.parseInt(homeTeamId), homeTeamLogo, Integer.parseInt(homeTeamScore),
						Integer.parseInt(awayTeamId), awayTeamLogo, Integer.parseInt(awayTeamScore));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	//Method to get the players of a team
	ArrayList<Player> getTeamPlayers(String url) {
		ArrayList<Player> players = new ArrayList<>();
		String data = getDataFromWebService(url);
		if (data != null) {
			try {
				JSONObject json = new JSONObject(data);
				Iterator<String> keys = json.keys();

				//Getting json from WS

				while (keys.hasNext()) {
					String playerId = keys.next();
					String teamId = json.getJSONObject(playerId).getString("team_id");
					String surname = json.getJSONObject(playerId).getString("surname");

					//Code to add from Json to Screen
					players.add(new Player(surname, Integer.parseInt(playerId), Integer.parseInt(teamId)));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return players;
		}
		return null;
	}

	//Method to get the scores of a finished match
	Game getDataForFinishedScores(String url) {
		String data = getDataFromWebService(url);
		if (data != null) {
			try {
				JSONObject json = new JSONObject(data);
				Iterator<String> keys = json.keys();

				if (keys.hasNext()) {
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
		}
		return null;
	}

//	String getCurrentMinute(String url){
//		OkHttpClient client = new OkHttpClient().newBuilder().build();
//		String data = getDataFromWebService(url);
//		if(data != null) {
//			try {
//				JSONObject json = new JSONObject(data);
//				return json.getString("current_minute");
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}

	//Method to get the 3 newest events in a match
    public String[] getNewestEvents(String url) {
		String[] newestEvents = {"","",""};
		String data = getDataFromWebService(url);
		if(data != null) {
			try {
				JSONObject json = new JSONObject(data);
				Iterator<String> keys = json.keys();
				int index = 0;
				while (keys.hasNext()) {
					String uid = keys.next();
					String minute = json.getJSONObject(uid).getString("minute");
					String templateText = json.getJSONObject(uid).getString("template_text");
					String surname = json.getJSONObject(uid).getString("surname");
					String teamName = json.getJSONObject(uid).getString("team_name");
					String extraSurname = json.getJSONObject(uid).getString("extra_surname");
					String extraTeam = json.getJSONObject(uid).getString("extra_team");

					newestEvents[index] = formLogString(minute, templateText, surname, teamName, extraSurname, extraTeam);
					index++;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return newestEvents;
		}
		return null;
    }

	//Method to connect with a database through a web service and get the requested records
    private String getDataFromWebService(String url) {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
		Request request = new Request.Builder().url(url).method("POST", body).build();
		try {
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String formLogString(String minute, String templateText, String surname, String teamName, String extraSurname, String extraTeam) {
		String logString="";
		List<Integer> separatorIndexes = locateSeparators(templateText);

		if (separatorIndexes==null) return null;

		int occurrences = separatorIndexes.size();

		logString += minute + "': " + templateText.substring(0,separatorIndexes.get(0))
					+ surname + " (" + teamName + ")";

		switch (occurrences) {
			case 2:
				logString += templateText.substring(separatorIndexes.get(0)+1, separatorIndexes.get(1))
							+ extraSurname + " (" + extraTeam + ")" + templateText.substring(separatorIndexes.get(1)+1);
				break;
			case 1:
				logString += templateText.substring(separatorIndexes.get(0)+1);
				break;
			default:
				break;
		}

		return logString;
	}

	private List<Integer> locateSeparators(String templateText) {
		List<Integer> separatorIndexes = new ArrayList<>();
		int offset = 0;
		int index;
		if (!templateText.contains(",")) return null;
		while(true) {
			index = templateText.indexOf(',', offset);
			if (index==-1) break;
			separatorIndexes.add(index);
			offset = index+1;
		}

		return separatorIndexes;
	}
}
