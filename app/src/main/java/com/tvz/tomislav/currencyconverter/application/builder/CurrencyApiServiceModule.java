package com.tvz.tomislav.currencyconverter.application.builder;


import com.tvz.tomislav.currencyconverter.api.CurrencyAPI;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
public class CurrencyApiServiceModule {

    private static final String BASE_URL = "http://hnbex.eu/";
    @AppScope
    @Provides
    CurrencyAPI provideApiService(OkHttpClient client, GsonConverterFactory gson)
    {
        Retrofit retrofit =   new Retrofit.Builder().client(client)
                .baseUrl(BASE_URL).addConverterFactory(gson).
                        addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        return  retrofit.create(CurrencyAPI.class);
    }
}
