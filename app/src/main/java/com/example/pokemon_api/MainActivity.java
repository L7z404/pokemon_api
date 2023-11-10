package com.example.pokemon_api;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    TextView data;
    TextView abilityname;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById(R.id.data);
        abilityname = findViewById(R.id.abilityname);
        url = "https://pokeapi.co/api/v2/pokemon/gulpin";

        //Creating a request to pull data from api and assign it to the textview
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String name = response.getString("name");
                    String exp = response.getString("base_experience");
                    //String abilityname = "";
                    String ability = response.getJSONArray("abilities").getJSONObject(0).getString("ability");
                    JSONObject test = new JSONObject(ability);
                    String testability = test.getString("name");

                    //JSONArray abilities = response.getJSONArray("abilities");
                    //for (int i = 0; i < abilities.length(); i++) {
                    //    JSONObject ability = abilities.getJSONObject(i);
                    //    abilityname = ability.getString("name");
                    //}
                    data.setText(name);
                    abilityname.setText(testability);


                }catch ( Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(request);
    }
}
