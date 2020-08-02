package com.example.sessionfive;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
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

        contactsDbHelper = new ContactsDbHelper(MainActivity.this);
        contacts = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(MainActivity.this, contacts, this);

        RecyclerView contactsRecyclerView = findViewById(R.id.contacts_recycler_view);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        contactsRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
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
    public void onClick(View view, Contact contact) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getPhone()));
        startActivity(intent);
    }

    @Override
    public void onMoreClick(View view, final Contact contact) {
        PopupMenu popup = new PopupMenu(MainActivity.this, view);
        popup.getMenuInflater().inflate(R.menu.contact_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.update:
                        updateContact(contact);
                        return true;
                    case R.id.delete:
                        deleteContact(contact.getId());
                        return true;
                }
                return false;
            }
        });
        popup.show();
    }

    public void updateContact(Contact contact) {
        Intent intent = new Intent(MainActivity.this, ContactDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.CONTACT, contact);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void deleteContact(int id) {
        contactsDbHelper.deleteContact(id);
        onResume();
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
                startActivity(new Intent(MainActivity.this, ContactDetailsActivity.class));
                return true;
            case R.id.exit:
                finish();
                return true;
        }
        return false;
    }
}