package com.mynameismidori.currencypicker;

import android.content.Context;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ExtendedCurrency {
    public static final ExtendedCurrency[] CURRENCIES = {
            new ExtendedCurrency("EUR", "Euro", "€", R.drawable.flag_eur),
            new ExtendedCurrency("USD", "United States Dollar", "$", R.drawable.flag_usd),
            new ExtendedCurrency("GBP", "British Pound", "£", R.drawable.flag_gbp),
            new ExtendedCurrency("CZK", "Czech Koruna", "Kč", R.drawable.flag_czk),
            new ExtendedCurrency("TRY", "Turkish Lira", "₺", R.drawable.flag_try),
            new ExtendedCurrency("AED", "Emirati Dirham", "د.إ", R.drawable.flag_aed),
            new ExtendedCurrency("AFN", "Afghanistan Afghani", "؋", R.drawable.flag_afn),
            new ExtendedCurrency("ARS", "Argentine Peso", "$", R.drawable.flag_ars),
            new ExtendedCurrency("AUD", "Australian Dollar", "$", R.drawable.flag_aud),
            new ExtendedCurrency("BBD", "Barbados Dollar", "$", R.drawable.flag_bbd),
            new ExtendedCurrency("BDT", "Bangladeshi Taka", " Tk", R.drawable.flag_bdt),
            new ExtendedCurrency("BGN", "Bulgarian Lev", "лв", R.drawable.flag_bgn),
            new ExtendedCurrency("BHD", "Bahraini Dinar", "BD", R.drawable.flag_bhd),
            new ExtendedCurrency("BMD", "Bermuda Dollar","$", R.drawable.flag_bmd),
            new ExtendedCurrency("BND", "Brunei Darussalam Dollar","$", R.drawable.flag_bnd),
            new ExtendedCurrency("BOB", "Bolivia Bolíviano","$b", R.drawable.flag_bob),
            new ExtendedCurrency("BRL", "Brazil Real","R$", R.drawable.flag_brl),
            new ExtendedCurrency("BTN", "Bhutanese Ngultrum","Nu.", R.drawable.flag_btn),
            new ExtendedCurrency("BZD", "Belize Dollar","BZ$", R.drawable.flag_bzd),
            new ExtendedCurrency("CAD", "Canada Dollar","$", R.drawable.flag_cad),
            new ExtendedCurrency("CHF", "Switzerland Franc","CHF", R.drawable.flag_chf),
            new ExtendedCurrency("CLP", "Chile Peso","$", R.drawable.flag_clp),
            new ExtendedCurrency("CNY", "China Yuan Renminbi","¥", R.drawable.flag_cny),
            new ExtendedCurrency("COP", "Colombia Peso","$", R.drawable.flag_cop),
            new ExtendedCurrency("CRC", "Costa Rica Colon","₡", R.drawable.flag_crc),
            new ExtendedCurrency("DKK", "Denmark Krone","kr", R.drawable.flag_dkk),
            new ExtendedCurrency("DOP", "Dominican Republic Peso","RD$", R.drawable.flag_dop),
            new ExtendedCurrency("EGP", "Egypt Pound","£", R.drawable.flag_egp),
            new ExtendedCurrency("ETB", "Ethiopian Birr","Br", R.drawable.flag_etb),
            new ExtendedCurrency("GEL", "Georgian Lari","₾", R.drawable.flag_gel),
            new ExtendedCurrency("GHS", "Ghana Cedi","¢", R.drawable.flag_ghs),
            new ExtendedCurrency("GMD", "Gambian dalasi","D", R.drawable.flag_gmd),
            new ExtendedCurrency("GYD", "Guyana Dollar","$", R.drawable.flag_gyd),
            new ExtendedCurrency("HKD", "Hong Kong Dollar","$", R.drawable.flag_hkd),
            new ExtendedCurrency("HRK", "Croatia Kuna","kn", R.drawable.flag_hrk),
            new ExtendedCurrency("HUF", "Hungary Forint","Ft", R.drawable.flag_huf),
            new ExtendedCurrency("IDR", "Indonesia Rupiah","Rp", R.drawable.flag_idr),
            new ExtendedCurrency("ILS", "Israel Shekel","₪", R.drawable.flag_ils),
            new ExtendedCurrency("INR", "Indian Rupee","₹", R.drawable.flag_inr),
            new ExtendedCurrency("ISK", "Iceland Krona","kr", R.drawable.flag_isk),
            new ExtendedCurrency("JMD", "Jamaica Dollar","J$", R.drawable.flag_jmd),
            new ExtendedCurrency("JPY", "Japanese Yen","¥", R.drawable.flag_jpy),
            new ExtendedCurrency("KES", "Kenyan Shilling","KSh", R.drawable.flag_kes),
            new ExtendedCurrency("KRW", "Korea (South) Won","₩", R.drawable.flag_krw),
            new ExtendedCurrency("KWD", "Kuwaiti Dinar","د.ك", R.drawable.flag_kwd),
            new ExtendedCurrency("KYD", "Cayman Islands Dollar","$", R.drawable.flag_kyd),
            new ExtendedCurrency("KZT", "Kazakhstan Tenge","лв", R.drawable.flag_kzt),
            new ExtendedCurrency("LAK", "Laos Kip","₭", R.drawable.flag_lak),
            new ExtendedCurrency("LKR", "Sri Lanka Rupee","₨", R.drawable.flag_lkr),
            new ExtendedCurrency("LRD", "Liberia Dollar","$", R.drawable.flag_lrd),
            new ExtendedCurrency("LTL", "Lithuanian Litas","Lt", R.drawable.flag_ltl),
            new ExtendedCurrency("MAD", "Moroccan Dirham","MAD", R.drawable.flag_mad),
            new ExtendedCurrency("MDL", "Moldovan Leu","MDL", R.drawable.flag_mdl),
            new ExtendedCurrency("MKD", "Macedonia Denar","ден", R.drawable.flag_mkd),
            new ExtendedCurrency("MNT", "Mongolia Tughrik","₮", R.drawable.flag_mnt),
            new ExtendedCurrency("MUR", "Mauritius Rupee","₨", R.drawable.flag_mur),
            new ExtendedCurrency("MWK", "Malawian Kwacha","MK", R.drawable.flag_mwk),
            new ExtendedCurrency("MXN", "Mexico Peso","$", R.drawable.flag_mxn),
            new ExtendedCurrency("MYR", "Malaysia Ringgit","RM", R.drawable.flag_myr),
            new ExtendedCurrency("MZN", "Mozambique Metical","MT", R.drawable.flag_mzn),
            new ExtendedCurrency("NAD", "Namibia Dollar","$", R.drawable.flag_nad),
            new ExtendedCurrency("NGN", "Nigeria Naira","₦", R.drawable.flag_ngn),
            new ExtendedCurrency("NIO", "Nicaragua Cordoba","C$", R.drawable.flag_nio),
            new ExtendedCurrency("NOK", "Norway Krone","kr", R.drawable.flag_nok),
            new ExtendedCurrency("NPR", "Nepal Rupee","₨", R.drawable.flag_npr),
            new ExtendedCurrency("NZD", "New Zealand Dollar","$", R.drawable.flag_nzd),
            new ExtendedCurrency("OMR", "Oman Rial","﷼", R.drawable.flag_omr),
            new ExtendedCurrency("PEN", "Peru Sol","S/.", R.drawable.flag_pen),
            new ExtendedCurrency("PGK", "Papua New Guinean Kina","K", R.drawable.flag_pgk),
            new ExtendedCurrency("PHP", "Philippines Peso","₱", R.drawable.flag_php),
            new ExtendedCurrency("PKR", "Pakistan Rupee","₨", R.drawable.flag_pkr),
            new ExtendedCurrency("PLN", "Poland Zloty","zł", R.drawable.flag_pln),
            new ExtendedCurrency("PYG", "Paraguay Guarani","Gs", R.drawable.flag_pyg),
            new ExtendedCurrency("QAR", "Qatar Riyal","﷼", R.drawable.flag_qar),
            new ExtendedCurrency("RON", "Romania Leu","lei", R.drawable.flag_ron),
            new ExtendedCurrency("RSD", "Serbia Dinar","Дин.", R.drawable.flag_rsd),
            new ExtendedCurrency("RUB", "Russia Ruble","₽", R.drawable.flag_rub),
            new ExtendedCurrency("SAR", "Saudi Arabia Riyal","﷼", R.drawable.flag_sar),
            new ExtendedCurrency("SEK", "Sweden Krona","kr", R.drawable.flag_sek),
            new ExtendedCurrency("SGD", "Singapore Dollar","$", R.drawable.flag_sgd),
            new ExtendedCurrency("SOS", "Somalia Shilling","S", R.drawable.flag_sos),
            new ExtendedCurrency("SRD", "Suriname Dollar","$", R.drawable.flag_srd),
            new ExtendedCurrency("THB", "Thailand Baht","฿", R.drawable.flag_thb),
            new ExtendedCurrency("TTD", "Trinidad and Tobago Dollar","TT$", R.drawable.flag_ttd),
            new ExtendedCurrency("TWD", "Taiwan New Dollar","NT$", R.drawable.flag_twd),
            new ExtendedCurrency("TZS", "Tanzanian Shilling","TSh", R.drawable.flag_tzs),
            new ExtendedCurrency("UAH", "Ukraine Hryvnia","₴", R.drawable.flag_uah),
            new ExtendedCurrency("UGX", "Ugandan Shilling","USh", R.drawable.flag_ugx),
            new ExtendedCurrency("UYU", "Uruguay Peso","$U", R.drawable.flag_uyu),
            new ExtendedCurrency("VEF", "Venezuela Bolívar","Bs", R.drawable.flag_vef),
            new ExtendedCurrency("VND", "Viet Nam Dong","₫", R.drawable.flag_vnd),
            new ExtendedCurrency("YER", "Yemen Rial","﷼", R.drawable.flag_yer),
            new ExtendedCurrency("ZAR", "South Africa Rand","R", R.drawable.flag_zar)
    };
    private String code;
    private String name;
    private String symbol;
    private int flag = -1;

    public ExtendedCurrency(String code, String name, String symbol, int flag) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
        this.flag = flag;
    }

    public ExtendedCurrency() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void loadFlagByCode(Context context) {
        if (this.flag != -1)
            return;

        try {
            this.flag = context.getResources()
                    .getIdentifier("flag_" + this.code.toLowerCase(), "drawable",
                            context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            this.flag = -1;
        }
    }

    /*
     *      GENERIC STATIC FUNCTIONS
     */

    private static List<ExtendedCurrency> allCurrenciesList;

    public static List<ExtendedCurrency> getAllCurrencies() {
        if (allCurrenciesList == null) {
            allCurrenciesList = Arrays.asList(CURRENCIES);
        }
        return allCurrenciesList;
    }

    public static ExtendedCurrency getCurrencyByISO(String currencyIsoCode) {
        // Because the data we have is sorted by ISO codes and not by names, we must check all
        // currencies one by one

        for (ExtendedCurrency c : CURRENCIES) {
            if (currencyIsoCode.equals(c.getCode())) {
                return c;
            }
        }
        return null;
    }

    public static ExtendedCurrency getCurrencyByName(String currencyName) {
        // Because the data we have is sorted by ISO codes and not by names, we must check all
        // currencies one by one

        for (ExtendedCurrency c : CURRENCIES) {
            if (currencyName.equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

    /*
     * COMPARATORS
     */

    public static class ISOCodeComparator implements Comparator<ExtendedCurrency> {
        @Override
        public int compare(ExtendedCurrency currency, ExtendedCurrency t1) {
            return currency.code.compareTo(t1.code);
        }
    }


    public static class NameComparator implements Comparator<ExtendedCurrency> {
        @Override
        public int compare(ExtendedCurrency currency, ExtendedCurrency t1) {
            return currency.name.compareTo(t1.name);
        }
    }
}
