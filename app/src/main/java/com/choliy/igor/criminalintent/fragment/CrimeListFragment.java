package com.choliy.igor.criminalintent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.choliy.igor.criminalintent.Crime;
import com.choliy.igor.criminalintent.CrimeAdapter;
import com.choliy.igor.criminalintent.CrimeConstants;
import com.choliy.igor.criminalintent.CrimeLab;
import com.choliy.igor.criminalintent.R;
import com.choliy.igor.criminalintent.activity.CrimePagerActivity;

import java.util.List;
import java.util.UUID;

public class CrimeListFragment extends Fragment implements CrimeAdapter.OnCrimeClickListener {

    private RecyclerView mRecyclerView;
    private CrimeAdapter mAdapter;
    private int mCrimePosition;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null)
            mSubtitleVisible = savedInstanceState.getBoolean(CrimeConstants.SAVED_SUBTITLE_VISIBLE);
        return inflater.inflate(R.layout.fragment_crime_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Crime> crimes = CrimeLab.getInstance(getActivity()).getCrimes();
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(getActivity(), crimes, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyItemChanged(mCrimePosition);
        }

        updateSubtitle();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.clearContext();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menuShowSubtitle);
        if (mSubtitleVisible) subtitleItem.setTitle(R.string.menu_hide_subtitle);
        else subtitleItem.setTitle(R.string.menu_show_subtitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNewCrime:
                Crime crime = new Crime();
                CrimeLab.getInstance(getActivity()).addCrime(crime);
                Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
                intent.putExtra(CrimeConstants.EXTRA_CRIME_ID, crime.getId());
                startActivity(intent);
                return true;
            case R.id.menuShowSubtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CrimeConstants.SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCrimeClick(UUID crimeId, int crimePosition) {
        mCrimePosition = crimePosition;
        Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
        intent.putExtra(CrimeConstants.EXTRA_CRIME_ID, crimeId);
        startActivity(intent);
    }

    private void updateSubtitle() {
        int crimeCount = CrimeLab.getInstance(getActivity()).getCrimes().size();
        String subtitle = getResources()
                .getQuantityString(R.plurals.menu_subtitle_plural_format, crimeCount, crimeCount);
        if (!mSubtitleVisible) subtitle = null;

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }
}