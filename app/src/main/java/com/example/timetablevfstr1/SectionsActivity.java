package com.example.timetablevfstr1;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class SectionsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSections;
    private SectionAdapter sectionAdapter;
    private String selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);

        selectedYear = getIntent().getStringExtra("year");

        TextView textViewYear = findViewById(R.id.text_view_year);
        textViewYear.setText(selectedYear);

        recyclerViewSections = findViewById(R.id.recycler_view_sections);
        recyclerViewSections.setLayoutManager(new LinearLayoutManager(this));
        sectionAdapter = new SectionAdapter(getSections(), this);
        recyclerViewSections.setAdapter(sectionAdapter);
    }

    private List<String> getSections() {
        // Return the list of sections based on the selected year
        if (selectedYear.equals("2nd Year")) {
            return Arrays.asList("AIML-A", "AIML-B", "CS", "CSBS", "DS");
        } else if (selectedYear.equals("3rd Year")) {
            return Arrays.asList("AIML-A", "AIML-B", "CS", "CSBS");
        } else {
            // Return sections for 4th year
            return Arrays.asList("Section 1", "Section 2", "Section 3");
        }
    }
}