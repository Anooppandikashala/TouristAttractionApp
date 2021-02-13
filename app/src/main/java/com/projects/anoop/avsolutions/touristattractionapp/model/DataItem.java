package com.projects.anoop.avsolutions.touristattractionapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataItem {
    int id;
    String name;
    String contact;
    String website;
    String description;
    String location;
    ArrayList<String> imageList;
    int rating;

    public String getImageAt(int i)
    {
        return imageList.get(i);
    }

    public ArrayList<String> getImageList(String jsonArray)
    {
        ArrayList<String> imageList = new ArrayList<>();

        imageList.add(jsonArray);

//        for (int i = 0; i < jsonArray.length(); i++) {
//            try {
//                JSONObject obj = jsonArray.getJSONObject(i);
//                System.out.println(obj.toString());
//                imageList.add(obj.toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }

        return imageList;
    }

    public ArrayList<String> getImageList(JSONArray jsonArray)
    {
        ArrayList<String> imageList = new ArrayList<>();


        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject obj = jsonArray.getJSONObject(i);
                System.out.println(obj.getString("url"));
                imageList.add(obj.getString("url"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return imageList;
    }

//    public ArrayList<String> getImageList(JSONObject jsonObject)
//    {
//        ArrayList<String> imageList = new ArrayList<>();
//
//        JSONArray jsonArray = jsonObject.ge
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            try {
//                JSONObject obj = jsonArray.getJSONObject(i);
//                System.out.println(obj.toString());
//                imageList.add(obj.toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return imageList;
//    }

    public DataItem(){}

    public DataItem(JSONObject object){
        try {
            this.id = object.getInt("id");
            this.name = object.getString("name");
            this.contact = object.getString("contact");
            this.website = object.getString("website");
            this.description = object.getString("desc");
            this.rating = object.getInt("rating");
            this.location  = object.getString("location");
            //System.out.println(object.getJSONArray("image_urls").toString());
            //this.imageList = getImageList(object.getString("image_urls"));
            this.imageList = getImageList(object.getJSONArray("image_urls"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList<DataItem> fromJson(JSONArray jsonObjects) {
        ArrayList<DataItem> dataItems = new ArrayList<DataItem>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                dataItems.add(new DataItem(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return dataItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
