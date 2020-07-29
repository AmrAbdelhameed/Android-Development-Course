package com.example.sessionfive;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name, phone;
    private ContactsDbHelper contactsDbHelper;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        contactsDbHelper = new ContactsDbHelper(ContactDetailsActivity.this);

        name = findViewById(R.id.enter_name);
        phone = findViewById(R.id.enter_phone);
        Button save = findViewById(R.id.save);
        save.setOnClickListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.contact_details));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            contact = (Contact) bundle.getSerializable(Constants.CONTACT);
            if (contact != null) {
                name.setText(contact.getName());
                phone.setText(contact.getPhone());
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save) {
            String _name = name.getText().toString();
            String _phone = phone.getText().toString();
            if (!_name.isEmpty() && !_phone.isEmpty()) {
                if (contact != null) {
                    contact.setName(_name);
                    contact.setPhone(_phone);
                    contactsDbHelper.updateContact(contact);
                } else {
                    contact = new Contact(_name, _phone);
                    contactsDbHelper.createContact(contact);
                }
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