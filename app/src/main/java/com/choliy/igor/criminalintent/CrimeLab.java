package com.choliy.igor.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);
//            mCrimes.add(crime);
//        }
    }

    public static CrimeLab getInstance(Context context) {
        if (sCrimeLab == null) sCrimeLab = new CrimeLab(context);
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID crimeId) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(crimeId)) return crime;
        }
        return null;
    }

    public int getCrimeIndex(UUID crimeId) {
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) return i;
        }
        return 0;
    }

    public void addCrime(Crime crime) {
        mCrimes.add(crime);
    }
}