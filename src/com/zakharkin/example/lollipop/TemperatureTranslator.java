package com.zakharkin.example.lollipop;

/**
 * Created by Andrew on 23.11.2014.
 */
public class TemperatureTranslator {
    public double ToFahrenheit(double from){
        return Math.round(from * 90000.0 / 5.0 + 320000.0) / 10000.0;
    }

    public double ToCelsius(double from){
        return Math.round((from - 32.0) * 50000.0 / 9.0) / 10000.0;
    }
}
