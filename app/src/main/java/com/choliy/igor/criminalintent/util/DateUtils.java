package com.choliy.igor.criminalintent.util;

import android.content.Context;
import android.text.format.DateFormat;

import com.choliy.igor.criminalintent.CrimeConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {

    public static String formatListDate(Context context, Date date) {
        SimpleDateFormat sdf;
        if (DateFormat.is24HourFormat(context))
            sdf = new SimpleDateFormat(CrimeConstants.INFO_DATE_FORMAT_UK, Locale.UK);
        else
            sdf = new SimpleDateFormat(CrimeConstants.INFO_DATE_FORMAT_US, Locale.US);

        return sdf.format(date);
    }

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

    public static String formatCrimeReport(Context context, Date date) {
        String formattedReport;
        if (DateFormat.is24HourFormat(context))
            formattedReport = DateFormat.format(CrimeConstants.INFO_DATE_FORMAT_UK, date).toString();
        else
            formattedReport = DateFormat.format(CrimeConstants.INFO_DATE_FORMAT_US, date).toString();

        return formattedReport;
    }
}