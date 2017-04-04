package com.choliy.igor.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.choliy.igor.criminalintent.CrimeConstants;
import com.choliy.igor.criminalintent.fragment.CrimeFragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeConstants.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}