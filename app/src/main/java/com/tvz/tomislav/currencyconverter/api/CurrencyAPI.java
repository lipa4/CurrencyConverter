package com.tvz.tomislav.currencyconverter.api;

import com.tvz.tomislav.currencyconverter.models.Currency;

import java.util.List;

import retrofit2.http.GET;
import io.reactivex.Observable;

public interface CurrencyAPI {

    @GET("api/v1/rates/daily")
    Observable<List<Currency>> getCurrencies();

}