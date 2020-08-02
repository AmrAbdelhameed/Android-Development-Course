package com.example.intentexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button navigate, navigateImplicit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigate = findViewById(R.id.navigate);
        navigateImplicit = findViewById(R.id.navigate_implicit);

        navigate.setOnClickListener(this);
        navigateImplicit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navigate:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", "Amr");
                bundle.putInt("age", 23);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.navigate_implicit:
                Uri uri = Uri.parse("https://www.google.com/");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
                break;
        }
    }
}
