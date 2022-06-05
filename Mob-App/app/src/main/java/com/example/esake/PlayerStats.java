package com.example.esake;

public class PlayerStats {
	// Points
	private int freethrows_in;
	private int freethrows_out;
	
	private int two_points_in;
	private int two_points_out;
	
	private int three_points_in;
	private int three_points_out;
	
	// Miscellaneous points
	private int offensive_rebounds;
	private int defensive_rebounds;
	private int assists;
	private int blocks;
	private int steals;
	private int turnovers;
	private int fouls;
	
	private Game played_game;
	
	public PlayerStats(Game played_game) {
		this.played_game = played_game;
	}

	// Point adding functions
	public void addFreeThrowIn() 	{ ++freethrows_in; }
	public void addFreeThrowOut()	{ ++freethrows_out; }
	
	public void addTwoPointIn()		{ ++two_points_in; }
	public void addTwoPointOut()	{ ++two_points_out; }
	
	public void addThreePointIn()	{ ++three_points_in; }
	public void addThreePointOut()	{ ++three_points_out; }
	
	public void addOffensiveRebound() { ++offensive_rebounds; }
	public void addDefensiveRebound() { ++defensive_rebounds; }
	
	public void addAssist()		{ ++assists; }
	public void addBlock()		{ ++blocks; }
	public void addSteal()		{ ++steals; }
	public void addTurnover()	{ ++turnovers; }
	public void addFoul()		{ ++fouls; }
	
	// Point removing functions, used for corrections.
	// They return false if the player already has 0 points.
	public boolean rmFreeThrowIn() {
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
	
	public int getRating() {
		int tmp_rating;
		
		tmp_rating = (getPoints() + getRebounds() + assists + steals + blocks) -
					 (two_points_out + three_points_out + freethrows_out + turnovers + fouls);
		
		return (tmp_rating > 0) ? tmp_rating : 0;
	}
}
