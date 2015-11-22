package com.zakharkin.example.lollipop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class FahrenheitWindow extends Activity {
    private GeneralTranslator translator = new GeneralTranslator();
    Button buttonCelsius, buttonFahrenheit;
    Button buttonMmHg, buttonPsi;
    EditText editTextFrom;
    TextView resultText, textViewResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buttonCelsius = (Button) findViewById(R.id.buttonCelsius);
        buttonFahrenheit = (Button) findViewById(R.id.buttonFahrenheit);
        buttonMmHg = (Button) findViewById(R.id.buttonMmHg);
        buttonPsi = (Button) findViewById(R.id.buttonPsi);
        editTextFrom = (EditText) findViewById(R.id.editTextFrom);
        textViewResult = (TextView) findViewById(R.id.textViewResultValue);
        resultText = (TextView) findViewById(R.id.textViewResult);
    }

    public void buttonCelsiusOnClick(View view) {
        translate(true);
        resultText.setText(buttonCelsius.getText());
    }

    public void buttonFahrenheitOnClick(View view){
        translate(false);
        resultText.setText(buttonFahrenheit.getText());
    }

    public void buttonMmHgOnClick(View view){
        pressTranslate(true);
        resultText.setText(buttonMmHg.getText());
    }

    public void buttonPsiOnClick(View view){
        pressTranslate(false);
        resultText.setText(buttonPsi.getText());
    }

    public void buttonViewOnClick(View view){
        Intent intent = new Intent(FahrenheitWindow.this, DBViewer.class);
        startActivity(intent);
    }

    private void translate(boolean toCelsius) {
        double from;
        try {
            from = Double.parseDouble(editTextFrom.getText().toString());
        }
        catch (Exception e){
            from = 0.0;
        }
        editTextFrom.setText(Double.toString(from));
        textViewResult.setText(Double.toString(toCelsius ? translator.ToCelsius(from) : translator.ToFahrenheit(from)));
    }

    private void pressTranslate(boolean toMmHg){
        double from;
        try{
            from = Double.parseDouble(editTextFrom.getText().toString());
        }
        catch (Exception e){
            from = 0.0;
        }
        editTextFrom.setText(Double.toString(from));
        textViewResult.setText(Double.toString(toMmHg ? translator.ToMmHg(from) : translator.ToPsi(from)));
    }
}
