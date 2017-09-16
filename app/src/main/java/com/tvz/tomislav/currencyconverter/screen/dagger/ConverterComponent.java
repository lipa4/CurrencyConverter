package com.tvz.tomislav.currencyconverter.screen.dagger;


import com.tvz.tomislav.currencyconverter.application.builder.AppComponent;
import com.tvz.tomislav.currencyconverter.screen.ConverterActivity;

import dagger.Component;

@ConverterScope
@Component(
        modules = {
                ConverterContextModule.class,
                ConverterModule.class
        },
        dependencies = {AppComponent.class}
)
public interface ConverterComponent {
    void inject(ConverterActivity activity);
}
