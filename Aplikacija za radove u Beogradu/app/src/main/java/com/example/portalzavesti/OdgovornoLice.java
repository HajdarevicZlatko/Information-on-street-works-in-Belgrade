package com.example.portalzavesti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portalzavesti.Api.Api;
import com.example.portalzavesti.Api.ReadDataHandler;
import com.example.portalzavesti.model.User;
import com.example.portalzavesti.model.Vest;

import org.json.JSONObject;

public class OdgovornoLice extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odgovorno_lice);
        Bundle bundle=getIntent().getExtras();
        int id=bundle.getInt("userId");
        Api.getJSON("https://jsonplaceholder.typicode.com/users/"+id, new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();

                try {
                    // JSONArray array = new JSONArray(odgovor);
                    JSONObject obj=new JSONObject(odgovor);
                   User user = User.parseJSONObjectUser(obj);

                    TextView labelJson = (TextView) findViewById(R.id.ispisKvar);
                    labelJson.setText("");
                    labelJson.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ((TextView)findViewById(R.id.id_odgovornoLice)).setText("Ime: "+user.getName()+"\n" +
                            "email: "+user.getEmail()+"\n" +
                            "telefon: "+user.getPhone()+"\n" +
                            "website: "+user.getWebsite());
                    //((TextView)findViewById(R.id.opis_kvar)).setText("\n\nOpis\n\n"+vestKvar.getBody());



                } catch (Exception e) {

                }


            }
        });

        findViewById(R.id.imageView_odgovorno).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    switch(view.getId()){
        case R.id.imageView_odgovorno:

            Bundle bundle=getIntent().getExtras();
            Bundle bundle1=new Bundle();
            bundle1.putString("id",""+bundle.getInt("id"));
            Intent i=new Intent(this, KvaroviPojedinacno.class);
            i.putExtras(bundle1);
            //Toast.makeText(getApplicationContext(), ""+bundle.getInt("id"), Toast.LENGTH_LONG).show();
            startActivity(i);
            finish();

    }
    }
}
