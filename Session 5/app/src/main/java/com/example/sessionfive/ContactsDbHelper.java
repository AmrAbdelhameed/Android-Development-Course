package com.example.sessionfive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class ContactsDbHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;

    public ContactsDbHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + Constants.ContactTable.TABLE_NAME
                + "(" + Constants.ContactTable.ID + " integer primary key, "
                + Constants.ContactTable.NAME + " text not null, "
                + Constants.ContactTable.PHONE + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + Constants.ContactTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void createContact(Contact contact) {
        ContentValues row = new ContentValues();
        row.put(Constants.ContactTable.NAME, contact.getName());
        row.put(Constants.ContactTable.PHONE, contact.getPhone());
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(Constants.ContactTable.TABLE_NAME, null, row);
        sqLiteDatabase.close();
    }

    public Cursor showContacts() {
        sqLiteDatabase = getReadableDatabase();
        String[] rowDetails = {Constants.ContactTable.ID, Constants.ContactTable.NAME, Constants.ContactTable.PHONE};
        Cursor curs = sqLiteDatabase.query(Constants.ContactTable.TABLE_NAME, rowDetails, null, null, null, null, null);
        if (curs != null)
            curs.moveToFirst();
        sqLiteDatabase.close();
        return curs;
    }

    public void updateContact(Contact contact) {
        ContentValues row = new ContentValues();
        row.put(Constants.ContactTable.NAME, contact.getName());
        row.put(Constants.ContactTable.PHONE, contact.getPhone());
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update(Constants.ContactTable.TABLE_NAME, row, Constants.ContactTable.ID + "='" + contact.getId() + "'", null);
        sqLiteDatabase.close();
    }

    public void deleteContact(int id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(Constants.ContactTable.TABLE_NAME, Constants.ContactTable.ID + "='" + id + "'", null);
        sqLiteDatabase.close();
    }
}
