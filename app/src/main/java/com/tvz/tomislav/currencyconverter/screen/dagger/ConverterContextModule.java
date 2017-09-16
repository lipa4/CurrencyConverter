package com.tvz.tomislav.currencyconverter.screen.dagger;

import com.tvz.tomislav.currencyconverter.screen.ConverterActivity;



import dagger.Module;
import dagger.Provides;


@Module
public class ConverterContextModule {

    ConverterActivity converterContext;

    public ConverterContextModule(ConverterActivity context) {
        this.converterContext = context;
    }
    @ConverterScope
    @Provides
    ConverterActivity provideConverterContext() {
        return converterContext;
    }
}
