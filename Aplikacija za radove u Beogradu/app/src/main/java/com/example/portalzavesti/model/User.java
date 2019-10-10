package com.example.portalzavesti.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class User {
    int id;
    String name, email, phone, website;

    public User(int id, String name, String email, String phone, String website) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.website = website;
    }

    public User() {

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    public static User parseJSONObjectUser (JSONObject object){
        User user = new User();

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
            if (object.has("phone")){
                user.setPhone(object.getString("phone"));
            }
            if (object.has("website")){
                user.setWebsite(object.getString("website"));
            }



        } catch (Exception e){

        }
        return user;
    }

    //parsiranje niza objekata, koje se zapravo zasniva na iterativno parsiranju pojedinacnih objekata
    public static LinkedList<User> parseJSONArray (JSONArray array){
        LinkedList<User> list = new LinkedList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                User vest = parseJSONObjectUser(array.getJSONObject(i));
                list.add(vest);
            }
        } catch (Exception e){

        }
        return list;
    }
}
