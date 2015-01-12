package com.zakharkin.example.lollipop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        buttonCelsius.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                translate(true);
                resultText.setText(buttonCelsius.getText());
            }
        });

        buttonFahrenheit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                translate(false);
                resultText.setText(buttonFahrenheit.getText());
            }
        });

        buttonMmHg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pressTranslate(true);
                resultText.setText(buttonMmHg.getText());
            }
        });

        buttonPsi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pressTranslate(false);
                resultText.setText(buttonPsi.getText());
            }
        });
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
