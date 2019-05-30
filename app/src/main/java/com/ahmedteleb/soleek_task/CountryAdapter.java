package com.ahmedteleb.soleek_task;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryAdapter extends BaseAdapter
{

    private ArrayList<Country> Countries;

    public CountryAdapter(ArrayList<Country> countries) {
        Countries = countries;
    }


    @Override
    public int getCount() {
        return Countries.size();
    }

    @Override
    public Country getItem(int i) {
        return Countries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_item, null);

        TextView country_tv = view.findViewById(R.id.country_tv);

        Country currentCountry = getItem(i);

        country_tv.setText(currentCountry.getCountryName());

        return view;
    }
}
