package com.tvz.tomislav.currencyconverter.screen.core;

import android.support.design.widget.Snackbar;
import android.util.Log;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.jakewharton.rxbinding2.widget.RxAdapterView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.tvz.tomislav.currencyconverter.models.Currency;
import com.tvz.tomislav.currencyconverter.utils.UiUtils;
import com.tvz.tomislav.currencyconverter.utils.rx.RxSchedulers;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;



public class ConverterPresenter {



    ConverterView view;
    ConverterModel model;
    RxSchedulers rxSchedulers;
    CompositeDisposable subscriptions;
    List<Currency> currenciesList;

    private static final String ERROR_MESSAGE = "Choose different currencies!";
    static boolean first = true;

    private Disposable waitForNetwork;
    private Flowable<Integer> fromSpinnerChangeObservable;
    private Flowable<Integer> toSpinnerChangeObservable;
    private Flowable<CharSequence> amountChangeObservable;
    private DisposableSubscriber<Boolean> disposableSubscriber;

    public ConverterPresenter(RxSchedulers schedulers, ConverterModel model, ConverterView view, CompositeDisposable sub) {
        this.rxSchedulers = schedulers;
        this.view = view;
        this.model = model;
        this.subscriptions = sub;
    }

    public void onCreate() {

        subscriptions.add(getCurrencyList());

        fromSpinnerChangeObservable =
                RxAdapterView.itemSelections(view.fromSpinner).skip(1).toFlowable(BackpressureStrategy.LATEST);
        amountChangeObservable =
                RxTextView.textChanges(view.amountEditText).toFlowable(BackpressureStrategy.LATEST);
        toSpinnerChangeObservable =
                RxAdapterView.itemSelections(view.toSpinner).skip(1).toFlowable(BackpressureStrategy.LATEST);

        converterListener();


    }

    public void onDestroy() {
        subscriptions.clear();
        disposableSubscriber.dispose();
    }


    private Disposable waitForConnection(){

         waitForNetwork=ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connected -> {
                            if (connected){
                                getCurrencyList();
                                subscriptions.remove(waitForNetwork);
                            }

                        }, throwable -> {
                            UiUtils.handleThrowable(throwable);
                        }
                );
         return waitForNetwork;
    }


    private Disposable getCurrencyList() {

        return model.isNetworkAvailable().doOnNext(networkAvailable -> {
            if (!networkAvailable) {
                Log.d("Connection error: ", "No connection!");
                UiUtils.showSnackbar(view.view,"No connection!", Snackbar.LENGTH_LONG);
                subscriptions.add(waitForConnection());
            }
        }).
                filter(isNetworkAvailable -> true).
                flatMap(isAvailable -> model.provideListCurrencies()).
                subscribeOn(rxSchedulers.internet()).
                observeOn(rxSchedulers.androidThread()).
                subscribe(currencies -> {
                    Log.d("Currency list: ", "Load completed!");
                    view.setAdapter(Currency.getCurrencyCodes(currencies));
                   currenciesList = currencies;
                }, throwable -> {
                    UiUtils.handleThrowable(throwable);
                }
        );

    }



    private void converterListener(){

        disposableSubscriber = new DisposableSubscriber<Boolean>(){
            @Override
            public void onNext(Boolean formValid) {
                if (formValid) {
                    int fromIndex = view.fromSpinner.getSelectedItemPosition();
                    int toIndex = view.toSpinner.getSelectedItemPosition();
                    Double amount=Double.valueOf(view.amountEditText.getText().toString());
                    Double result=model.convertCurrencies(currenciesList.get(fromIndex), currenciesList.get(toIndex),amount);
                    view.updateResultLabel(result);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Disposable subscriber: ", "There was an error!", e);
            }

            @Override
            public void onComplete() {
                Log.d("Converter subscriber: ", "Completed!");
            }
        };

        Flowable.combineLatest(
                fromSpinnerChangeObservable,
                amountChangeObservable,
                toSpinnerChangeObservable,
                (from, amount, to) -> {
                    if (from.intValue() == to.intValue()) {
                        if (!first){
                            view.amountEditText.setError(ERROR_MESSAGE);
                            view.convertedValueTextView.setText("");
                            return false;
                        }
                        first=false;

                    }
                    view.amountEditText.setError(null);

                    if (amount.length()==0){
                        view.convertedValueTextView.setText("");
                        return false;
                    }

                    view.toCurrencyLabel.setText(currenciesList.get(to).getCurrency_code());
                    view.fromCurrencyLabel.setText(currenciesList.get(from).getCurrency_code());

                    return true;
                })
                .subscribe(disposableSubscriber);
    }
}
