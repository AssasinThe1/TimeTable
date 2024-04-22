package com.example.timetablevfstr1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import api.ApiService;
import api.RetrofitClientInstance;
import model.RoomTimetableRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomTimetableActivity extends AppCompatActivity {

    private Spinner roomSpinner;
    private RecyclerView timetableRecyclerView;
    private TimetableAdapter timetableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_timetable);

        roomSpinner = findViewById(R.id.spinner_room);
        timetableRecyclerView = findViewById(R.id.recycler_view_timetable);

        // Set up the room spinner with room names
        String[] rooms = {"N - 601", "N - 607", "N - 608", "N - 501 A", "N - 501 B", "N - 502", "N - 402", "N - 602", "N - 604", "N - 605", "N - 606"};
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rooms);
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomSpinner.setAdapter(roomAdapter);

        // Set up the RecyclerView and adapter
        timetableRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        timetableAdapter = new TimetableAdapter(new ArrayList<>(), new ArrayList<>());
        timetableRecyclerView.setAdapter(timetableAdapter);

        Button getTimetableButton = findViewById(R.id.button_get_timetable);
        getTimetableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedRoom = roomSpinner.getSelectedItem().toString();
                getRoomTimetable(selectedRoom);
            }
        });
    }

    private void getRoomTimetable(String room) {
        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        RoomTimetableRequest request = new RoomTimetableRequest(room);
        Call<List<List<String>>> call = apiService.getRoomTimetable(request);
        call.enqueue(new Callback<List<List<String>>>() {
            @Override
            public void onResponse(Call<List<List<String>>> call, Response<List<List<String>>> response) {
                if (response.isSuccessful()) {
                    Log.d("RoomTimetableActivity", "onResponse: " + response.body());
                    List<List<String>> timetable = response.body();
                    updateTimetableAdapter(timetable);
                } else {
                    Log.d("RoomTimetableActivity", "onResponse: " + response.errorBody());
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<List<List<String>>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void updateTimetableAdapter(List<List<String>> timetable) {
        List<String> periods = new ArrayList<>();
        List<String> times = new ArrayList<>();

        for (List<String> entry : timetable) {
            String day = entry.get(0);
            for (int i = 1; i < entry.size(); i++) {
                String subject = entry.get(i);
                if (!subject.equals(day)) {
                    periods.add(subject);
                    times.add(getTimeSlot(i));
                }
            }
        }

        timetableAdapter.updateData(periods, times);
    }

    private String getTimeSlot(int index) {
        switch (index) {
            case 1:
                return "08:15-9:10";
            case 2:
                return "9:10-10:05";
            case 3:
                return "10:20-11:15";
            case 4:
                return "11:15-12:10";
            case 5:
                return "12.10-01.05";
            case 6:
                return "02:00-02:55";
            case 7:
                return "02:55-03:50";
            default:
                return "";
        }
    }
}