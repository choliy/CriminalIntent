package com.choliy.igor.criminalintent.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.choliy.igor.criminalintent.Crime;
import com.choliy.igor.criminalintent.CrimeAdapter;
import com.choliy.igor.criminalintent.R;
import com.choliy.igor.criminalintent.data.CrimeLab;

import java.util.List;
import java.util.UUID;

public final class InfoUtils {

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

    public static void photoDialog(Context context, Bitmap bitmap) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        String titleText;
        if (bitmap == null) {
            titleText = context.getString(R.string.dialog_no_photo_title);
        } else {
            final View view = View.inflate(context, R.layout.dialog_photo, null);
            final ImageView photo = (ImageView) view.findViewById(R.id.bigCrimePhoto);
            titleText = context.getString(R.string.dialog_big_photo_title);
            photo.setImageBitmap(bitmap);
            builder.setView(view);
        }

        builder.setTitle(titleText);
        builder.setNegativeButton(
                R.string.dialog_delete_close,
                new DialogInterface.OnClickListener() {
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