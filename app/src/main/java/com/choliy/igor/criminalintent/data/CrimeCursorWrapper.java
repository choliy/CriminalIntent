package com.choliy.igor.criminalintent.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.choliy.igor.criminalintent.Crime;

import java.util.Date;
import java.util.UUID;

class CrimeCursorWrapper extends CursorWrapper {

    CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    Crime getCrime() {
        String uuid = getString(getColumnIndex(CrimeContract.COLUMN_UUID));
        String title = getString(getColumnIndex(CrimeContract.COLUMN_TITLE));
        long date = getLong(getColumnIndex(CrimeContract.COLUMN_DATE));
        int isSolved = getInt(getColumnIndex(CrimeContract.COLUMN_SOLVED));

        Crime crime = new Crime(UUID.fromString(uuid));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);

        return crime;
    }
}