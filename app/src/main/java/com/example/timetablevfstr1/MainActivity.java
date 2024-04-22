package com.example.timetablevfstr1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        studentSectionLayout = findViewById(R.id.include_year_section);
        facultySectionLayout = findViewById(R.id.include_faculty_section);
        sectionSpinner = findViewById(R.id.spinner_section1);
        facultySpinner = findViewById(R.id.spinner_section);
        // Set up button click listeners
        setupYearButtons();
        setupFacultyButton();

        Button submitButton = findViewById(R.id.button_submit_section);
        Button submitFacultyButton = findViewById(R.id.button_submit_faculty);
        submitFacultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedLecturer = facultySpinner.getSelectedItem().toString();
                Log.d("MainActivity", "Selected lecturer: " + selectedLecturer);
                RequestLecturerData requestLecturerData = new RequestLecturerData(selectedLecturer);
                sendRequestToServer(requestLecturerData, selectedLecturer, true);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedSection = sectionSpinner.getSelectedItem().toString();
                Log.d("MainActivity", "Selected section: " + selectedSection + " for year: " + selectedYear);
                RequestData requestData = new RequestData(selectedYear, selectedSection);
                sendRequestToServer(requestData, selectedSection, false);
            }
        });
        Button extraFeaturesButton = findViewById(R.id.button_extra_features);
        extraFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExtraFeaturesActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setupYearButtons() {
        Button buttonSecondYear = findViewById(R.id.button_second_year);
        Button buttonThirdYear = findViewById(R.id.button_third_year);
        Button buttonFourthYear = findViewById(R.id.button_fourth_year);

        buttonSecondYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearSection();
                selectedYear = "2";
                updateSectionSpinner(secondYearSections);
            }
        });

        buttonThirdYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearSection();
                selectedYear = "3";
                updateSectionSpinner(thirdYearSections);
            }
        });

        buttonFourthYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearSection();
                // If you have a specific array for fourth-year sections, update the spinner here
                // updateSectionSpinner(fourthYearSections);
            }
        });
    }

    private void setupFacultyButton() {
        Button buttonFaculty = findViewById(R.id.button_faculty);
        buttonFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFacultySection();
                updateFacultySpinner(lecturerNames);
            }
        });
    }

    private void updateSectionSpinner(String[] sections) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sections);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionSpinner.setAdapter(adapter);
    }

    private void updateFacultySpinner(String[] facultyNames) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, facultyNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultySpinner.setAdapter(adapter);
    }

    private void showYearSection() {
        studentSectionLayout.setVisibility(View.VISIBLE);
        facultySectionLayout.setVisibility(View.GONE);
    }

    private void showFacultySection() {
        studentSectionLayout.setVisibility(View.GONE);
        facultySectionLayout.setVisibility(View.VISIBLE);

    }

    private void sendRequestToServer(Object requestData, String selectedValue, boolean isLecturerRequest) {
        ApiService service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<ResponseBody> call;

        if (isLecturerRequest) {
            RequestLecturerData requestLecturerData = (RequestLecturerData) requestData;
            call = service.getLecturerTimetable(requestLecturerData);
        } else {
            RequestData requestSectionData = (RequestData) requestData;
            call = service.getTimetable(requestSectionData);
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String jsonResponse = response.body().string();
                        Intent intent = new Intent(MainActivity.this, DisplayResponseActivity.class);
                        Log.d("MainActivity", "Response successful: " + jsonResponse);
                        intent.putExtra("timetable_json", jsonResponse);
                        intent.putExtra("selected_value", selectedValue);
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d("MainActivity", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("MainActivity", "Request failed");
                t.printStackTrace();
            }
        });
    }
}