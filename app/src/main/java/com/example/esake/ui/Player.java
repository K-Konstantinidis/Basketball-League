package com.example.esake.ui;

import java.util.ArrayList;

public class Player
{
	private String name;
	private int number;
	private Team playing_team;
	private PlayerPosition position;
	private ArrayList<PlayerStats> statistics;
	
	public Player() {
		this.name = null;
		this.number = -1;
		this.playing_team = null;
		this.position = null;
		this.statistics = new ArrayList<>();
	}
	
	public Player(String name, int number, Team playing_team, PlayerPosition position) {
		this.name = name;
		this.number = number;
		this.playing_team = playing_team;
		this.position = position;
		this.statistics = new ArrayList<>();
	}

	// Getters and setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Team getPlaying_team() {
		return playing_team;
	}

	public void setPlaying_team(Team playing_team) {
		this.playing_team = playing_team;
	}

	public PlayerPosition getPosition() {
		return position;
	}

	public void setPosition(PlayerPosition position) {
		this.position = position;
	}
	
	
	public void addStatistic(PlayerStats new_stats) {
		if(!this.statistics.contains(new_stats)) {
			this.statistics.add(new_stats);
		}
	}
}
