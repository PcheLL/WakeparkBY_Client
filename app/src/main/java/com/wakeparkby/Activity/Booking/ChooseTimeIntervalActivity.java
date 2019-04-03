package com.wakeparkby.Activity.Booking;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseTimeIntervalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerStartHours;
    Spinner spinnerStartMinutes;
    Spinner spinnerEndHours;
    Spinner spinnerEndMinutes;

    List<String> startHoursIntervalList = new ArrayList<>();
    List<String> startMinutesIntervalList = new ArrayList<>();
    List<String> endHoursIntervalList = new ArrayList<>();
    List<String> endMinutesIntervalList = new ArrayList<>();
    int startTime = 0;
    int endTime = 0;
    int startHours = 0;
    int startMinutes = 0;
    int endHours = 0;
    int endMinutes = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time_intervel);
        spinnerStartHours = findViewById(R.id.SpinnerFirstHours);
        spinnerStartMinutes = findViewById(R.id.SpinnerFirstMinutes);
        spinnerEndHours = findViewById(R.id.SpinnerSecondHours);
        spinnerEndMinutes = findViewById(R.id.SpinnerSecondMinutes);
        spinnerStartHours.setOnItemSelectedListener(this);
        spinnerStartMinutes.setOnItemSelectedListener(this);
        spinnerEndHours.setOnItemSelectedListener(this);
        spinnerEndMinutes.setOnItemSelectedListener(this);
        updateSpinner();
    }

    private void updateSpinner() {

        startHours = getIntent().getIntExtra("startHours", 0);
        startMinutes = getIntent().getIntExtra("startMinutes", 0);
        endMinutes = getIntent().getIntExtra("endMinutes", 0);
        endHours = getIntent().getIntExtra("endHours", 0);
        startTime = startHours * 60 + startMinutes;
        endTime = endHours * 60 + endMinutes;

        for (int i = startHours; i <= endHours; i++) {
            if (endMinutes == 0 && i == endHours)
                continue;
            startHoursIntervalList.add(String.valueOf(i));
            endHoursIntervalList.add(String.valueOf(i));
            int firstTime = 0;
            if (i != startHours)
                firstTime = 0;
            int lastTime = 60;
            if (endMinutes > 0 && i == endHours)
                lastTime = endMinutes;
        }
        for (int k = 0; k < 60; k += 5) {
            startMinutesIntervalList.add(String.valueOf(k));
            endMinutesIntervalList.add(String.valueOf(k));
        }
        ArrayAdapter<String> startHoursIntervalListAdapter = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                R.layout.text_view,
                startHoursIntervalList.toArray(new String[startHoursIntervalList.size()]));
        ArrayAdapter<String> startMinutesIntervalListAdapter = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                R.layout.text_view,
                startMinutesIntervalList.toArray(new String[startMinutesIntervalList.size()]));
        ArrayAdapter<String> endHoursIntervalListAdapter = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                R.layout.text_view,
                endHoursIntervalList.toArray(new String[endHoursIntervalList.size()]));
        ArrayAdapter<String> endMinutesIntervalListAdapter = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                R.layout.text_view,
                endMinutesIntervalList.toArray(new String[endMinutesIntervalList.size()]));
        spinnerStartHours.setAdapter(startHoursIntervalListAdapter);
        spinnerStartMinutes.setAdapter(startMinutesIntervalListAdapter);
        spinnerEndHours.setAdapter(endHoursIntervalListAdapter);
        spinnerEndMinutes.setAdapter(endMinutesIntervalListAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int lastTime = 60;
        int firstTime = 0;
        switch (parent.getId()) {
            case R.id.SpinnerFirstHours:
                int selectedHours = Integer.valueOf(spinnerStartHours.getItemAtPosition(spinnerStartHours.getSelectedItemPosition()).toString());

                startMinutesIntervalList.clear();

                if (selectedHours == startHours)
                    firstTime = startMinutes;
                if (selectedHours == endHours)
                    lastTime = endMinutes;

                for (int i = firstTime; i < lastTime; i++) {
                    startMinutesIntervalList.add(String.valueOf(i));

                }

                break;
            case R.id.SpinnerFirstMinutes:

                break;
            case R.id.SpinnerSecondHours:

                break;
            case R.id.SpinnerSecondMinutes:

                break;

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
