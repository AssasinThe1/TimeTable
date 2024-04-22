package com.example.timetablevfstr1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import api.ApiService;
import api.RetrofitClientInstance;
import model.FreeLecturersRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeLecturersActivity extends AppCompatActivity {

    private Spinner daySpinner;
    private Spinner timeSpinner;

    private String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private String[] timeSlots = {"1\n08:15-9:10", "2\n9:10-10:05", "3\n10:20-11:15", "4\n11:15-12:10",
            "5\n12.10-01.05", "6\n02:00-02:55", "7\n02:55-03:50"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_lecturers);

        daySpinner = findViewById(R.id.spinner_day);
        timeSpinner = findViewById(R.id.spinner_time);

        // Set up the day spinner
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        // Set up the time spinner
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeSlots);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);

        Button submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDay = daySpinner.getSelectedItem().toString();
                String selectedTime = timeSpinner.getSelectedItem().toString();
                sendFreeLecturersRequest(selectedDay, selectedTime);
            }
        });
    }

    private void sendFreeLecturersRequest(String day, String time) {
        ApiService service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        FreeLecturersRequest request = new FreeLecturersRequest(day, time);
        Call<List<String>> call = service.getFreeLecturers(request);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    List<String> freeLecturers = response.body();
                    displayFreeLecturers(freeLecturers);
                } else {
                    // Handle error response
                    Toast.makeText(FreeLecturersActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                // Handle request failure
                Toast.makeText(FreeLecturersActivity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayFreeLecturers(List<String> freeLecturers) {
        if (freeLecturers.isEmpty()) {
            Toast.makeText(this, "No free lecturers found for the selected day and time.", Toast.LENGTH_SHORT).show();
        } else {
            // Create a string to display the free lecturers
            StringBuilder sb = new StringBuilder();
            sb.append("Free Lecturers:\n");
            for (String lecturer : freeLecturers) {
                sb.append("- ").append(lecturer).append("\n");
            }

            // Display the free lecturers in a dialog or a text view
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Free Lecturers");
            builder.setMessage(sb.toString());
            builder.setPositiveButton("OK", null);
            builder.show();
        }
    }
}