package com.example.sessionsix;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText email, password;
    CheckBox checkBoxRememberMe;
    Button loginBtn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        checkBoxRememberMe = findViewById(R.id.check_box_remember_me);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(Constants.REMEMBER_ME, false))
            goToMainActivity();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_btn) {
            String _email = email.getText().toString();
            String _password = password.getText().toString();
            if (!_email.isEmpty() && !_password.isEmpty()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.EMAIL, _email);
                editor.putBoolean(Constants.REMEMBER_ME, checkBoxRememberMe.isChecked());
                editor.apply();

                goToMainActivity();
            }
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}