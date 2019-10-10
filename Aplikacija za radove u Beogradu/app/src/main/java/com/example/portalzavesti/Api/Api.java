package com.example.portalzavesti.Api;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by student on 27.3.2018..
 */

public class Api {

    public static void getJSON (String url, final ReadDataHandler rdh){
        AsyncTask<String,Void,String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String response = "";

                //treba nam http konekcija
                try {
                    URL link = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) link.openConnection();

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    //cita se red po red iz br
                    String red;
                    while ((red = br.readLine())!=null){
                        response += red + "\n";
                    }

                    br.close();
                    con.disconnect(); // ne mora se pisati - Android sam ubija konekciju

                } catch (Exception e){
                    response = "[]";
                }
                return response;
            }

            @Override
            protected void onPostExecute(String response) {
                rdh.setJson(response);
                rdh.sendEmptyMessage(0);
            }
        };

        task.execute(url);
    }

}
