package com.choliy.igor.criminalintent.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choliy.igor.criminalintent.Crime;
import com.choliy.igor.criminalintent.CrimeAdapter;
import com.choliy.igor.criminalintent.CrimeLab;
import com.choliy.igor.criminalintent.R;

import java.util.List;

public class CrimeListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crime_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Crime> crimes = CrimeLab.getInstance(getActivity()).getCrimes();
        CrimeAdapter crimeAdapter = new CrimeAdapter(getActivity(), crimes);
        recyclerView.setAdapter(crimeAdapter);
    }
}