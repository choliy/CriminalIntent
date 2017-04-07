package com.choliy.igor.criminalintent;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.choliy.igor.criminalintent.data.CrimeLab;

import java.util.List;
import java.util.UUID;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {

    private Context mContext;
    private List<Crime> mCrimes;
    private OnCrimeClickListener mClickListener;

    public CrimeAdapter(Context context, List<Crime> crimes, OnCrimeClickListener clickListener) {
        mContext = context;
        mCrimes = crimes;
        mClickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return mCrimes.size();
    }

    @Override
    public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_crime, parent, false);
        return new CrimeHolder(view);
    }

    @Override
    public void onBindViewHolder(CrimeHolder holder, int position) {
        holder.bindView(position);
    }

    public void updateCrimeList(List<Crime> crimes) {
        mCrimes = crimes;
    }

    public void emptyListVisibility() {
        LinearLayout emptyList =
                (LinearLayout) ((AppCompatActivity) mContext).findViewById(R.id.emptyList);
        if (getItemCount() > 0) emptyList.setVisibility(View.INVISIBLE);
        else emptyList.setVisibility(View.VISIBLE);
    }

    class CrimeHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener,
            CompoundButton.OnCheckedChangeListener {

        private TextView mCrimeTitle;
        private TextView mCrimeDate;
        private CheckBox mCrimeSolved;

        CrimeHolder(View itemView) {
            super(itemView);
            mCrimeTitle = (TextView) itemView.findViewById(R.id.listItemTitle);
            mCrimeDate = (TextView) itemView.findViewById(R.id.listItemDate);
            mCrimeSolved = (CheckBox) itemView.findViewById(R.id.listItemSolved);
            mCrimeSolved.setOnCheckedChangeListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            UUID crimeId = mCrimes.get(position).getId();
            mClickListener.onCrimeClick(crimeId);
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
            Crime crime = mCrimes.get(getAdapterPosition());
            crime.setSolved(checked);
            CrimeLab.getInstance(mContext).updateCrime(crime);
        }

        private void bindView(int position) {
            Crime crime = mCrimes.get(position);
            if (crime.getTitle().equals("")) mCrimeTitle.setText(R.string.crime_title_empty);
            else mCrimeTitle.setText(crime.getTitle());
            String formattedDate = CrimeUtils.formatListDate(mContext, crime.getDate());
            mCrimeDate.setText(formattedDate);
            mCrimeSolved.setChecked(crime.isSolved());
            itemView.setTag(crime.getId());
        }
    }

    public interface OnCrimeClickListener {

        void onCrimeClick(UUID crimeId);

    }
}