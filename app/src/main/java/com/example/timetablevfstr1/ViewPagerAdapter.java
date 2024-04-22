// ViewPagerAdapter.java

package com.example.timetablevfstr1;

import androidx.viewpager2.adapter.FragmentStateAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.List;

import android.util.Log;
public class ViewPagerAdapter extends FragmentStateAdapter {

    final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private List<List<String>> timetableData;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity,
                            List<List<String>> timetableData) {
        super(fragmentActivity);
        this.timetableData = timetableData;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        List<String> timetableForDay = timetableData.get(position);
        Log.d("ViewPagerAdapter", "Creating fragment for day " + (position + 1) + " with timetable: " + timetableForDay);
        return DayFragment.newInstance(timetableForDay);
    }

    @Override
    public int getItemCount() {
        return days.length;
    }
}