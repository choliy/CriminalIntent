package com.choliy.igor.criminalintent.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class CrimeDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    CrimeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CrimeContract.TABLE_NAME + " (" +
                CrimeContract._ID + " INTEGER PRIMARY KEY, " +
                CrimeContract.COLUMN_UUID + " INTEGER NOT NULL, " +
                CrimeContract.COLUMN_TITLE + " TEXT, " +
                CrimeContract.COLUMN_DATE + " INTEGER NOT NULL, " +
                CrimeContract.COLUMN_SOLVED + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CrimeContract.TABLE_NAME);
        onCreate(db);
    }
}