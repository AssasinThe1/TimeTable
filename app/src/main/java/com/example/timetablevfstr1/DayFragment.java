package com.example.timetablevfstr1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


// ...

// DayFragment.java
public class DayFragment extends Fragment {

    private static final String ARG_TIMETABLE = "timetable";

    public static DayFragment newInstance(List<String> timetable) {
        DayFragment fragment = new DayFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_TIMETABLE, new ArrayList<>(timetable));
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView recyclerViewDayTimetable;
    private TimetableAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);

        recyclerViewDayTimetable = view.findViewById(R.id.recyclerViewDayTimetable);
        recyclerViewDayTimetable.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getArguments() != null) {
            List<String> timetable = getArguments().getStringArrayList(ARG_TIMETABLE);
            List<String> times = Arrays.asList("08:15-9:10", "9:10-10:05", "10:20-11:15", "11:15-12:10", "12.10-01.05", "02:00-02:55", "02:55-03:50");
            adapter = new TimetableAdapter(timetable, times);
            recyclerViewDayTimetable.setAdapter(adapter);
        }
        return view;
    }
}