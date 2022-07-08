package com.example.esake;

import java.io.Serializable;

public class Player implements Serializable {
	private String name;
	private int number;
	private int teamID;

	public Player(String name, int number, int teamId) {
		this.name = name;
		this.number = number;
		this.teamID = teamId;
	}

	//Getters
	public String getName() {return name;}
	public int getNumber() {return number;}
}
