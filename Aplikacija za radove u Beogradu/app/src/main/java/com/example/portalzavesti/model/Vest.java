package com.example.portalzavesti.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class Vest {
    public static String APP_PREFIX="VESTKEY3";
    public static String APP_DATA="VESTKEYNAME";
    private String title, body;
    private int id, userId;

    public Vest() {

    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vest(String title, String body, int id) {
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public Vest(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    //parsiranje jednog objekta
    public static Vest parseJSONObject (JSONObject object){
        Vest vest = new Vest();

        try {
            if (object.has("title")) {
                vest.setTitle(object.getString("title"));
            }

            if (object.has("body")){
                vest.setBody(object.getString("body"));
            }
            if (object.has("id")){
                vest.setId(object.getInt("id"));
            }
            if (object.has("userId")){
                vest.setUserId(object.getInt("userId"));
            }



        } catch (Exception e){

        }
        return vest;
    }

    //parsiranje niza objekata, koje se zapravo zasniva na iterativno parsiranju pojedinacnih objekata
    public static LinkedList<Vest> parseJSONArray (JSONArray array){
        LinkedList<Vest> list = new LinkedList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                Vest vest = parseJSONObject(array.getJSONObject(i));
                list.add(vest);
            }
        } catch (Exception e){

        }
        return list;
    }

}
