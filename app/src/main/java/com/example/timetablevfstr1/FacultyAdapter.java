package com.example.timetablevfstr1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.ViewHolder> {

    private List<String> facultyList;
    private Context context;

    public FacultyAdapter(List<String> facultyList, Context context) {
        this.facultyList = facultyList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faculty, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String faculty = facultyList.get(position);
        holder.textViewFaculty.setText(faculty);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FacultyTimetableActivity.class);
                intent.putExtra("faculty", faculty);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFaculty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFaculty = itemView.findViewById(R.id.text_view_faculty);
        }
    }
}