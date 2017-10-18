package com.mynameismidori.currencypicker;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MultiCurrencyPreference extends MultiSelectListPreference {

    private EditText searchEditText;
    private ListView currencyListView;

    private CharSequence[] currencyName;
    private CharSequence[] currencyCode;
    private MultiCurrencyListAdapter adapter;
    private int currentIndex = 0;

    private List<ExtendedCurrency> currenciesList = new ArrayList<>();
    private List<ExtendedCurrency> selectedCurrenciesList = new ArrayList<>();

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public MultiCurrencyPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCurrenciesList(ExtendedCurrency.getAllCurrencies());

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        Log.v("VALUES", getValues().toString());
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

        adapter = new MultiCurrencyListAdapter(getContext(), selectedCurrenciesList, getValues());
        currencyListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        currencyListView.setAdapter(adapter);

        builder.setView(view);

        builder.setNegativeButton(null, null);
        builder.setPositiveButton("Ok", null);
        currencyCode = getEntries();
        currencyName = getEntryValues();

        if (currencyName == null || currencyCode == null || currencyCode.length != currencyName.length) {
            throw new IllegalStateException(
                    "Preference requires an entries array and an entryValues array which are both the same length");
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        editor.putStringSet(getKey(), getValues());
        editor.commit();
        super.onDialogClosed(positiveResult);
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
