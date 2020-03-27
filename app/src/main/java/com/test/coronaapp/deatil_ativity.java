package com.test.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class deatil_ativity extends AppCompatActivity {
    public String cases;
    public String Death;
    public String Recover;
    public String TodayCases;
    public String TodayDeaths;
    public String url;
    public static TextView countrycases;
    public static TextView countrydeaths;
    public static TextView countryre;
    public static TextView tocases;
    public static TextView todeaths;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_deatil_ativity);
        countrycases = findViewById(R.id.CCases);
        countrydeaths = findViewById(R.id.CDeaths);
        countryre = findViewById(R.id.CRecover);
        tocases = findViewById(R.id.Tc);
        todeaths = findViewById(R.id.Td);

        getInIntent();


    }




    private void getInIntent()
    { if(getIntent().hasExtra("cname")){
        String coname = getIntent().getStringExtra("cname");
        setThing(coname);
    }

    }

    private void setThing(String coname)
    {
        TextView countryname = findViewById(R.id.CountryDatac);
        countryname.setText(coname);
       // url = "https://coronavirus-19-api.herokuapp.com/countries/"+coname;

        final RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(deatil_ativity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://coronavirus-19-api.herokuapp.com/countries/"+coname   , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("myapp", "The response is"+ response.getString("cases")+ " "+ response.getString("deaths"));
                    Death = response.getString("deaths");
                    cases = response.getString("cases");
                    Recover = response.getString("recovered");
                    TodayCases = response.getString("todayCases");
                    TodayDeaths = response.getString("todayDeaths");

                    countrycases.setText(cases);
                    countrydeaths.setText(Death);
                    countryre.setText(Recover);
                    tocases.setText(TodayCases);
                    todeaths.setText(TodayDeaths);
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
}
