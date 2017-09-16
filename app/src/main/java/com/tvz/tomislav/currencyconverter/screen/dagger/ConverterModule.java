package com.tvz.tomislav.currencyconverter.screen.dagger;


import com.tvz.tomislav.currencyconverter.api.CurrencyAPI;
import com.tvz.tomislav.currencyconverter.screen.ConverterActivity;
import com.tvz.tomislav.currencyconverter.screen.core.ConverterModel;
import com.tvz.tomislav.currencyconverter.screen.core.ConverterPresenter;
import com.tvz.tomislav.currencyconverter.screen.core.ConverterView;
import com.tvz.tomislav.currencyconverter.utils.rx.RxSchedulers;



import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class ConverterModule {

    @ConverterScope
    @Provides
    ConverterView provideConverterView(ConverterActivity context) {
        return new ConverterView(context);
    }

    @ConverterScope
    @Provides
    ConverterPresenter providePresenter(RxSchedulers schedulers, ConverterModel model,ConverterView view) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        return new ConverterPresenter( schedulers,model,view, compositeDisposable);
    }


    @ConverterScope
    @Provides
    ConverterModel provideConverterModel(CurrencyAPI api, ConverterActivity ctx) {
        return new ConverterModel(ctx, api);
    }

}
