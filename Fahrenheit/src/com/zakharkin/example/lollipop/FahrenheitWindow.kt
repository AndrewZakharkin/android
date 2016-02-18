package com.zakharkin.example.lollipop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import java.util.HashMap

class FahrenheitWindow : Activity() {
    private val translator = GeneralTranslator()
    internal var buttonCelsius: Button? = null
    internal var buttonFahrenheit: Button? = null
    internal var buttonMmHg: Button? = null
    internal var buttonPsi: Button? = null
    internal var editTextFrom: EditText? = null
    internal var resultText: TextView? = null
    internal var textViewResult: TextView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        buttonCelsius = findViewById(R.id.buttonCelsius) as Button
        buttonFahrenheit = findViewById(R.id.buttonFahrenheit) as Button
        buttonMmHg = findViewById(R.id.buttonMmHg) as Button
        buttonPsi = findViewById(R.id.buttonPsi) as Button
        editTextFrom = findViewById(R.id.editTextFrom) as EditText
        textViewResult = findViewById(R.id.textViewResultValue) as TextView
        resultText = findViewById(R.id.textViewResult) as TextView
    }

    fun buttonCelsiusOnClick(view: View) {
        translate(true)
        resultText!!.text = buttonCelsius!!.text
    }

    fun buttonFahrenheitOnClick(view: View) {
        translate(false)
        resultText!!.text = buttonFahrenheit!!.text
    }

    fun buttonMmHgOnClick(view: View) {
        pressTranslate(true)
        resultText!!.text = buttonMmHg!!.text
    }

    fun buttonPsiOnClick(view: View) {
        pressTranslate(false)
        resultText!!.text = buttonPsi!!.text
    }

    fun buttonViewOnClick(view: View) {
        val intent = Intent(this@FahrenheitWindow, DBViewer::class.java)
        startActivity(intent)
    }

    private fun translate(toCelsius: Boolean) {
        val from: Double
        try {
            from = java.lang.Double.parseDouble(editTextFrom!!.text.toString())
        } catch (e: Exception) {
            from = 0.0
        }

        editTextFrom!!.setText(java.lang.Double.toString(from))
        textViewResult!!.text = java.lang.Double.toString(if (toCelsius) translator.ToCelsius(from) else translator.ToFahrenheit(from))
    }

    private fun pressTranslate(toMmHg: Boolean) {
        val from: Double
        try {
            from = java.lang.Double.parseDouble(editTextFrom!!.text.toString())
        } catch (e: Exception) {
            from = 0.0
        }

        editTextFrom!!.setText(java.lang.Double.toString(from))
        textViewResult!!.text = java.lang.Double.toString(if (toMmHg) translator.ToMmHg(from) else translator.ToPsi(from))
    }
}
