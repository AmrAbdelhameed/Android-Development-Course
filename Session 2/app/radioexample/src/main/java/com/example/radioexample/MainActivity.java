package com.example.radioexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText tempText;
    RadioButton radioCelsius;
    RadioButton radioFahrenheit;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempText = findViewById(R.id.temp_text);
        radioCelsius = findViewById(R.id.radio_celsius);
        radioFahrenheit = findViewById(R.id.radio_fahrenheit);
        calculate = findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempTextStr = tempText.getText().toString();
                if (!tempTextStr.isEmpty()) {
                    float tempTextFloat = Float.parseFloat(tempTextStr);
                    Temperature temperature = new Temperature();
                    temperature.setTemperature(tempTextFloat);
                    if (radioCelsius.isChecked()) {
                        tempTextFloat = temperature.convertFahrenheitToCelsius();
                    } else if (radioFahrenheit.isChecked()) {
                        tempTextFloat = temperature.convertCelsiusToFahrenheit();
                    } else {
                        return;
                    }
                    tempText.setText(String.valueOf(tempTextFloat));
                } else {
                    Toast.makeText(MainActivity.this, R.string.please_enter_a_valid_number, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}