package com.zakharkin.example.lollipop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FahrenheitWindow extends Activity {
    private TemperatureTranslator translator = new TemperatureTranslator();
    Button buttonCelsius, buttonFahrenheit;
    EditText editTextFrom;
    TextView resultText, textViewResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buttonCelsius = (Button) findViewById(R.id.buttonCelsius);
        buttonFahrenheit = (Button) findViewById(R.id.buttonFahrenheit);
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
}
