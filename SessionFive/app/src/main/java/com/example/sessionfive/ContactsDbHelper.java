package com.example.sessionfive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class ContactsDbHelper extends SQLiteOpenHelper {
    private final String TABLE_NAME = "contacts";
    private final String ID = "id";
    private final String NAME = "name";
    private final String PHONE = "phone";
    private SQLiteDatabase sqLiteDatabase;

    public ContactsDbHelper(Context context) {
        super(context, context.getString(R.string.app_name), null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(" + ID + " integer primary key, " + NAME + " text not null, " + PHONE + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void createContact(String name, String phone) {
        ContentValues row = new ContentValues();
        row.put(NAME, name);
        row.put(PHONE, phone);
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME, null, row);
        sqLiteDatabase.close();
    }

    public Cursor showContacts() {
        sqLiteDatabase = getReadableDatabase();
        String[] rowDetails = {ID, NAME, PHONE};
        Cursor curs = sqLiteDatabase.query(TABLE_NAME, rowDetails, null, null, null, null, null);
        if (curs != null)
            curs.moveToFirst();
        sqLiteDatabase.close();
        return curs;
    }
}
