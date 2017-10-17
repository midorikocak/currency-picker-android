package com.mynameismidori.currencypicker;

public class CurrencyItem extends ExtendedCurrency {
    private boolean isChecked;

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean getChecked() {
        return this.isChecked;
    }
}
