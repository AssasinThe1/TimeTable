package com.example.timetablevfstr1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ExtraFeaturesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_features);

        Button freeLecturersButton = findViewById(R.id.button_free_lecturers);
        freeLecturersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtraFeaturesActivity.this, FreeLecturersActivity.class);
                startActivity(intent);
            }
        });

        Button emptyRoomButton = findViewById(R.id.button_empty_room);
        emptyRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtraFeaturesActivity.this, EmptyClassroomsActivity.class);
                startActivity(intent);
            }
        });

        Button roomTimetableButton = findViewById(R.id.button_room_timetable);
        roomTimetableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtraFeaturesActivity.this, RoomTimetableActivity.class);
                startActivity(intent);
            }
        });
    }
}