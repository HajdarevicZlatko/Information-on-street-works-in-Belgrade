package com.example.portalzavesti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portalzavesti.Api.Api;
import com.example.portalzavesti.Api.ReadDataHandler;
import com.example.portalzavesti.model.Vest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class KvaroviPojedinacno extends AppCompatActivity implements View.OnClickListener{
    Vest vestKvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kvarovi_pojedinacno);
        Bundle bundle=getIntent().getExtras();
        String id=bundle.getString("id");
        Api.getJSON("https://jsonplaceholder.typicode.com/posts/"+id, new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();
                try {
                   // JSONArray array = new JSONArray(odgovor);
                    JSONObject obj=new JSONObject(odgovor);
                     vestKvar = Vest.parseJSONObject(obj);

                    TextView labelJson = (TextView) findViewById(R.id.ispisKvar);
                    labelJson.setText("");
                   labelJson.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ((TextView)findViewById(R.id.id_kvar)).setText("Ulica\n\n"+vestKvar.getTitle());
                    ((TextView)findViewById(R.id.opis_kvar)).setText("\n\nOpis\n\n"+vestKvar.getBody());



                } catch (Exception e) {

                }


            }
        });
        ((TextView) findViewById(R.id.id_kvar)).setText("Ucitava se...");
        findViewById(R.id.imageView_backKvar).setOnClickListener(this);
        findViewById(R.id.odgovornoLice_button).setOnClickListener(this);
        findViewById(R.id.button_komentari).setOnClickListener(this);
        findViewById(R.id.imageView_kvarovi_sacuvaj).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView_backKvar:
                Intent intent1=new Intent(this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.odgovornoLice_button:
                Intent intent2=new Intent(this, OdgovornoLice.class);
                //Toast.makeText(getApplicationContext(), ""+vestKvar.getUserId(), Toast.LENGTH_LONG).show();
                Bundle bundle=new Bundle();
                bundle.putInt("userId", vestKvar.getUserId());
                bundle.putInt("id", vestKvar.getId());
                intent2.putExtras(bundle);
                startActivity(intent2);
                finish();
                break;
            case R.id.button_komentari:
                Intent intent3=new Intent(this, Komentari.class);
                //Toast.makeText(getApplicationContext(), ""+vestKvar.getUserId(), Toast.LENGTH_LONG).show();
                Bundle bundle1=new Bundle();
                bundle1.putInt("postId", vestKvar.getId());
                intent3.putExtras(bundle1);
                startActivity(intent3);
                finish();
                break;
            case R.id.imageView_kvarovi_sacuvaj:
                try {
                    FileOutputStream fos=openFileOutput(Vest.APP_PREFIX, Context.MODE_APPEND);
                    PrintWriter pw=new PrintWriter(fos);
                    String line=vestKvar.getTitle()+"/"+vestKvar.getBody().replace("\n","");
                    pw.println(line);
                   // Toast.makeText(getApplicationContext(),vestKvar.getTitle()+"/"+vestKvar.getBody(), Toast.LENGTH_LONG).show();
                    pw.flush();
                    pw.close();
                    fos.close();


                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),"Nema dovoljno memorije", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"Input problem", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(),"Kvar sacuvan", Toast.LENGTH_LONG).show();
                ((ImageView) findViewById(R.id.imageView_kvarovi_sacuvaj)).setImageResource(R.drawable.done);


        }

    }
}
