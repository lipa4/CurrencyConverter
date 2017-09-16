package com.tvz.tomislav.currencyconverter.screen.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.tvz.tomislav.currencyconverter.R;
import com.tvz.tomislav.currencyconverter.screen.ConverterActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class ConverterView {

    @BindView(R.id.from_spinner)
    Spinner fromSpinner;
    @BindView(R.id.to_spinner)
    Spinner toSpinner;
    @BindView(R.id.to_label_textView)
    TextView toLabelTextview;
    @BindView(R.id.from_label_textView)
    TextView fromLabelTextView;
    @BindView(R.id.from_currency_label)
    TextView fromCurrencyLabel;
    @BindView(R.id.to_currency_label)
    TextView toCurrencyLabel;
    @BindView(R.id.amount_editText)
    EditText amountEditText;
    @BindView(R.id.converted_value_textView)
    TextView convertedValueTextView;


    static ArrayAdapter<String> sSpinnerArrayAdapter;

    View view;

    public ConverterView(ConverterActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_converter, parent, true);
        ButterKnife.bind(this, view);

    }


    public View view() {
        return view;
    }

    public void setAdapter(List<String> currencies)
    {

        sSpinnerArrayAdapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,currencies);
        sSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(sSpinnerArrayAdapter);
        toSpinner.setAdapter(sSpinnerArrayAdapter);
    }
    public void updateResultLabel(Double result){
        convertedValueTextView.setText(result.toString());
    }
}
