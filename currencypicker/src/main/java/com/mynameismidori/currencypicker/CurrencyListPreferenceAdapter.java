package com.mynameismidori.currencypicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.List;


public class CurrencyListPreferenceAdapter extends ArrayAdapter {
    private Context context;
    private List<CurrencyItem> currencies;
    private int resource;

    LayoutInflater inflater;

    public CurrencyListPreferenceAdapter(Context context, int resource, List<CurrencyItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.currencies = objects;
        inflater = LayoutInflater.from(context);
    }


}
