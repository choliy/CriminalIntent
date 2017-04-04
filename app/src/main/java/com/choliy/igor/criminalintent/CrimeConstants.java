package com.choliy.igor.criminalintent;

public interface CrimeConstants {

    String EXTRA_CRIME_ID = "com.choliy.igor.criminalintent.extra_crime_id";
    String ARG_CRIME_ID = "com.choliy.igor.criminalintent.arg_crime_id";

    // Date & Time format constants
    String DATE_FORMAT = "d MMMM yyyy";
    String TIME_FORMAT_UK = "HH:mm";
    String TIME_FORMAT_US = "h:mm a";
    String INFO_DATE_FORMAT_UK = DATE_FORMAT + " (" + TIME_FORMAT_UK + ")";
    String INFO_DATE_FORMAT_US = DATE_FORMAT + " (" + TIME_FORMAT_US + ")";
}