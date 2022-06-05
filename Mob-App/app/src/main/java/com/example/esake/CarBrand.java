package com.example.esake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarBrand {
    private String name;
    private List<String> models = new ArrayList<String>();

    public CarBrand(String b, String m) {
        name = b;
        models = Arrays.asList(m.split(","));
    }

    public boolean hasName(String b) {
        return name.equals(b);
    }

    public String getAllModelsAsString() {
        return models.toString();
    }

    public String getName() {return name; }
    public List<String> getAllModels() {return models;}


    //image Code, delete above if you add images

//    private String name;
//    private List<String> models = new ArrayList<String>();
//    private HashMap<String, Media> media = new HashMap<>();
//
//    public CarBrand(String brand, String models, String images) {
//        name = brand;
//        this.models = Arrays.asList(models.split(","));
//        String[] imageArray = images.split(",");
//        for (int i=0; i<this.models.size(); i++) {
//            media.put(this.models.get(i),new Media(imageArray[i]));
//        }
//    }
//
//    public Media getMedia(String key){
//        return this.media.get(key);
//    }
//    public boolean hasName(String b) {
//        return name.equals(b);
//    }
//    public String getName() {return name; }
//    public List<String> getAllModels() {return models;}
}
