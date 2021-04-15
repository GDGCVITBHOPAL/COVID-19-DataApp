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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

   /* Declaring all the necessary variables which we will be using*/

    public static Button buttonc;
    public static Button secBtn;
    public static TextView Data;
    public static TextView Tcases;
    public static TextView Tdeaths;
    public static TextView Trecover;
   public String Cases;
    public String Death;
    public String Recover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intialising all the variables from the resource file*/

        buttonc = (Button)findViewById(R.id.buttonc);
        Data = (TextView) findViewById(R.id.Datac);
        Tcases = (TextView)findViewById(R.id.TCases);
        Tdeaths = (TextView)findViewById(R.id.TDeaths);
        Trecover = (TextView)findViewById(R.id.TRecover);
        secBtn= (Button)findViewById(R.id.go);

        /*This Action leads you to the second activity where you can view the country wise list.*/
        secBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CountryWiseListActivity.class);
                startActivity(intent);

            }
        });

        final RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        buttonc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


           /* Fetching the data from the api */
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://coronavirus-19-api.herokuapp.com/all", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("myapp", "The response is"+ response.getString("cases")+ " "+ response.getString("deaths"));
                    Death = response.getString("deaths");
                    Cases = response.getString("cases");
                    Recover = response.getString("recovered");
                    Tcases.setText(Cases);
                    Tdeaths.setText(Death);
                    Trecover.setText(Recover);
                      } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        { @Override
        public void onErrorResponse(VolleyError error){
            Log.d("myapp", "something went wrong");

        }
        });

        requestQueue.add(jsonObjectRequest);
    }

});



    }

}
