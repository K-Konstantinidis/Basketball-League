package com.example.esake;

public class TeamStats {

	private String logo;
	private String name;
	private int total_points;
	private int shots_made;
	private double perc_2_in;
	private double perc_3_in;
	private double perc_freethrows_in;
	private int total_rebounds;
	private int total_offensive_rebounds;
	private int total_defensive_rebounds;
	private int total_assists;
	private int total_blocks;
	private int total_steals;
	private int total_turnovers;
	private int total_fouls;

	//Constructor
	public TeamStats(String logo, String name, int total_points, int shots_made, double perc_2_in, double perc_3_in,
					 double perc_freethrows_in, int total_rebounds, int total_offensive_rebounds,
					 int total_defensive_rebounds, int total_assists, int total_blocks,
					 int total_steals, int total_turnovers, int total_fouls) {
		this.logo = logo;
		this.name = name;
		this.total_points = total_points;
		this.shots_made = shots_made;
		this.perc_2_in = perc_2_in;
		this.perc_3_in = perc_3_in;
		this.perc_freethrows_in = perc_freethrows_in;
		this.total_rebounds = total_rebounds;
		this.total_offensive_rebounds = total_offensive_rebounds;
		this.total_defensive_rebounds = total_defensive_rebounds;
		this.total_assists = total_assists;
		this.total_blocks = total_blocks;
		this.total_steals = total_steals;
		this.total_turnovers = total_turnovers;
		this.total_fouls = total_fouls;
	}

	//Getters
	public String getLogo() {return "http://"+myIP.getIp()+logo;}


	public String getName() {return name;}
	public String getTotal_points() {return String.valueOf(total_points);}
	public String getShots_made() {return String.valueOf(shots_made);}
	public String getPerc_2_in() {return String.valueOf(perc_2_in);}
	public String getPerc_3_in() {return String.valueOf(perc_3_in);}
	public String getPerc_freethrows_in() {return String.valueOf(perc_freethrows_in);}
	public String getTotal_rebounds() {return String.valueOf(total_rebounds);}
	public String getTotal_offensive_rebounds() {return String.valueOf(total_offensive_rebounds);}
	public String getTotal_defensive_rebounds() {return String.valueOf(total_defensive_rebounds);}
	public String getTotal_assists() {return String.valueOf(total_assists);}
	public String getTotal_blocks() {return String.valueOf(total_blocks);}
	public String getTotal_steals() {return String.valueOf(total_steals);}
	public String getTotal_turnovers() {return String.valueOf(total_turnovers);}
	public String getTotal_fouls() {return String.valueOf(total_fouls);}
}
