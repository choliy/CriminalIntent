package com.choliy.igor.criminalintent;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class CrimeUtils {

    public static String formatDate(Context context, Date date) {
        SimpleDateFormat sdf;
        if (DateFormat.is24HourFormat(context))
            sdf = new SimpleDateFormat(CrimeConstants.INFO_DATE_FORMAT_UK, Locale.UK);
        else
            sdf = new SimpleDateFormat(CrimeConstants.INFO_DATE_FORMAT_US, Locale.US);

        return sdf.format(date);
    }
}