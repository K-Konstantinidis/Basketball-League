package com.example.esake;

import java.util.ArrayList;

public class GameWeek {

	ArrayList<Championship> matchList = new ArrayList<>();

	public GameWeek(String ip) {
		String url= "http://"+ip+"/Scores/getGameweekMatches.php?round_id=2";
		//String url= "http://"+ip+"/multimediaDBServices/getMedia.php"; image code

		try {
			OkHttpHandler okHttpHandler = new OkHttpHandler();
			matchList = okHttpHandler.populateDropDown(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMatchList(int matchIndex, boolean isHomeTeam){
		return this.matchList.get(matchIndex).getTeamScore(isHomeTeam);
	}

//    public List<String> getAllBrands() {
//        List<String> temp = new ArrayList<String>();
//        for (int i=0; i<cbList.size(); i++) {
//            temp.add(cbList.get(i).getName());
//        }
//        return temp;
//    }

	//image code
//    public List<String> getAllModels(String b) {
//        List<String> temp = new ArrayList<String>();
//        for (int i=0; i<cbList.size(); i++) {
//            if (cbList.get(i).hasName(b)) {
//                temp = cbList.get(i).getAllModels();
//            }
//        }
//        return temp;
//    }

	//use this function if you need to get a specific image
//    public Media lookup(String brand, String model) {
//        for (int i = 0; i < cbList.size(); i++) {
//            if (cbList.get(i).hasName(brand)) {
//                return cbList.get(i).getMedia(model);
//            }
//
//        }
//        return null;
//    }

}