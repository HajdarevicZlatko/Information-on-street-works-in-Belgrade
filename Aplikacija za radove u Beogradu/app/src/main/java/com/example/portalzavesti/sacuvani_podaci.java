package com.example.portalzavesti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portalzavesti.model.Vest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class sacuvani_podaci extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sacuvani_podaci);
        List<Vest> lista = new ArrayList<>();

        try {
            FileInputStream fileInputStream = openFileInput(Vest.APP_PREFIX);
            BufferedReader bf = new BufferedReader(new InputStreamReader(fileInputStream));

            for (String line = bf.readLine(); line != null; line = bf.readLine()) {
                //  Toast.makeText(getApplicationContext(), line, Toast.LENGTH_LONG).show();
               try{
                   Vest vest = new Vest();
                   String[] podaci = line.split("/");
                   vest.setTitle(podaci[0]);
                   vest.setBody(podaci[1]);
                   lista.add(vest);
                    //Toast.makeText(getApplicationContext(), line, Toast.LENGTH_LONG).show();
               } catch (Exception e){}



            }

            LinearLayout layout = (LinearLayout) findViewById(R.id.sacuvani_podaci_layout);

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            for (Vest vest1 : lista) {
                RelativeLayout item = (RelativeLayout) inflater.inflate(R.layout.sacuvanipodaciview, null);
                ((TextView) item.findViewById(R.id.sacuvanipodaciviewtitle)).setText("Naslov: \n"+vest1.getTitle() + "\n\n");
                ((TextView) item.findViewById(R.id.sacuvanipodacibody)).setText("Opis: \n"+vest1.getBody());

                layout.addView(item);
            }

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Ne postoje sacuvani podaci", Toast.LENGTH_LONG).show();
            nazad();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Problem u ispisu", Toast.LENGTH_LONG).show();
        }
        findViewById(R.id.imageView_sacuvaniPodaciBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.imageView_sacuvaniPodaciBack:
                nazad();
                break;

        }

    }
    private void nazad(){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
