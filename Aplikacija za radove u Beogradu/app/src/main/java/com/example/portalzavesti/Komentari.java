package com.example.portalzavesti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portalzavesti.Api.Api;
import com.example.portalzavesti.Api.ReadDataHandler;
import com.example.portalzavesti.model.Komentar;
import com.example.portalzavesti.model.User;
import com.example.portalzavesti.model.Vest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class Komentari extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komentari);
        Komentar komentar;
        Bundle bundle=getIntent().getExtras();

        Api.getJSON("https://jsonplaceholder.typicode.com/comments?postId="+bundle.getInt("postId"), new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();

                try {
                    JSONArray array = new JSONArray(odgovor);
                    LinkedList<Komentar> komentarLista = Komentar.parseJSONArray(array);

                    TextView labelJson = (TextView) findViewById(R.id.komentari_ucitava);
                    labelJson.setText("");
                    labelJson.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    LinearLayout layout=(LinearLayout) findViewById(R.id.komentari_layout);

                    LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    for (Komentar kom:komentarLista){
                        RelativeLayout item=(RelativeLayout) inflater.inflate(R.layout.komentarpojedinacno, null);
                        ((TextView) item.findViewById(R.id.komentar_email)).setText("Email: \n"+kom.getEmail());
                        ((TextView) item.findViewById(R.id.komentar_name)).setText("\nNaslov komentara:\n "+kom.getName());
                        ((TextView) item.findViewById(R.id.komentar_body)).setText("\nKomentar:\n"+kom.getBody());
                        layout.addView(item);
                    }


                } catch (Exception e) {

                }


            }
        });
        findViewById(R.id.imageView_backKomentari).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView_backKomentari:
                Bundle bundle=getIntent().getExtras();
                Bundle bundle1=new Bundle();
                Intent intent=new Intent(this, KvaroviPojedinacno.class);
                bundle1.putString("id",""+bundle.getInt("postId"));
                intent.putExtras(bundle1);
                startActivity(intent);
                finish();


        }

    }
}
