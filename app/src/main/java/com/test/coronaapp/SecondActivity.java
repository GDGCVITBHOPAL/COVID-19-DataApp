package com.test.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
private TextView mTextViewResult;
private Button mButton;
private RequestQueue mQuee;
private ListView mList;
private SearchView mSearch;
   EditText editText;

//ArrayList<String> list;
LIstViewAdapter adapter;
//ArrayAdapter<String> adapter;
  // List mList;
    List<Country>countrylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTextViewResult = findViewById(R.id.Text_View_Rsult);
        mButton = findViewById(R.id.btn_Check);
        mList = findViewById(R.id.CuntryList);
        countrylist = new ArrayList<>();
       // mSearch = findViewById(R.id.SearchV);
        mList.setTextFilterEnabled(true);
        editText = findViewById(R.id.edittext1);


        mQuee = Volley.newRequestQueue(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();


                editText.addTextChangedListener(new TextWatcher() {

                    public void afterTextChanged(Editable s) {
                    }

                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    public void onTextChanged(CharSequence stringVar, int start, int before, int count) {

                        adapter.getFilter().filter(stringVar.toString());
                    }
                });
            }
        });


    }


    private void jsonParse()
    {
         String url =  "https://coronavirus-19-api.herokuapp.com/countries";
        JsonArrayRequest request =new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                          //  JSONArray jsonArray= new JSONArray("");//response.getJSONArray();
                            for (int i =0;i<response.length();i++)
                            {
                                JSONObject count=  response.getJSONObject(i);
                                Country country = new Country(count.getString("country").toString(), count.getString("cases"));
                                countrylist.add(country);

                                //String Cuntry =   count.getString("country");
                                //String Cases = count.getString("cases");

                               // list = new ArrayList<>();
                               // list.add(Cuntry);

                               // list.add(Cuntry+ "," + Cases);
                               // adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

                               // mTextViewResult.append(Cuntry + "," + Cases);
                                //mTextViewResult.append("\n\n");

                            }
                             adapter = new LIstViewAdapter(countrylist, getApplicationContext());
                            mList.setAdapter(adapter);

                           /* mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String query) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    adapter.getFilter().filter(newText);
                                    return false;
                                }
                            });*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();



            }
        });

        mQuee.add(request);
       // adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        //mList.setAdapter(adapter);
    }



}
