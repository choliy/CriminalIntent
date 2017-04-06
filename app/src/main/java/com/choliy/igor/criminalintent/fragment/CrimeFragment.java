package com.choliy.igor.criminalintent.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.choliy.igor.criminalintent.Crime;
import com.choliy.igor.criminalintent.CrimeUtils;
import com.choliy.igor.criminalintent.R;
import com.choliy.igor.criminalintent.data.CrimeConstants;
import com.choliy.igor.criminalintent.data.CrimeLab;

import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mCrimeTitle;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(CrimeConstants.ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(CrimeConstants.ARG_CRIME_ID);
        mCrime = CrimeLab.getInstance(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crime, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mCrimeTitle = (EditText) view.findViewById(R.id.crimeTitle);
        mCrimeTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton = (Button) view.findViewById(R.id.crimeDate);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker(CrimeConstants.DATE_PICKER_TYPE);
            }
        });

        mTimeButton = (Button) view.findViewById(R.id.crimeTime);
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker(CrimeConstants.TIME_PICKER_TYPE);
            }
        });

        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.crimeSolved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        mCrimeTitle.setText(mCrime.getTitle());
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        updateDate();
        updateTime();
    }

    @Override
    public void onPause() {
        super.onPause();
        CrimeLab.getInstance(getActivity()).updateCrime(mCrime);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;

        if (requestCode == CrimeConstants.REQUEST_CODE_PICKER) {
            Date date = (Date) data.getSerializableExtra(CrimeConstants.EXTRA_DATE_TIME);
            mCrime.setDate(date);
            updateDate();
            updateTime();
        }
    }

    private void showPicker(int pickerType) {
        DateTimePickerFragment pickerFragment = DateTimePickerFragment
                .newInstance(mCrime.getDate(), pickerType);
        pickerFragment.setTargetFragment(CrimeFragment.this, CrimeConstants.REQUEST_CODE_PICKER);
        pickerFragment.show(getActivity().getSupportFragmentManager(), CrimeConstants.TAG_DIALOG);
    }

    private void updateDate() {
        String formattedDate = CrimeUtils.formatDate(mCrime.getDate());
        mDateButton.setText(formattedDate);
    }

    private void updateTime() {
        String formattedDate = CrimeUtils.formatTime(getActivity(), mCrime.getDate());
        mTimeButton.setText(formattedDate);
    }
}