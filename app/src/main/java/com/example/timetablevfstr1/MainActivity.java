package com.example.timetablevfstr1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import api.ApiService;
import api.RetrofitClientInstance;
import model.RequestData;
import model.RequestLecturerData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// ...

public class MainActivity extends AppCompatActivity {
    private LinearLayout studentSectionLayout;
    private LinearLayout facultySectionLayout;
    private Spinner sectionSpinner;

    private Spinner facultySpinner;
    private String selectedYear;
    private final String[] secondYearSections = {"AIML-A", "AIML-B", "CS", "CSBS", "DS"};
    private final String[] thirdYearSections = {"AIML-A", "AIML-B", "CS", "CSBS"};
    private final String[] lecturerNames = {" Dr B Jyotshna Devi", " Ms R Naga Sirisha", " Ms P Naga Sravanthi", " Dr Brij Kishor Tiwari", " Ms U Nandini", " Mr S Krishna Kishore", " Dr Jawad Ahmed Dar", " Ms S Radharani", " Dr S Bala Krishna", " Dr M Nirupama Bhat", " Dr. Arul Elango G", " Mr C Elangovan", " Mr Amar J", " Dr Ch Amarendra", " Mr N Murali Krishna", " Ms A Hema Latha", " Mr. John Bob Gali", " Dr Arnab De", " Dr. Hilly Gohain Baruah", " Mr Akash Mishra", " Dr. P.M. Benson Mansingh", " Mr P Ramdass", " Dr D Radha Rani", " Dr SK Satpathy", " Dr A Britto Manoj", " Mr P Srinivasa Rao", " Prof M M Naidu", " Dr TH Lal Rokhawma", " Dr Mohammed Shameem", " Mr DS Bhupal Naik"};



        private RecyclerView recyclerViewYears;
        private RecyclerView recyclerViewFaculty;
        private YearAdapter yearAdapter;
        private FacultyAdapter facultyAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            recyclerViewYears = findViewById(R.id.recycler_view_years);
            recyclerViewFaculty = findViewById(R.id.recycler_view_faculty);

            // Set up the year RecyclerView
            recyclerViewYears.setLayoutManager(new LinearLayoutManager(this));
            yearAdapter = new YearAdapter(getYears(), this);
            recyclerViewYears.setAdapter(yearAdapter);

            // Set up the faculty RecyclerView
            recyclerViewFaculty.setLayoutManager(new LinearLayoutManager(this));
            facultyAdapter = new FacultyAdapter(getFacultyList(), this);
            recyclerViewFaculty.setAdapter(facultyAdapter);

            Button adminButton = findViewById(R.id.button_admin);
            adminButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ExtraFeaturesActivity.class);
                    startActivity(intent);
                }
            });
        }

        private List<String> getYears() {
            // Return the list of years
            return Arrays.asList("2nd Year", "3rd Year", "4th Year");
        }

        private List<String> getFacultyList() {
            // Return the list of faculty members
            return Arrays.asList(lecturerNames);
        }
    }
