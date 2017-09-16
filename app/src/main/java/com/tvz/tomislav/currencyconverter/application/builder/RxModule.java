package com.tvz.tomislav.currencyconverter.application.builder;

import com.tvz.tomislav.currencyconverter.utils.rx.AppRxSchedulers;
import com.tvz.tomislav.currencyconverter.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;


@Module
public class RxModule {

    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}

