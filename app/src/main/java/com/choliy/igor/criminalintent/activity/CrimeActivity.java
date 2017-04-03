package com.choliy.igor.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.choliy.igor.criminalintent.fragment.CrimeFragment;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}