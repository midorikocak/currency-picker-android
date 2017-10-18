package com.mynameismidori.currencypickerexample;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;
import com.mynameismidori.currencypicker.CurrencyPreference;
import com.mynameismidori.currencypicker.ExtendedCurrency;
import com.mynameismidori.currencypicker.MultiCurrencyPreference;

import java.util.ArrayList;
import java.util.List;


public class CurrencySettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new CurrencyPreferenceFragment()).commit();
    }

    public static class CurrencyPreferenceFragment extends PreferenceFragment implements CurrencyPickerListener {
        private CurrencyPicker mCurrencyPicker;
        private CurrencyPreference currencyPreference;
        private MultiCurrencyPreference multiSelectListPreference;

        private static final String TAG = "CurrencyPreference";


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            currencyPreference = (CurrencyPreference) findPreference("selectedCurrency");
            mCurrencyPicker = CurrencyPicker.newInstance("Select Currency");
            multiSelectListPreference = (MultiCurrencyPreference) findPreference("selectedCurrencies");

            List<CharSequence> availableCurrencyNames = new ArrayList<CharSequence>();
            List<CharSequence> availableCurrencyCodes = new ArrayList<CharSequence>();

            // You can limit the displayed countries
            ArrayList<ExtendedCurrency> nc = new ArrayList<>();
            for (ExtendedCurrency c : ExtendedCurrency.getAllCurrencies()) {
                //if (c.getSymbol().endsWith("0")) {
                nc.add(c);

                availableCurrencyCodes.add(c.getCode());
                availableCurrencyNames.add(c.getName());
                //}
            }
            // and decide, in which order they will be displayed
            //Collections.reverse(nc);

            multiSelectListPreference.setEntries(availableCurrencyNames.toArray(new CharSequence[]{}));
            multiSelectListPreference.setEntryValues(availableCurrencyCodes.toArray(new CharSequence[]{}));

            mCurrencyPicker.setCurrenciesList(nc);
            mCurrencyPicker.setListener(this);
        }

        @Override
        public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
            currencyPreference.setValue(code);
        }
    }

}
