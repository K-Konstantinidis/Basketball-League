package com.example.esake;

public class PlayerStats {
	// Points
/*	private int freethrows_in;
	private int freethrows_out;

	private int two_points_in;
	private int two_points_out;

	private int three_points_in;
	private int three_points_out;*/
	
	// Miscellaneous points
/*	private int offensive_rebounds;
	private int defensive_rebounds;*/
/*	private int assists;
	private int blocks;
	private int steals;
	private int turnovers;
	private int fouls;*/
	
	private Game played_game;

	private String logo;
	private String surname;
	private int total_points;
	private int rating;
	private int shots_made;
	private int perc_2_in;
	private int perc_3_in;
	private int perc_freethrows_in;
	private int total_rebounds;
	private int total_assists;
	private int total_blocks;
	private int total_steals;
	private int total_turnovers;
	private int total_fouls;

	//first Constructor
	public PlayerStats(Game played_game) {
		this.played_game = played_game;
	}

	public PlayerStats(String logo, String surname, int total_points, int rating, int shots_made,
					   int perc_2_in, int perc_3_in, int perc_freethrows_in, int total_rebounds, int total_assists,
					   int total_blocks, int total_steals, int total_turnovers, int total_fouls) {
		this.logo = logo;
		this.surname = surname;
		this.total_points = total_points;
		this.rating = rating;
		this.shots_made = shots_made;
		this.perc_2_in = perc_2_in;
		this.perc_3_in = perc_3_in;
		this.perc_freethrows_in = perc_freethrows_in;
		this.total_rebounds = total_rebounds;
		this.total_assists = total_assists;
		this.total_blocks = total_blocks;
		this.total_steals = total_steals;
		this.total_turnovers = total_turnovers;
		this.total_fouls = total_fouls;
	}

	//Getters for Data
	public String getLogo() {return "http://"+myIP.getIp()+logo;}

	public String getSurname() {return surname;}
	public String getTotal_points() {return String.valueOf(total_points);}
	public String getPRating() {return String.valueOf(rating);}
	public String getShots_made() {return String.valueOf(shots_made);}
	public String getPerc_2_in() {return String.valueOf(perc_2_in);}
	public String getPerc_3_in() {return String.valueOf(perc_3_in);}
	public String getPerc_freethrows_in() {return String.valueOf(perc_freethrows_in);}
	public String getTotal_rebounds() {return String.valueOf(total_rebounds);}
	public String getTotal_assists() {return String.valueOf(total_assists);}
	public String getTotal_blocks() {return String.valueOf(total_blocks);}
	public String getTotal_steals() {return String.valueOf(total_steals);}
	public String getTotal_turnovers() {return String.valueOf(total_turnovers);}
	public String getTotal_fouls() {return String.valueOf(total_fouls);}

	// Point adding functions
/*	public void addFreeThrowIn() 	{ ++freethrows_in; }
	public void addFreeThrowOut()	{ ++freethrows_out; }

	public void addTwoPointIn()		{ ++two_points_in; }
	public void addTwoPointOut()	{ ++two_points_out; }

	public void addThreePointIn()	{ ++three_points_in; }
	public void addThreePointOut()	{ ++three_points_out; }

	public void addOffensiveRebound() { ++offensive_rebounds; }
	public void addDefensiveRebound() { ++defensive_rebounds; }*/
	
	/*public void addAssist()		{ ++assists; }
	public void addBlock()		{ ++blocks; }
	public void addSteal()		{ ++steals; }
	public void addTurnover()	{ ++turnovers; }
	public void addFoul()		{ ++fouls; }*/
	
	// Point removing functions, used for corrections.
	// They return false if the player already has 0 points.
	/*public boolean rmFreeThrowIn() {
		if(freethrows_in > 0) {
			--freethrows_in;
			return true;
		}
		return false;
	}
	
	public boolean rmFreeThrowOut() {
		if(freethrows_out > 0) {
			--freethrows_out;
			return true;
		}
		return false;
	}
	
	public boolean rmTwoPointIn() {
		if(two_points_in > 0) {
			--two_points_in;
			return true;
		}
		return false;
	}
	
	public boolean rmTwoPointOut() {
		if(two_points_out > 0) {
			--two_points_out;
			return true;
		}
		return false;
	}
	
	
	public boolean rmThreePointIn() {
		if(three_points_in > 0) {
			--three_points_in;
			return true;
		}
		return false;
	}
	
	public boolean rmThreePointOut() {
		if(three_points_out > 0) {
			--three_points_out;
			return true;
		}
		return false;
	}
	
	public boolean rmOffensiveRebound() {
		if(offensive_rebounds > 0) {
			--offensive_rebounds;
			return true;
		}
		return false;
	}
	
	public boolean rmDefensiveRebound() {
		if(defensive_rebounds > 0) {
			--defensive_rebounds;
			return true;
		}
		return false;
	}
	
	public boolean rmAssist() {
		if(assists > 0) {
			--assists;
			return true;
		}
		return false;
	}
	
	public boolean rmBlock() {
		if(blocks > 0) {
			--blocks;
			return true;
		}
		return false;
	}
	
	public boolean rmSteal() {
		if(steals > 0) {
			--steals;
			return true;
		}
		return false;
	}
	
	public boolean rmTurnover() {
		if(turnovers > 0) {
			--turnovers;
			return true;
		}
		return false;
	}
	
	public boolean rmFoul() {
		if(fouls > 0) {
			--fouls;
			return true;
		}
		return false;
	}
	
	public int getPoints() {
		return two_points_in + three_points_in + freethrows_in;
	}
	
	public int getRebounds() {
		return offensive_rebounds + defensive_rebounds;
	}

	//Ftiaxte me kapou ta percentages pvw ta vriskei
	//Gia na ta pername sthn PlayerStatsFinishedAdapter.java
	//Ayto paizei na einai teleiow lathos apla to evala gia na mhn
	//me petaei error oti den exv perasei kati
	public int getPlayerPercentFg() {
		return (perc_2_in + perc_3_in + perc_freethrows_in) / 3;
	}*/



	/*
	public String get3ptsin() {return String.valueOf(three_points_in);}
*/
}
