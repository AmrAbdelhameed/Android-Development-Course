package com.example.sessionfive;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewContactActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name, phone;
    private ContactsDbHelper contactsDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        name = findViewById(R.id.enter_name);
        phone = findViewById(R.id.enter_phone);
        Button save = findViewById(R.id.save);
        save.setOnClickListener(this);

        contactsDbHelper = new ContactsDbHelper(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.new_contact));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save) {
            String _name = name.getText().toString();
            String _phone = phone.getText().toString();
            if (!_name.isEmpty() && !_phone.isEmpty()) {
                contactsDbHelper.createContact(_name, _phone);
                finish();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}