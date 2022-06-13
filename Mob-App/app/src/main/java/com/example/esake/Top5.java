package com.example.esake;

public class Top5 {

	private String name;
	private String pos;
	private String logoPath;
	private int rating;
	private int points;

	//Constructor
	public Top5(String name, String pos, int rating, int points) {
		this.name = name;
		this.pos = pos;
		this.logoPath = logoPath;
		this.rating = rating;
		this.points = points;
	}

	//Getters
	public String getLogoPath() { return "http://"+myIP.getIp()+logoPath;};

	public String getName() { return name; }

	public String getPos() { return pos; }

	public String getRating() { return String.valueOf(rating); }

	public String getPoints() { return String.valueOf(points); }
}
