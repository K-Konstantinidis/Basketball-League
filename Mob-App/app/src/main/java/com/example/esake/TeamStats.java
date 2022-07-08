package com.example.esake;

public class TeamStats {

	private String logo, name;
	private double total_points;
	private double shots_made;
	private double perc_2_in;
	private double perc_3_in;
	private double perc_freethrows_in;
	private double total_rebounds;
	private double total_offensive_rebounds;
	private double total_defensive_rebounds;
	private double total_assists;
	private double total_blocks;
	private double total_steals;
	private double total_turnovers;
	private double total_fouls;

	//Constructor
	public TeamStats(String logo, String name, double total_points, double shots_made, double perc_2_in, double perc_3_in,
					 double perc_freethrows_in, double total_rebounds, double total_offensive_rebounds,
					 double total_defensive_rebounds, double total_assists, double total_blocks,
					 double total_steals, double total_turnovers, double total_fouls) {
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
	public String getLogo() {return "http://"+ MyIP.getIp()+logo;}
	public String getName() {return name;}
	public String getTotal_points() {return String.valueOf(total_points);}
	public String getPerc_2_in() {return String.valueOf(perc_2_in);}
	public String getPerc_3_in() {return String.valueOf(perc_3_in);}
	public String getPerc_freethrows_in() {return String.valueOf(perc_freethrows_in);}
	public String getTotal_rebounds() {return String.valueOf(total_rebounds);}
	public String getTotal_assists() {return String.valueOf(total_assists);}
	public String getTotal_blocks() {return String.valueOf(total_blocks);}
	public String getTotal_steals() {return String.valueOf(total_steals);}
	public String getTotal_turnovers() {return String.valueOf(total_turnovers);}
	public String getTotal_fouls() {return String.valueOf(total_fouls);}

	public String getIntTotal_points() {return String.valueOf((int) total_points);}
	public String getIntShots_made() {return String.valueOf((int) shots_made);}
	public String getIntTotal_rebounds() {return String.valueOf((int) total_rebounds);}
	public String getIntTotal_offensive_rebounds() {return String.valueOf((int) total_offensive_rebounds);}
	public String getIntTotal_defensive_rebounds() {return String.valueOf((int) total_defensive_rebounds);}
	public String getIntTotal_assists() {return String.valueOf((int) total_assists);}
	public String getIntTotal_blocks() {return String.valueOf((int) total_blocks);}
	public String getIntTotal_steals() {return String.valueOf((int) total_steals);}
	public String getIntTotal_turnovers() {return String.valueOf((int) total_turnovers);}
	public String getIntTotal_fouls() {return String.valueOf((int) total_fouls);}
}
