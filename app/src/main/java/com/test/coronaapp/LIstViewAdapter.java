package com.test.coronaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class LIstViewAdapter extends ArrayAdapter<Country> {

    private List<Country>countrylist;
    private Context mCtx;

    public LIstViewAdapter(List<Country> countrylist, Context mCtx) {
        super(mCtx, R.layout.list_items, countrylist);
        this.countrylist = countrylist;
        this.mCtx = mCtx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text views
        TextView textViewCountry = listViewItem.findViewById(R.id.textViewCountry);
        TextView textViewCases = listViewItem.findViewById(R.id.textViewCases);

        //Getting the hero for the specified position
        Country country = countrylist.get(position);

        //setting hero values to textviews
        textViewCountry.setText("CountryName:" + country.getCcountry());
        textViewCases.setText("Cases:"+ country.getCcases());

        return  listViewItem;

        //returning the listitem

    }
}
