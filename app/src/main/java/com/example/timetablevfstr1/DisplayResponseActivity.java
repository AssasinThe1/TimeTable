package com.example.timetablevfstr1;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DisplayResponseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_response);

        String timetableJson = getIntent().getStringExtra("timetable_json");
        List<List<String>> timetableData = parseTimetableJson(timetableJson);

        Log.d("DisplayResponseActivity", "Parsed data: " + timetableData.toString());

        ViewPager2 viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, timetableData);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(adapter.days[position])
        ).attach();
    }

    public List<List<String>> parseTimetableJson(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<List<String>>>(){}.getType();
        List<List<String>> timetableData = gson.fromJson(json, type);

        // Replace "***" with empty strings
        for (List<String> day : timetableData) {
            for (int i = 0; i < day.size(); i++) {
                if (day.get(i).equals("***")) {
                    day.set(i, "");
                }
            }
        }

        return timetableData;
    }
}