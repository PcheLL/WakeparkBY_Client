package com.wakeparkby.Activity.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseTimeIntervalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Spinner spinnerStartHours;
    Spinner spinnerStartMinutes;
    Spinner spinnerEndHours;
    Spinner spinnerEndMinutes;
    TextView textViewAllTime;
    TextView textViewStringMinutes;
    Button buttonSelectTimeInterval;
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
    int newStartTime = 0;
    int newEndTime = 0;


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
        textViewAllTime = findViewById(R.id.textViewAllTime);
        textViewStringMinutes = findViewById(R.id.textViewStringMinutes);
        buttonSelectTimeInterval = findViewById(R.id.buttonSelectTimeInterval);
        buttonSelectTimeInterval.setOnClickListener(this);
        updateChooseTimeInterval();
    }

    private void updateChooseTimeInterval() {

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
        int selectedMinutes;
        int selectedHours = 0;

        switch (parent.getId()) {
            case R.id.SpinnerFirstHours:
                selectedHours = Integer.valueOf(spinnerStartHours.getItemAtPosition(spinnerStartHours.getSelectedItemPosition()).toString());

                startMinutesIntervalList.clear();
                endMinutesIntervalList.clear();
                endHoursIntervalList.clear();

                if (selectedHours == startHours) {
                    firstTime = startMinutes;
                }
                if (selectedHours == endHours) {
                    lastTime = endMinutes;
                }

                for (int i = firstTime; i < lastTime; i += 5) {
                    startMinutesIntervalList.add(String.valueOf(i));
                    if (i != firstTime) {
                        endMinutesIntervalList.add(String.valueOf(i));
                    }
                }

                for (int i = selectedHours; i <= endHours; i++) {
                    if (endMinutes == 0 && i == endHours) {
                        break;
                    }
                    endHoursIntervalList.add(String.valueOf(i));
                }

                ArrayAdapter<String> startMinutesIntervalListAdapter = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                        R.layout.text_view,
                        startMinutesIntervalList.toArray(new String[startMinutesIntervalList.size()]));
                ArrayAdapter<String> endHoursIntervalListAdapter = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                        R.layout.text_view,
                        endHoursIntervalList.toArray(new String[endHoursIntervalList.size()]));
                ArrayAdapter<String> endMinutesIntervalListAdapter = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                        R.layout.text_view,
                        endMinutesIntervalList.toArray(new String[endMinutesIntervalList.size()]));

                spinnerStartMinutes.setAdapter(startMinutesIntervalListAdapter);
                spinnerEndHours.setAdapter(endHoursIntervalListAdapter);
                spinnerEndMinutes.setAdapter(endMinutesIntervalListAdapter);
                break;
            case R.id.SpinnerFirstMinutes:
                endMinutesIntervalList.clear();
                selectedMinutes = Integer.valueOf(spinnerStartMinutes.getItemAtPosition(spinnerStartMinutes.getSelectedItemPosition()).toString());
                if (selectedMinutes == 55) {
                    endHoursIntervalList.remove(0);
                    ArrayAdapter<String> endHoursIntervalListAdapter1 = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                            R.layout.text_view,
                            endHoursIntervalList.toArray(new String[endHoursIntervalList.size()]));
                    spinnerEndHours.setAdapter(endHoursIntervalListAdapter1);
                    endMinutesIntervalList.add(0 + "");
                }
                firstTime = (selectedMinutes + 5) % 60;
                lastTime = 60;
                if (endHoursIntervalList.size() == 1) {
                    lastTime = endMinutes;
                    lastTime += 5;
                }

                for (int i = firstTime; i < lastTime; i += 5) {
                    endMinutesIntervalList.add(i + "");
                }


                ArrayAdapter<String> endMinutesIntervalListAdapter1 = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                        R.layout.text_view,
                        endMinutesIntervalList.toArray(new String[endMinutesIntervalList.size()]));
                spinnerEndMinutes.setAdapter(endMinutesIntervalListAdapter1);
                break;
            case R.id.SpinnerSecondHours:
                int selectedEndHours = Integer.valueOf(spinnerEndHours.getItemAtPosition(spinnerEndHours.getSelectedItemPosition()).toString());


                if (selectedEndHours != selectedHours) {
                    endMinutesIntervalList.clear();
                    lastTime = 60;
                    if (selectedEndHours == endHours) {
                        lastTime = endTime;
                    }

                    for (int i = 0; i < lastTime; i += 5) {
                        endMinutesIntervalList.add(i + "");
                    }
                }
                ArrayAdapter<String> endMinutesIntervalListAdapter2 = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                        R.layout.text_view,
                        endMinutesIntervalList.toArray(new String[endMinutesIntervalList.size()]));


                spinnerEndMinutes.setAdapter(endMinutesIntervalListAdapter2);
                break;
            case R.id.SpinnerSecondMinutes:


                int a = Integer.valueOf(spinnerStartHours.getItemAtPosition(spinnerStartHours.getSelectedItemPosition()).toString());
                int b = Integer.valueOf(spinnerStartMinutes.getItemAtPosition(spinnerStartMinutes.getSelectedItemPosition()).toString());
                int a1 = Integer.valueOf(spinnerEndHours.getItemAtPosition(spinnerEndHours.getSelectedItemPosition()).toString());
                int b1 = Integer.valueOf(spinnerEndMinutes.getItemAtPosition(spinnerEndMinutes.getSelectedItemPosition()).toString());
                newStartTime = a * 60 + b;
                newEndTime = a1 * 60 + b1;
                int c = newEndTime - newStartTime;
                if (c < 0) {
                    textViewAllTime.setText("");
                    textViewStringMinutes.setText("Неправильное время");
                } else {
                    textViewAllTime.setText(String.valueOf(c));
                    textViewStringMinutes.setText(" минут");
                }
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        Intent intent_description = new Intent(this,BookingDescriptionActivity.class);
        String location = getIntent().getStringExtra("location");
        String date = getIntent().getStringExtra("date");
        int reverseCableNumber =  getIntent().getIntExtra("reverseCableNumber", 0);
        BookingController bookingController = new BookingController(date, location , reverseCableNumber , newStartTime,newEndTime, intent_description, this);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}