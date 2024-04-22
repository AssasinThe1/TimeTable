package com.example.timetablevfstr1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import api.ApiService;

import api.RetrofitClientInstance;
import model.EmptyClassroomsRequest;
import model.RoomTimetableRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmptyClassroomsActivity extends AppCompatActivity {

    private Spinner daySpinner;
    private Spinner periodSpinner;
    private TextView emptyClassroomsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_classrooms);

        daySpinner = findViewById(R.id.spinner_day);
        periodSpinner = findViewById(R.id.spinner_period);
        emptyClassroomsTextView = findViewById(R.id.text_view_empty_classrooms);

        // Set up the day spinner
        String[] days = {"MON", "TUE", "WED", "THU", "FRI", "SAT"};
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        // Set up the period spinner
        String[] periods = {"1", "2", "3", "4", "5", "6", "7"};
        ArrayAdapter<String> periodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, periods);
        periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodSpinner.setAdapter(periodAdapter);

        Button getEmptyClassroomsButton = findViewById(R.id.button_get_empty_classrooms);
        getEmptyClassroomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDay = daySpinner.getSelectedItem().toString();
                String selectedPeriod = periodSpinner.getSelectedItem().toString();
                getEmptyClassrooms(selectedDay, selectedPeriod);
            }
        });
    }

    private void getEmptyClassrooms(String day, String period) {
        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        EmptyClassroomsRequest request = new EmptyClassroomsRequest(day, period);
        Call<List<String>> call = apiService.getEmptyClassrooms(request);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {

                    List<String> emptyClassrooms = response.body();
                    displayEmptyClassrooms(emptyClassrooms);

                } else {
                    // Handle error
                    Log.d("EmptyClassroomsActivity", "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void displayEmptyClassrooms(List<String> emptyClassrooms) {
        StringBuilder sb = new StringBuilder();
        if (emptyClassrooms.isEmpty()) {
            sb.append("No empty classrooms found for the selected day and period.");
        } else {
            sb.append("Empty classrooms:\n");
        }
        for (String classroom : emptyClassrooms) {
            sb.append(classroom).append("\n");
        }
        emptyClassroomsTextView.setText(sb.toString());
    }
}