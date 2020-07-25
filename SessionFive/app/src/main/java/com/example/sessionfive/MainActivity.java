package com.example.sessionfive;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactClickListener {
    private List<Contact> contacts;
    private ContactsAdapter contactsAdapter;
    private ContactsDbHelper contactsDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView contactsRecyclerView = findViewById(R.id.contacts_recycler_view);
        contacts = new ArrayList<>();
        contactsDbHelper = new ContactsDbHelper(this);

        contactsAdapter = new ContactsAdapter(MainActivity.this, contacts, this);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactsRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        contactsRecyclerView.setAdapter(contactsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        Cursor cursor = contactsDbHelper.showContacts();
        while (!cursor.isAfterLast()) {
            contacts.add(new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        contactsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickContact(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getPhone()));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_contact:
                Intent intent = new Intent(this, NewContactActivity.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                finish();
                return true;
        }
        return false;
    }
}