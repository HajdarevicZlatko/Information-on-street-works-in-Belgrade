package com.example.portalzavesti;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portalzavesti.Api.Api;
import com.example.portalzavesti.Api.ReadDataHandler;
import com.example.portalzavesti.model.Vest;

import org.json.JSONArray;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFaculties();

}
    public void initFaculties (){
        Api.getJSON("https://jsonplaceholder.typicode.com/posts", new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();

                try {
                    JSONArray array = new JSONArray(odgovor);
                    LinkedList<Vest> vestLista = Vest.parseJSONArray(array);

                    TextView labelJson = (TextView) findViewById(R.id.ispis);
                    labelJson.setText("");
                    labelJson.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    LinearLayout layout=(LinearLayout) findViewById(R.id.ispis_layoit);

                    LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    for (Vest vest: vestLista){
                    RelativeLayout item=(RelativeLayout) inflater.inflate(R.layout.vest_view, null);
                    ((TextView) item.findViewById(R.id.title)).setText(vest.getTitle()+"\n\n");
                    ((TextView) item.findViewById(R.id.id)).setText(""+vest.getId());
                        item.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Toast.makeText(getApplicationContext(),((TextView)view.findViewById(R.id.id)).getText().toString(), Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),KvaroviPojedinacno.class);
                                Bundle bundle=new Bundle();
                                bundle.putString("id",((TextView)view.findViewById(R.id.id)).getText().toString());
                                intent.putExtras(bundle);
                                startActivity(intent);


                            }
                        });
                    layout.addView(item);
                        }


                } catch (Exception e) {

                }


            }
        });

        ((TextView) findViewById(R.id.ispis)).setText("Ucitava se...");
        findViewById(R.id.imageView_load_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),sacuvani_podaci.class);
                startActivity(intent);



            }
        });


    }






}
