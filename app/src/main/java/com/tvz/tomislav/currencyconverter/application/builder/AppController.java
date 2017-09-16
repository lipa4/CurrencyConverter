package com.tvz.tomislav.currencyconverter.application.builder;

import android.app.Application;

public class AppController extends Application {

    private static AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder().appContextModule(new AppContextModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
