package com.choliy.igor.criminalintent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;

import com.choliy.igor.criminalintent.data.CrimeConstants;
import com.choliy.igor.criminalintent.data.CrimeLab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class CrimeUtils {

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

    public static String formatCrimeReport(Date date) {
        return DateFormat.format(CrimeConstants.DATE_FORMAT, date).toString();
    }

    public static void deleteDialog(final Context context,
                                    final UUID uuid,
                                    final AppCompatActivity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_delete_title);
        builder.setPositiveButton(R.string.dialog_delete_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                CrimeLab.getInstance(context).deleteCrime(uuid);
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

    public static void undoSnackBar(
            final Context context,
            View view,
            final Crime crime,
            final CrimeAdapter adapter,
            final int crimePosition) {

        String text = context.getString(R.string.snack_bar_text_removed);
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.snack_bar_button, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrimeLab.getInstance(context).addCrime(crime);
                List<Crime> crimes = CrimeLab.getInstance(context).getCrimes();
                adapter.notifyItemInserted(crimePosition);
                adapter.updateCrimeList(crimes);
                adapter.emptyListVisibility();
                Snackbar.make(view, R.string.snack_bar_text_restored, Snackbar.LENGTH_SHORT).show();
            }
        });
        snackbar.show();
    }
}