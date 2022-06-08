package com.example.esake;

import java.util.ArrayList;

public class Connector {

	private ArrayList<Day> weeks = new ArrayList<>();

	public Connector(String ip){
		String url= "http://"+ip+"/ws/populateRounds.php";
		try {
			OkHttpHandler okHttpHandler = new OkHttpHandler();
			weeks = okHttpHandler.getGameWeeks(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getWeeks() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i=0; i<weeks.size(); i++) {
			temp.add(weeks.get(i).getGameweek());
		}
		return temp;
	}
}
