package com.tvz.tomislav.currencyconverter.models;


import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Currency implements Serializable {

    @Expose
    private int unit_value;
    @Expose
    private String currency_code;
    @Expose
    private double median_rate;
    @Expose
    private double selling_rate;
    @Expose
    private double buying_rate;

    public int getUnit_value() {
        return unit_value;
    }

    public void setUnit_value(int unit_value) {
        this.unit_value = unit_value;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public double getMedian_rate() {
        return median_rate;
    }

    public void setMedian_rate(double median_rate) {
        this.median_rate = median_rate;
    }

    public double getSelling_rate() {
        return selling_rate;
    }

    public void setSelling_rate(double selling_rate) {
        this.selling_rate = selling_rate;
    }

    public double getBuying_rate() {
        return buying_rate;
    }

    public void setBuying_rate(double buying_rate) {
        this.buying_rate = buying_rate;
    }

    public static List<String> getCurrencyCodes(List<Currency> elements){
        List<String> currencyCodes = new ArrayList<>();
        for(Currency item:elements){
            currencyCodes.add(item.getCurrency_code());
        }
        return currencyCodes;
    }
}
