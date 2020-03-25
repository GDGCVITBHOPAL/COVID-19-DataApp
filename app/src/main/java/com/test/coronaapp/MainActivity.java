package com.test.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static Button buttonc;
    public static Button gosc;
   // public static Button Refresh;
    public static TextView Data;
    public static TextView Tcases;
    public static TextView Tdeaths;
    public static TextView trecover;
   public String Cases;
    public String death;
    public String recover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://coronavirus-19-api.herokuapp.com/all", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("myapp", "The response is"+ response.getString("cases")+ " "+ response.getString("deaths"));
                    death = response.getString("deaths");
                    Cases = response.getString("cases");
                    Tcases.setText(Cases);
                    Data.setText(death);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
            { @Override
                public void onErrorResponse(VolleyError error){
                Log.d("myapp", "something went wrond");

            }
        });

        requestQueue.add(jsonObjectRequest);*/

        buttonc = (Button)findViewById(R.id.buttonc);
       // Refresh = (Button)findViewById(R.id.refresh);
        Data = (TextView) findViewById(R.id.Datac);
        Tcases = (TextView)findViewById(R.id.TCases);
        Tdeaths = (TextView)findViewById(R.id.TDeaths);
        trecover = (TextView)findViewById(R.id.TRecover);
        gosc = (Button)findViewById(R.id.go);


        gosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LastTry.class);
                startActivity(intent);

            }
        });

        final RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

buttonc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://coronavirus-19-api.herokuapp.com/all", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("myapp", "The response is"+ response.getString("cases")+ " "+ response.getString("deaths"));
                    death = response.getString("deaths");
                    Cases = response.getString("cases");
                    recover = response.getString("recovered");
                    Tcases.setText(Cases);
                    Tdeaths.setText(death);
                    trecover.setText(recover);
                   // Data.setText(death);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        { @Override
        public void onErrorResponse(VolleyError error){
            Log.d("myapp", "something went wrond");

        }
        });

        requestQueue.add(jsonObjectRequest);
    }

});


    /* buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            CoronaData process = new CoronaData();
            process.execute();
            }
        });*/
    }

}
