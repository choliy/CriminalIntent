package com.choliy.igor.criminalintent;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class CrimeUtils {

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(CrimeConstants.DATE_FORMAT, Locale.ENGLISH);
        return sdf.format(date);
    }

    public static String formatTime(Context context, Date time) {
        SimpleDateFormat sdf;
        if (DateFormat.is24HourFormat(context))
            sdf = new SimpleDateFormat(CrimeConstants.TIME_FORMAT_UK, Locale.UK);
        else
            sdf = new SimpleDateFormat(CrimeConstants.TIME_FORMAT_US, Locale.US);

        return sdf.format(time);
    }

    public static String formatListDate(Context context, Date date) {
        SimpleDateFormat sdf;
        if (DateFormat.is24HourFormat(context))
            sdf = new SimpleDateFormat(CrimeConstants.INFO_DATE_FORMAT_UK, Locale.UK);
        else
            sdf = new SimpleDateFormat(CrimeConstants.INFO_DATE_FORMAT_US, Locale.US);

        return sdf.format(date);
    }
}