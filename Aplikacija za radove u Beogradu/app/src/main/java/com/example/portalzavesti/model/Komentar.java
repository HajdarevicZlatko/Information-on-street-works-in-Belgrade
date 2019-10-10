package com.example.portalzavesti.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class Komentar {
    int id;
    String name, email, body;

    public Komentar() {

    }

    public Komentar(int id, String name, String email, String body) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static Komentar parseJSONObjectKomentar (JSONObject object){
        Komentar user = new Komentar();

        try {
            if (object.has("name")) {
                user.setName(object.getString("name"));
            }

            if (object.has("email")){
                user.setEmail(object.getString("email"));
            }
            if (object.has("id")){
                user.setId(object.getInt("id"));
            }
            if (object.has("body")){
                user.setBody(object.getString("body"));
            }




        } catch (Exception e){

        }
        return user;
    }

    //parsiranje niza objekata, koje se zapravo zasniva na iterativno parsiranju pojedinacnih objekata
    public static LinkedList<Komentar> parseJSONArray (JSONArray array){
        LinkedList<Komentar> list = new LinkedList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                Komentar vest = parseJSONObjectKomentar(array.getJSONObject(i));
                list.add(vest);
            }
        } catch (Exception e){

        }
        return list;
    }

}
