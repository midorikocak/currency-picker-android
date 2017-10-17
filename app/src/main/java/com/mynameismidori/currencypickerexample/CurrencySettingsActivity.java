package com.mynameismidori.currencypickerexample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.TextView;

import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;
import com.mynameismidori.currencypicker.ExtendedCurrency;

import java.util.ArrayList;


public class CurrencySettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new CurrencyPreferenceFragment()).commit();
    }

    public static class CurrencyPreferenceFragment extends PreferenceFragment implements CurrencyPickerListener {
        private CurrencyPicker mCurrencyPicker;
        private ListPreference currencyPreference;

        private static final String TAG = "CurrencyPreference";


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            currencyPreference = (ListPreference) findPreference("selectedCurrency");
            mCurrencyPicker = CurrencyPicker.newInstance("Select Currency");


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
            mCurrencyPicker.setListener(this);
        }

        @Override
        public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
            currencyPreference.setValue(code);
        }
    }

}
