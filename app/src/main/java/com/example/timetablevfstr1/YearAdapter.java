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

public class YearAdapter extends RecyclerView.Adapter<YearAdapter.ViewHolder> {

    private List<String> yearList;
    private Context context;

    public YearAdapter(List<String> yearList, Context context) {
        this.yearList = yearList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_year, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String year = yearList.get(position);
        holder.textViewYear.setText(year);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SectionsActivity.class);
                intent.putExtra("year", year);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return yearList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewYear = itemView.findViewById(R.id.text_view_year);
        }
    }
}