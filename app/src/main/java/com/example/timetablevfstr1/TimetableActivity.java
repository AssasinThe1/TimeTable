package com.example.timetablevfstr1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class TimetableActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTimetable;
    private TimetableAdapter timetableAdapter;
    private String selectedSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        selectedSection = getIntent().getStringExtra("section");

        TextView textViewSection = findViewById(R.id.text_view_section);
        textViewSection.setText(selectedSection);

        recyclerViewTimetable = findViewById(R.id.recycler_view_timetable);
        recyclerViewTimetable.setLayoutManager(new LinearLayoutManager(this));
        timetableAdapter = new TimetableAdapter(getTimetable(), this);
        recyclerViewTimetable.setAdapter(timetableAdapter);
    }

    private List<String> getTimetable() {
        // Return the timetable data based on the selected section
        // You can fetch this data from an API or local storage
        // For simplicity, we'll return a dummy list
        return Arrays.asList("Monday - 9:00 AM - Subject 1", "Tuesday - 10:00 AM - Subject 2", "Wednesday - 11:00 AM - Subject 3");
    }
}