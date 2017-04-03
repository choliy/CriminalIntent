package com.choliy.igor.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.choliy.igor.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}