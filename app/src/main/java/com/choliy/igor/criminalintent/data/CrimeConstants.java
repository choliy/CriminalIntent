package com.choliy.igor.criminalintent.data;

public interface CrimeConstants {

    String TAG_DIALOG = "com.choliy.igor.criminalintent.dialog_date_picker";
    String EXTRA_CRIME_ID = "com.choliy.igor.criminalintent.extra_crime_id";
    String ARG_CRIME_ID = "com.choliy.igor.criminalintent.arg_crime_id";
    String SAVED_SUBTITLE_VISIBLE = "com.choliy.igor.criminalintent.subtitle";

    // Date & Time pickers constants
    String EXTRA_DATE_TIME = "com.choliy.igor.criminalintent.extra_date_time";
    String ARG_PICKER_DATE_TIME = "com.choliy.igor.criminalintent.arg_date_time";
    String ARG_PICKER_TYPE = "com.choliy.igor.criminalintent.arg_picker_type";

    int REQUEST_CODE_PICKER = 1001;
    int DATE_PICKER_TYPE = 2001;
    int TIME_PICKER_TYPE = 2002;

    // Date & Time format constants
    String DATE_FORMAT = "d MMMM yyyy";
    String TIME_FORMAT_UK = "HH:mm";
    String TIME_FORMAT_US = "h:mm a";
    String INFO_DATE_FORMAT_UK = DATE_FORMAT + " (" + TIME_FORMAT_UK + ")";
    String INFO_DATE_FORMAT_US = DATE_FORMAT + " (" + TIME_FORMAT_US + ")";
}