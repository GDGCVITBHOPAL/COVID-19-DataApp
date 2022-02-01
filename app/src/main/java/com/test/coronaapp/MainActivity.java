package com.test.coronaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphTextView;

public class MainActivity extends AppCompatActivity {



    /* Declaring all the necessary variables which we will be using*/

    //    public static NeumorphCardView secBtn;
    public static TextView Data, Tcases, Tdeaths, Trecover;
    public String Cases, Death, Recover;

    public SwipeRefreshLayout refreshSwipe;

    ArrayList<CountryWiseListActivity.Countries> countries = new ArrayList<>();
    ListView myListView;
    CountryWiseListActivity.ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        myListView = findViewById(R.id.myListView);
        final SearchView mySearchView = findViewById(R.id.mySearchView);

        mySearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        countries = new CountryWiseListActivity.JSONDownloader(MainActivity.this).result(myListView);
        adapter = new CountryWiseListActivity.ListViewAdapter(this, countries);
        myListView.setAdapter(adapter);

        /*Intialising all the variables from the resource file*/

        refreshSwipe = findViewById(R.id.refreshSwipe);
//        buttonc = findViewById(R.id.buttonc);
        Tcases = findViewById(R.id.TCases);
        Tdeaths = findViewById(R.id.TDeaths);
        Trecover = findViewById(R.id.TRecover);
//        secBtn= findViewById(R.id.go);

        /*This Action leads you to the second activity where you can view the country wise list.*/
//        secBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CountryWiseListActivity.class);
//                startActivity(intent);
//
//            }
//        });

        loadingData();

        refreshSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshSwipe.setRefreshing(false);
                /* Fetching the data from the api */
                loadingData();
            }
        });


//        buttonc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//
//        });


    }

    private void loadingData() {

        final RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://coronavirus-19-api.herokuapp.com/all", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("myapp", "The response is" + response.getString("cases") + " " + response.getString("deaths"));
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp", "something went wrong");

            }
        });

        requestQueue.add(jsonObjectRequest);


    }

}
