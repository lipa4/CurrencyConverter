package com.tvz.tomislav.currencyconverter.screen.core;


import com.tvz.tomislav.currencyconverter.api.CurrencyAPI;
import com.tvz.tomislav.currencyconverter.models.Currency;
import com.tvz.tomislav.currencyconverter.screen.ConverterActivity;
import com.tvz.tomislav.currencyconverter.utils.NetworkUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import io.reactivex.Observable;

public class ConverterModel {

    ConverterActivity context;
    CurrencyAPI api;

    public ConverterModel(ConverterActivity context, CurrencyAPI api) {
        this.api = api;
        this.context = context;
    }


    Observable<List<Currency>> provideListCurrencies() {
        return api.getCurrencies();
    }

    Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(context);
    }

    Double convertCurrencies(Currency from, Currency to,Double amount){

        double fromCurrencyToKN = from.getMedian_rate()*from.getUnit_value();
        double toCurrencyToKN = to.getMedian_rate()*to.getUnit_value();
        Double result = new Double((amount*fromCurrencyToKN)/toCurrencyToKN);
        BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN);
        result = bd.doubleValue();
        return result;
    }


}
