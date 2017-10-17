package com.mynameismidori.currencypicker;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CurrencyPreference extends ListPreference implements SharedPreferences.OnSharedPreferenceChangeListener {

    private EditText searchEditText;
    private ListView currencyListView;

    private CharSequence[] currencyName;
    private CharSequence[] currencyCode;
    private CurrencyListAdapter adapter;
    private int currentIndex = 0;

    private List<ExtendedCurrency> currenciesList = new ArrayList<>();
    private List<ExtendedCurrency> selectedCurrenciesList = new ArrayList<>();

    private Resources resources;
    private String selectedCurrencyCode, defaultCurrencyCode;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public CurrencyPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        //setDialogLayoutResource(R.layout.currency_picker);

        preferences = PreferenceManager.getDefaultSharedPreferences(context);

        setCurrenciesList(ExtendedCurrency.getAllCurrencies());

        editor = preferences.edit();

        setSummary(preferences.getString("selectedCurrency", "CZK"));


        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.attrs_currency, 0, 0);
        try {
            defaultCurrencyCode = a.getString(R.styleable.attrs_currency_currencyCode);
        } finally {
            a.recycle();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("selectedCurrency")){
            setSummary(sharedPreferences.getString("selectedCurrency", "CZK"));
        }
    }

    @Override
    protected View onCreateDialogView() {
        return super.onCreateDialogView();
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.currency_picker, null);

        searchEditText = (EditText) view.findViewById(R.id.currency_code_picker_search);
        currencyListView = (ListView) view.findViewById(R.id.currency_code_picker_listview);

        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                search(s.toString());
            }
        });

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);
        ListView currencyListView = (ListView) view.findViewById(R.id.currency_code_picker_listview);

        selectedCurrenciesList = new ArrayList<>(currenciesList.size());
        selectedCurrenciesList.addAll(currenciesList);

        adapter = new CurrencyListAdapter(getContext(), selectedCurrenciesList);

        currencyListView.setAdapter(adapter);

        currencyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExtendedCurrency currency = selectedCurrenciesList.get(position);
                setValue(currency.getCode());
                setSummary(currency.getCode());
                editor.putString("selectedCurrency", currency.getCode());
                editor.commit();
                getDialog().dismiss();
            }
        });

        builder.setView(view);

        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton(null, null);
        currencyCode = getEntries();
        currencyName = getEntryValues();

        if (currencyName == null || currencyCode == null || currencyCode.length != currencyName.length) {
            throw new IllegalStateException(
                    "Preference requires an entries array and an entryValues array which are both the same length");
        }

    }

    @SuppressLint("DefaultLocale")
    private void search(String text) {
        this.selectedCurrenciesList.clear();
        for (ExtendedCurrency currency : this.currenciesList) {
            if (currency.getName().toLowerCase(Locale.ENGLISH).contains(text.toLowerCase())) {
                this.selectedCurrenciesList.add(currency);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void setCurrenciesList(List<ExtendedCurrency> newCurrencies) {
        this.currenciesList.clear();
        this.currenciesList.addAll(newCurrencies);
    }


}
