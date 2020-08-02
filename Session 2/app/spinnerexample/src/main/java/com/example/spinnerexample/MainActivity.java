package com.example.spinnerexample;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText tempText;
    Spinner tempOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempText = findViewById(R.id.temp_text);
        tempOptions = findViewById(R.id.temp_options);

        tempOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tempTextStr = tempText.getText().toString();
                if (!tempTextStr.isEmpty()) {
                    float tempTextFloat = Float.parseFloat(tempTextStr);
                    Temperature temperature = new Temperature();
                    temperature.setTemperature(tempTextFloat);
                    String selectionValue = tempOptions.getSelectedItem().toString();
                    if (selectionValue.equals("Celsius")) {
                        tempTextFloat = temperature.convertFahrenheitToCelsius();
                    } else if (selectionValue.equals("Fahrenheit")) {
                        tempTextFloat = temperature.convertCelsiusToFahrenheit();
                    }
                    tempText.setText(String.valueOf(tempTextFloat));
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
