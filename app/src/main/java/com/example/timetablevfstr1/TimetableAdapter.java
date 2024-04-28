package com.example.timetablevfstr1;

import android.content.Context;
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

    private List<String> timetableList;
    private Context context;

    public TimetableAdapter(List<String> timetableList, Context context) {
        this.timetableList = timetableList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable, parent, false);
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