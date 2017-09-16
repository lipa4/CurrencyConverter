package com.tvz.tomislav.currencyconverter.application.builder;


import com.tvz.tomislav.currencyconverter.api.CurrencyAPI;
import com.tvz.tomislav.currencyconverter.utils.rx.RxSchedulers;

import dagger.Component;


@AppScope
@Component(modules = {
        NetworkModule.class,
        AppContextModule.class,
        RxModule.class,
        CurrencyApiServiceModule.class}
        )
public interface AppComponent {

    RxSchedulers rxSchedulers();
    CurrencyAPI apiService();

}
