package com.mynameismidori.currencypickerexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;
import com.mynameismidori.currencypicker.ExtendedCurrency;

import java.util.ArrayList;
import java.util.HashSet;

public class CurrencyFragment extends Fragment implements View.OnClickListener, CurrencyPickerListener, SharedPreferences.OnSharedPreferenceChangeListener {
    View view;
    private TextView mCurrencyNameTextView, mCurrencyIsoCodeTextView, mCurrencySymbolTextView;
    private ImageView mCurrencyFlagImageView;
    private Button mPickCurrencyButton;
    private Button mOpenFragmentButton;
    private Button mOpenPreferenceButton;
    private TextView mTextView;
    private CurrencyPicker mCurrencyPicker;
    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {

            view = inflater.inflate(R.layout.fragment_currency, container, false);


            mCurrencyNameTextView = (TextView) view.findViewById(R.id.selected_currency_name_text_view);
            mCurrencyIsoCodeTextView = (TextView) view.findViewById(R.id.selected_currency_iso_text_view);
            mCurrencySymbolTextView = (TextView) view.findViewById(R.id.selected_currency_symbol_text_view);
            mPickCurrencyButton = (Button) view.findViewById(R.id.currency_picker_button);
            mOpenFragmentButton = (Button) view.findViewById(R.id.openFragment);
            mOpenPreferenceButton = (Button) view.findViewById(R.id.openPreferences);
            mCurrencyFlagImageView = (ImageView) view.findViewById(R.id.selected_currency_flag_image_view);
            mCurrencyPicker = CurrencyPicker.newInstance("Select Currency");

            mPickCurrencyButton.setOnClickListener(this);
            mOpenFragmentButton.setOnClickListener(this);
            mOpenPreferenceButton.setOnClickListener(this);

            mTextView = (TextView) view.findViewById(R.id.selectedCurrencyPreference);
            preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            preferences.registerOnSharedPreferenceChangeListener(this);


            String selectedCurrency = preferences.getString("selectedCurrency", getString(R.string.default_currency));
            mTextView.setText(selectedCurrency);

            Toast.makeText(getActivity(), selectedCurrency, Toast.LENGTH_LONG).show();
            // You can limit the displayed countries
            ArrayList<ExtendedCurrency> nc = new ArrayList<>();
            for (ExtendedCurrency c : ExtendedCurrency.getAllCurrencies()) {
                //if (c.getSymbol().endsWith("0")) {
                nc.add(c);
                //}
            }
            // and decide, in which order they will be displayed
            //Collections.reverse(nc);
            mCurrencyPicker.setCurrenciesList(nc);
            mCurrencyPicker.setCurrenciesList(preferences.getStringSet("selectedCurrencies", new HashSet<String>()));

            mCurrencyPicker.setListener(this);
        }

        return view;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("selectedCurrency")){
            mTextView.setText(sharedPreferences.getString(key, ""));
        }
        if (key.equals("selectedCurrencies")) {
            mCurrencyPicker.setCurrenciesList(preferences.getStringSet("selectedCurrencies", new HashSet<String>()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        preferences.registerOnSharedPreferenceChangeListener(this);
        mTextView.setText(preferences.getString("selectedCurrency", "CZK"));
    }

    @Override
    public void onPause() {
        super.onPause();
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSelectCurrency(String name, String code, String symbol,
                                 int flagDrawableResID) {
        mCurrencyNameTextView.setText(name);
        mCurrencyIsoCodeTextView.setText(code);
        mCurrencySymbolTextView.setText(symbol);
        mCurrencyFlagImageView.setImageResource(flagDrawableResID);
        mCurrencyPicker.dismiss();
    }

    @Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.currency_picker_button:
                mCurrencyPicker.show(getFragmentManager(), "CURRENCY_PICKER");
                break;
            case R.id.openPreferences:
                Intent intent = new Intent(getActivity(), CurrencySettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.openFragment:

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.container, mCurrencyPicker, "currencyFragment");
                transaction.addToBackStack(null);

                transaction.commit();
                break;
        }
    }

    private void getUserCurrencyInfo(String code) {
        ExtendedCurrency currency = ExtendedCurrency.getCurrencyByISO(code);
        if (currency != null) {
            mCurrencyFlagImageView.setImageResource(currency.getFlag());
            mCurrencySymbolTextView.setText(currency.getSymbol());
            mCurrencyIsoCodeTextView.setText(currency.getCode());
            mCurrencyNameTextView.setText(currency.getName());
        }
    }
}
