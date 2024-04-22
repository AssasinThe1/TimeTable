package com.example.timetablevfstr1;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;

import java.util.List;
import android.widget.TextView;
import android.util.Log;
import java.util.Arrays;


// ...


public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.ViewHolder> {

    private List<String> timetablePeriods;
    private List<String> times;

    public TimetableAdapter(List<String> timetablePeriods, List<String> times) {
        this.timetablePeriods = timetablePeriods;
        this.times = times;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable_period, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position >= times.size() || position >= timetablePeriods.size()) {
            // Handle case when position is out of bounds for either list
            return;
        }

        String periodSubject = timetablePeriods.get(position);
        String periodTime = times.get(position);

        holder.textViewTime.setText(periodTime);
        holder.textViewSubject.setText(periodSubject);
        // If you have more details, bind them here
    }

    @Override
    public int getItemCount() {
        // Return the smaller size between timetablePeriods and times lists
        return Math.min(timetablePeriods.size(), times.size());
    }

    public void updateData(List<String> timetablePeriods, List<String> times) {
        this.timetablePeriods = timetablePeriods;
        this.times = times;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTime;
        TextView textViewSubject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewSubject = itemView.findViewById(R.id.textViewSubject);
            // Initialize other views if you have more details
        }
    }
}