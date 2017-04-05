package com.choliy.igor.criminalintent.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.choliy.igor.criminalintent.Crime;
import com.choliy.igor.criminalintent.CrimeConstants;
import com.choliy.igor.criminalintent.CrimeLab;
import com.choliy.igor.criminalintent.R;
import com.choliy.igor.criminalintent.fragment.CrimeFragment;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private List<Crime> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mCrimes = CrimeLab.getInstance(this).getCrimes();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeConstants.EXTRA_CRIME_ID);
        int crimePosition = CrimeLab.getInstance(this).getCrimeIndex(crimeId);
        viewPager.setCurrentItem(crimePosition);
    }
}