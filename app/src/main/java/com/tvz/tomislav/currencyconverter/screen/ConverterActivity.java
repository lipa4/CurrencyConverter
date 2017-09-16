package com.tvz.tomislav.currencyconverter.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import com.tvz.tomislav.currencyconverter.R;
import com.tvz.tomislav.currencyconverter.application.builder.AppController;
import com.tvz.tomislav.currencyconverter.screen.core.ConverterPresenter;
import com.tvz.tomislav.currencyconverter.screen.core.ConverterView;
import com.tvz.tomislav.currencyconverter.screen.dagger.ConverterContextModule;
import com.tvz.tomislav.currencyconverter.screen.dagger.DaggerConverterComponent;

import javax.inject.Inject;

import butterknife.BindView;


public class ConverterActivity extends AppCompatActivity {

    @Inject
    ConverterView view;
    @Inject
    ConverterPresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerConverterComponent.builder().
                appComponent(AppController.getAppComponent()).
                converterContextModule(new ConverterContextModule(this)).
                build().inject(this);

        setContentView(view.view());
        presenter.onCreate();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
