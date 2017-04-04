package com.choliy.igor.criminalintent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choliy.igor.criminalintent.Crime;
import com.choliy.igor.criminalintent.CrimeAdapter;
import com.choliy.igor.criminalintent.CrimeConstants;
import com.choliy.igor.criminalintent.CrimeLab;
import com.choliy.igor.criminalintent.R;
import com.choliy.igor.criminalintent.activity.CrimeActivity;

import java.util.List;
import java.util.UUID;

public class CrimeListFragment extends Fragment implements CrimeAdapter.OnCrimeClickListener {

    private RecyclerView mRecyclerView;
    private CrimeAdapter mAdapter;
    private int mCrimePosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
    }

    @Override
    public void onCrimeClick(UUID id, int crimePosition) {
        mCrimePosition = crimePosition;
        Intent intent = new Intent(getActivity(), CrimeActivity.class);
        intent.putExtra(CrimeConstants.EXTRA_CRIME_ID, id);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.clearContext();
    }
}