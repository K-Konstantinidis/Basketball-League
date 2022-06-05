package com.example.esake;

import java.util.ArrayList;

public class Team {
	private String name;
	private String code;
	private City city;
	private ArrayList<Player> players;

	// Constructors
	
	public Team() {
		this.name = null;
		this.code = null;
		this.city = null;
		this.players = new ArrayList<>();
	}
	
	public Team(String name, String code, City city) {
		this.name = name;
		this.code = code;
		this.city = city;
		this.players = new ArrayList<>();
	}
	
	// Getters and setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
	// Functions
	
	public boolean addPlayer(Player new_player) {
		if(this.players.contains(new_player)) {
			return false;
		}
		
		this.players.add(new_player);
		return true;
	}
	
}
