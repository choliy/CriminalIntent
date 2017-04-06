package com.choliy.igor.criminalintent.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.choliy.igor.criminalintent.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private SQLiteDatabase mDatabase;

    private CrimeLab(Context context) {
        mDatabase = new CrimeDbHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public static CrimeLab getInstance(Context context) {
        if (sCrimeLab == null) sCrimeLab = new CrimeLab(context);
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursorWrapper = queryCrimes(null, null, null);

        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                crimes.add(cursorWrapper.getCrime());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }

        return crimes;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs, String orderBy) {
        Cursor cursor = mDatabase.query(
                CrimeContract.TABLE_NAME,
                null, // null will choose all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                orderBy);

        return new CrimeCursorWrapper(cursor);
    }

    public Crime getCrime(UUID crimeId) {
        CrimeCursorWrapper cursorWrapper = queryCrimes(
                CrimeContract.COLUMN_UUID + " = ?",
                new String[]{crimeId.toString()},
                null);

        try {
            if (cursorWrapper.getCount() == 0) return null;
            cursorWrapper.moveToFirst();
            return cursorWrapper.getCrime();
        } finally {
            cursorWrapper.close();
        }
    }

    public int getCrimeIndex(UUID crimeId) {
//        for (int i = 0; i < mCrimes.size(); i++) {
//            if (mCrimes.get(i).getId().equals(crimeId)) return i;
//        }
        return 0;
    }

    public void addCrime(Crime crime) {
        mDatabase.insert(
                CrimeContract.TABLE_NAME,
                null,
                getContentValues(crime));
    }

    public void updateCrime(Crime crime) {
        mDatabase.update(
                CrimeContract.TABLE_NAME,
                getContentValues(crime),
                CrimeContract.COLUMN_UUID + " = ?",
                new String[]{crime.getId().toString()});
    }

    public void deleteCrime(int crimePosition) {
        mDatabase.delete(
                CrimeContract.TABLE_NAME,
                CrimeContract._ID + " = ?",
                new String[] {String.valueOf(crimePosition)});
//        for (int i = 0; i < mCrimes.size(); i++) {
//            if (mCrimes.indexOf(mCrimes.get(i)) == crimePosition) {
//                mCrimes.remove(i);
//                break;
//            }
//        }
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeContract.COLUMN_UUID, crime.getId().toString());
        values.put(CrimeContract.COLUMN_TITLE, crime.getTitle());
        values.put(CrimeContract.COLUMN_DATE, crime.getDate().getTime());
        values.put(CrimeContract.COLUMN_SOLVED, crime.isSolved() ? 1 : 0);
        return values;
    }
}