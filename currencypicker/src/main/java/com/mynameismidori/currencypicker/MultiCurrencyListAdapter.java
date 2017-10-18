package com.mynameismidori.currencypicker;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultiCurrencyListAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
    private Context mContext;
    List<ExtendedCurrency> currencies;
    LayoutInflater inflater;
    Set<String> selectedCurrencies;
    private SparseBooleanArray checkedCurrencies;

    public MultiCurrencyListAdapter(Context context, List<ExtendedCurrency> currencies, Set<String> selectedCurrencies) {
        super();
        this.mContext = context;
        this.currencies = currencies;
        this.selectedCurrencies = selectedCurrencies;
        checkedCurrencies = new SparseBooleanArray(currencies.size());
        for(String code : selectedCurrencies){
            int position = currencies.indexOf(ExtendedCurrency.getCurrencyByISO(code));
            checkedCurrencies.put(position, true);
        }

        inflater = LayoutInflater.from(context);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setChecked((Integer) buttonView.getTag(), isChecked);
    }

    public boolean isChecked(int position) {
        return checkedCurrencies.get(position, false);
    }

    public void setChecked(int position, boolean isChecked) {
        checkedCurrencies.put(position, isChecked);
        if(isChecked){
            selectedCurrencies.add(currencies.get(position).getCode());
        }
        else{
            selectedCurrencies.remove(currencies.get(position).getCode());
        }
    }

    public void toggle(int position) {
        setChecked(position, !isChecked(position));
    }

    @Override
    public int getCount() {
        return currencies.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ExtendedCurrency currency = currencies.get(position);

        if (view == null)
            view = inflater.inflate(R.layout.row, null);

        Cell cell = Cell.from(view);
        cell.textView.setText(currency.getName());
        cell.checkBox.setTag(position);
        cell.checkBox.setOnCheckedChangeListener(this);
        cell.checkBox.setChecked(checkedCurrencies.get(position, false));
        /*
        if(selectedCurrencies.contains(currency.getCode())){
            //cell.checkBox.setChecked(true);
        }

        cell.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("VALUES",currency.getCode());
                if(isChecked){
                    //selectedCurrencies.add(currency.getCode());
                }
                else{
                    //selectedCurrencies.remove(currency.getCode());
                }
            }
        });

        */
        currency.loadFlagByCode(mContext);
        if (currency.getFlag() != -1)
            cell.imageView.setImageResource(currency.getFlag());
        return view;
    }

    static class Cell {
        public TextView textView;
        public ImageView imageView;
        public CheckBox checkBox;

        static Cell from(View view) {
            if (view == null)
                return null;

            if (view.getTag() == null) {
                Cell cell = new Cell();
                cell.textView = (TextView) view.findViewById(R.id.row_title);
                cell.imageView = (ImageView) view.findViewById(R.id.row_icon);
                cell.checkBox = (CheckBox) view.findViewById(R.id.checkBox);
                cell.checkBox.setVisibility(View.VISIBLE);
                view.setTag(cell);
                return cell;
            } else {
                return (Cell) view.getTag();
            }
        }
    }
}
