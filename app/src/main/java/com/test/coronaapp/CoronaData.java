package com.test.coronaapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CoronaData extends AsyncTask<Void, Void, String> {
    String data = "";
    String dataparsed="";
    String singleparsed="";
    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL("https://coronavirus-19-api.herokuapp.com/all");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream  inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for(int i=0; i<JA.length();i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                singleparsed = "Total Cases: "+ JO.get("cases")+ "\n"+
                               "Total Deaths: "+ JO.get("deaths")+ "\n" +
                              "Total Recovered Cases: "+ JO.get("recovered")+ "\n";

                dataparsed = "HEllo" + "\n" + singleparsed + dataparsed;

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
          e.printStackTrace();
       }

        return (dataparsed);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.Data.setText(this.dataparsed);
       // Toast.makeText(this, "Checking", Toast.LENGTH_SHORT).show();
    }
}
