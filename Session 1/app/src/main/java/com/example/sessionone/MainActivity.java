package com.example.sessionone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText number1, number2;
    Button add;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        add = findViewById(R.id.add);
        result = findViewById(R.id.result);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int _number1 = Integer.parseInt(number1.getText().toString());
                int _number2 = Integer.parseInt(number2.getText().toString());

                Calculator calculator = new Calculator(_number1, _number2);
                result.setText(String.valueOf(calculator.add()));
            }
        });
    }
}