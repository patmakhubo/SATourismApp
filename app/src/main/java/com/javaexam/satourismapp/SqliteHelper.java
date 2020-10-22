package com.javaexam.satourismapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {
    //DATABASE NAME
    public static final String DATABASE_NAME = "satourist";
    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;
    //TABLE NAME
    public static final String TABLE_TOURISM = "touristattractions";
    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "Id";
    //COLUMN first name
    public static final String KEY_PROVINCE = "Province";
    //COLUMN last name
    public static final String KEY_PLACE = "Place";
    //SQL for creating users table
    public static final String SQL_TABLE_TOURISM =
            "CREATE TABLE \"touristattractions\" (\n" +
                    "\t\"" + KEY_ID + "\"\tINTEGER,\n" +
                    "\t\"" + KEY_PROVINCE + "\"\tTEXT,\n" +
                    "\t\"" + KEY_PLACE + "\"\tTEXT\n" +
                    ");";
    //using this method we can update the balance of the current savings account

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_TOURISM);

        addPlaces(sqLiteDatabase, new PlaceDetails(1, "Gauteng", "Union Buildings"));
        addPlaces(sqLiteDatabase, new PlaceDetails(2, "Western Cape", "Table Mountain"));
        addPlaces(sqLiteDatabase, new PlaceDetails(3, "KwaZulu Natal", "uShaka Marine World"));
        addPlaces(sqLiteDatabase, new PlaceDetails(4, "Eastern Cape", "Addo Elephant National Park"));
        addPlaces(sqLiteDatabase, new PlaceDetails(5, "Northern Cape", "Augrabies Falls National Park"));
        addPlaces(sqLiteDatabase, new PlaceDetails(6, "Mpumalanga", "Kruger National Park"));
        addPlaces(sqLiteDatabase, new PlaceDetails(7, "Limpopo", "Mapungubwe National Park"));
        addPlaces(sqLiteDatabase, new PlaceDetails(8, "North West", "Sun City Resort"));
        addPlaces(sqLiteDatabase, new PlaceDetails(9, "Free State", "Free State National Botanical Garden"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_TOURISM);
    }

    private void addPlaces(SQLiteDatabase db, PlaceDetails details) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, details.getId());
        values.put(KEY_PLACE, details.getPlace());
        values.put(KEY_PROVINCE, details.getProvince());

        db.insert(TABLE_TOURISM, null, values);
    }

    public PlaceDetails getPlaceDetails(String province) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{KEY_ID, KEY_PROVINCE, KEY_PLACE};
        //WHERE clause
        String selection = KEY_PROVINCE + "=?";
        //WHERE clause arguments
        String[] selectionArgs = {province};
        Cursor cursor = db.query(TABLE_TOURISM, columns, selection, selectionArgs, null, null, null, null);
        PlaceDetails details = new PlaceDetails();
        if (cursor != null && cursor.moveToFirst()) {
            details.setId(cursor.getInt(0));
            details.setProvince(cursor.getString(1));
            details.setPlace(cursor.getString(2));
        }
        cursor.close();
        return details;
    }
}

