package com.mynameismidori.currencypickerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;
import com.mynameismidori.currencypicker.ExtendedCurrency;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

  private TextView mCurrencyNameTextView, mCurrencyIsoCodeTextView, mCurrencySymbolTextView;
  private ImageView mCurrencyFlagImageView;
  private Button mPickCurrencyButton;
  private CurrencyPicker mCurrencyPicker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initialize();
    setListener();
  }

  private void setListener() {
    mCurrencyPicker.setListener(new CurrencyPickerListener() {
      @Override
      public void onSelectCurrency(String name, String code, String symbol,
                                  int flagDrawableResID) {
        mCurrencyNameTextView.setText(name);
        mCurrencyIsoCodeTextView.setText(code);
        mCurrencySymbolTextView.setText(symbol);
        mCurrencyFlagImageView.setImageResource(flagDrawableResID);
        mCurrencyPicker.dismiss();
      }
    });
    mPickCurrencyButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mCurrencyPicker.show(getSupportFragmentManager(), "CURRENCY_PICKER");
      }
    });
    getUserCurrencyInfo(mCurrencyIsoCodeTextView.getText().toString());
  }

  private void initialize() {
    mCurrencyNameTextView = (TextView) findViewById(R.id.selected_currency_name_text_view);
    mCurrencyIsoCodeTextView = (TextView) findViewById(R.id.selected_currency_iso_text_view);
    mCurrencySymbolTextView = (TextView) findViewById(R.id.selected_currency_symbol_text_view);
    mPickCurrencyButton = (Button) findViewById(R.id.currency_picker_button);
    mCurrencyFlagImageView = (ImageView) findViewById(R.id.selected_currency_flag_image_view);
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