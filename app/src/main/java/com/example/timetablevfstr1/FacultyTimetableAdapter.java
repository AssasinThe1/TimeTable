package com.example.timetablevfstr1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FacultyTimetableAdapter extends RecyclerView.Adapter<FacultyTimetableAdapter.ViewHolder> {

    private List<String> timetableList;
    private Context context;

    public FacultyTimetableAdapter(List<String> timetableList, Context context) {
        this.timetableList = timetableList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faculty_timetable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String timetableItem = timetableList.get(position);
        holder.textViewTimetableItem.setText(timetableItem);
    }

    @Override
    public int getItemCount() {
        return timetableList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTimetableItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTimetableItem = itemView.findViewById(R.id.text_view_timetable_item);
        }
    }
}