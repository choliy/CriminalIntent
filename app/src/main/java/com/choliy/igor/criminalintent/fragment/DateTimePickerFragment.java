package com.choliy.igor.criminalintent.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.choliy.igor.criminalintent.data.CrimeConstants;
import com.choliy.igor.criminalintent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimePickerFragment extends DialogFragment implements
        DatePicker.OnDateChangedListener,
        TimePicker.OnTimeChangedListener {

    private Calendar mCalendar;

    public static DateTimePickerFragment newInstance(Date date, int pickerType) {
        Bundle args = new Bundle();
        args.putSerializable(CrimeConstants.ARG_PICKER_DATE_TIME, date);
        args.putInt(CrimeConstants.ARG_PICKER_TYPE, pickerType);
        DateTimePickerFragment dateTimePicker = new DateTimePickerFragment();
        dateTimePicker.setArguments(args);
        return dateTimePicker;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Date date = (Date) getArguments().getSerializable(CrimeConstants.ARG_PICKER_DATE_TIME);
        final int pickerType = getArguments().getInt(CrimeConstants.ARG_PICKER_TYPE);

        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);

        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);

        View pickerView = null;
        int pickerTitle = 0;

        switch (pickerType) {
            case CrimeConstants.DATE_PICKER_TYPE:
                DatePicker datePicker = new DatePicker(getActivity());
                datePicker.init(year, month, day, this);
                pickerView = datePicker;
                pickerTitle = R.string.date_picker_dialog_title;
                break;
            case CrimeConstants.TIME_PICKER_TYPE:
                TimePicker timePicker = new TimePicker(getActivity());
                if (Build.VERSION.SDK_INT >= 23) {
                    timePicker.setHour(hour);
                    timePicker.setMinute(minute);
                } else {
                    timePicker.setCurrentHour(hour);
                    timePicker.setCurrentMinute(minute);
                }
                timePicker.setOnTimeChangedListener(this);
                pickerView = timePicker;
                pickerTitle = R.string.time_picker_dialog_title;
                break;
        }

        return new AlertDialog.Builder(getActivity())
                .setView(pickerView)
                .setTitle(pickerTitle)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        updateDateTime();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .create();
    }

    @Override
    public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
        mCalendar.set(Calendar.MINUTE, minute);
    }

    private void updateDateTime() {
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);

        Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();
        sendResult(date);
    }

    private void sendResult(Date date) {
        if (getTargetFragment() == null) return;

        Intent intent = new Intent();
        intent.putExtra(CrimeConstants.EXTRA_DATE_TIME, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }
}