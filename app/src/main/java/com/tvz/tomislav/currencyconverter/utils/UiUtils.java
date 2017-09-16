package com.tvz.tomislav.currencyconverter.utils;


import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;


public class UiUtils {

    public static void handleThrowable(Throwable throwable) {
        Log.e("RxError: ","There was an error!",throwable);
    }

    public static void showSnackbar(View view, String message, int length) {
        Snackbar.make(view, message, length).setAction("Action", null).show();
    }
}
