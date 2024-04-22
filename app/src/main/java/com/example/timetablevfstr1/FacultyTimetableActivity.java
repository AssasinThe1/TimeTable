package com.example.timetablevfstr1;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class FacultyTimetableActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFacultyTimetable;
    private FacultyTimetableAdapter facultyTimetableAdapter;
    private String selectedFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_timetable);

        selectedFaculty = getIntent().getStringExtra("faculty");

        TextView textViewFaculty = findViewById(R.id.text_view_faculty);
        textViewFaculty.setText(selectedFaculty);

        recyclerViewFacultyTimetable = findViewById(R.id.recycler_view_faculty_timetable);
        recyclerViewFacultyTimetable.setLayoutManager(new LinearLayoutManager(this));
        facultyTimetableAdapter = new FacultyTimetableAdapter(getFacultyTimetable(), this);
        recyclerViewFacultyTimetable.setAdapter(facultyTimetableAdapter);
    }

    private List<String> getFacultyTimetable() {
        // Return the faculty timetable data based on the selected faculty
        // You can fetch this data from an API or local storage
        // For simplicity, we'll return a dummy list
        return Arrays.asList("Monday - 9:00 AM - Class 1", "Tuesday - 10:00 AM - Class 2", "Wednesday - 11:00 AM - Class 3");
    }
}