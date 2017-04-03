package com.choliy.igor.criminalintent.fragment;

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

public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mCrimeTitle;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
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
        String formattedDate = CrimeUtils.formatDate(getActivity(), mCrime.getDate());
        mDateButton.setText(formattedDate);
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.crimeSolved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
    }
}