package com.choliy.igor.criminalintent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;

import com.choliy.igor.criminalintent.data.CrimeConstants;
import com.choliy.igor.criminalintent.data.CrimeLab;

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

    public static void deleteDialog(final Context context,
                                    final int crimePosition,
                                    final AppCompatActivity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_delete_title);
        builder.setPositiveButton(R.string.dialog_delete_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                CrimeLab.getInstance(context).deleteCrime(crimePosition);
                activity.finish();
            }
        });
        builder.setNegativeButton(R.string.dialog_delete_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}