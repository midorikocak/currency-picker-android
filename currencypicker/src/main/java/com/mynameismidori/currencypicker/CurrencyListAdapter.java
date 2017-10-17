package com.mynameismidori.currencypicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CurrencyListAdapter extends BaseAdapter {
    private Context mContext;
    List<ExtendedCurrency> currencies;
    LayoutInflater inflater;

    public CurrencyListAdapter(Context context, List<ExtendedCurrency> currencies) {
        super();
        this.mContext = context;
        this.currencies = currencies;
        inflater = LayoutInflater.from(context);
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
    public View getView(int position, View view, ViewGroup parent) {
        ExtendedCurrency currency = currencies.get(position);

        if (view == null)
            view = inflater.inflate(R.layout.row, null);

        Cell cell = Cell.from(view);
        cell.textView.setText(currency.getName());

        currency.loadFlagByCode(mContext);
        if (currency.getFlag() != -1)
            cell.imageView.setImageResource(currency.getFlag());
        return view;
    }

    static class Cell {
        public TextView textView;
        public ImageView imageView;

        static Cell from(View view) {
            if (view == null)
                return null;

            if (view.getTag() == null) {
                Cell cell = new Cell();
                cell.textView = (TextView) view.findViewById(R.id.row_title);
                cell.imageView = (ImageView) view.findViewById(R.id.row_icon);
                view.setTag(cell);
                return cell;
            } else {
                return (Cell) view.getTag();
            }
        }
    }
}
