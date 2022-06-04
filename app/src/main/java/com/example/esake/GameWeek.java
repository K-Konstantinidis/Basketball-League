package com.example.esake;

import java.util.ArrayList;
import java.util.List;

public class GameWeek {

    ArrayList<CarBrand> cbList = new ArrayList<CarBrand>();

    public GameWeek(String ip) {
        String url= "http://"+ip+"/multimediaDBServices/populateDropDown.php";

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            cbList = okHttpHandler.populateDropDown(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllBrands() {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<cbList.size(); i++) {
            temp.add(cbList.get(i).getName());
        }
        return temp;
    }

    public List<String> getAllModels(String b) {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<cbList.size(); i++) {
            if (cbList.get(i).hasName(b)) {
                temp = cbList.get(i).getAllModels();
            }
        }
        return temp;
    }

}
