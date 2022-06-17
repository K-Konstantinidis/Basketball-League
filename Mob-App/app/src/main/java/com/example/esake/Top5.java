package com.example.esake;

public class Top5 {

	private String name;
	private String pos;
	private String image;
	private int rating;

	//Constructor
	public Top5(String name, String pos, String image, int rating) {
		this.name = name;
		this.pos = pos;
		this.image = image;
		this.rating = rating;
	}

	//Getters
	public String getLogoPath() { return "http://"+myIP.getIp()+image;};

	public String getName() { return name; }

	public String getPos() { return pos; }

	public String getRating() { return String.valueOf(rating); }
}
