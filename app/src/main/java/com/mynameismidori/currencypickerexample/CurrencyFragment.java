package com.mynameismidori.currencypickerexample;

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

public class CurrencyFragment extends Fragment implements View.OnClickListener, CurrencyPickerListener {
    View view;
    private TextView mCurrencyNameTextView, mCurrencyIsoCodeTextView, mCurrencySymbolTextView;
    private ImageView mCurrencyFlagImageView;
    private Button mPickCurrencyButton;
    private Button mOpenFragmentButton;
    private CurrencyPicker mCurrencyPicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {

            view = inflater.inflate(R.layout.fragment_currency, container, false);


            mCurrencyNameTextView = (TextView) view.findViewById(R.id.selected_currency_name_text_view);
            mCurrencyIsoCodeTextView = (TextView) view.findViewById(R.id.selected_currency_iso_text_view);
            mCurrencySymbolTextView = (TextView) view.findViewById(R.id.selected_currency_symbol_text_view);
            mPickCurrencyButton = (Button) view.findViewById(R.id.currency_picker_button);
            mOpenFragmentButton = (Button) view.findViewById(R.id.openFragment);
            mCurrencyFlagImageView = (ImageView) view.findViewById(R.id.selected_currency_flag_image_view);
            mCurrencyPicker = CurrencyPicker.newInstance("Select Currency");

            mPickCurrencyButton.setOnClickListener(this);
            mOpenFragmentButton.setOnClickListener(this);


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

        return view;
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
        Toast.makeText(getActivity(), "clicked in fragment", Toast.LENGTH_LONG).show();
        switch (v.getId()) {
            case R.id.currency_picker_button:
                mCurrencyPicker.show(getFragmentManager(), "CURRENCY_PICKER");
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
