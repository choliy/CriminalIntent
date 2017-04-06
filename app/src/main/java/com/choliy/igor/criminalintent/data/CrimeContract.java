package com.choliy.igor.criminalintent.data;

import android.provider.BaseColumns;

final class CrimeContract implements BaseColumns {

    static final String TABLE_NAME = "crimes";
    static final String COLUMN_UUID = "uuid";
    static final String COLUMN_TITLE = "title";
    static final String COLUMN_DATE = "date";
    static final String COLUMN_SOLVED = "solved";
}