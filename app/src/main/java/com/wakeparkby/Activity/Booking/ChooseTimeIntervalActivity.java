package com.wakeparkby.Activity.Booking;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseTimeIntervalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, View.OnTouchListener {
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
    private int finalTime = 0;
    private float fromPosition;

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
        RelativeLayout relativeLayoutCalendar = findViewById(R.id.relativeLayoutChooseTimeInterval);
        relativeLayoutCalendar.setOnTouchListener(this);
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
        startHoursIntervalListAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        startMinutesIntervalListAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        endHoursIntervalListAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        endMinutesIntervalListAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerStartHours.setAdapter(startHoursIntervalListAdapter);
        spinnerStartMinutes.setAdapter(startMinutesIntervalListAdapter);
        spinnerEndHours.setAdapter(endHoursIntervalListAdapter);
        spinnerEndMinutes.setAdapter(endMinutesIntervalListAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int fitrsMin;
        int lastMin;
        int firstHours;
        int lastHours;
        int startFitrsMin;
        int endFitrsMin;
        int startlastMin;
        int endlastMin;
        int startlastHours;
        int endlastHours;

        int selectedFirstHours = Integer.valueOf(spinnerStartHours.getItemAtPosition(spinnerStartHours.getSelectedItemPosition()).toString());


        switch (parent.getId()) {
            case R.id.SpinnerFirstHours:

                if(selectedFirstHours == startHours){
                    startFitrsMin = startMinutes;
                }
                else{
                    startFitrsMin = 0;
                }

                if (selectedFirstHours == endHours){
                    endFitrsMin = endMinutes-5;
                }
                else{
                    endFitrsMin = 55;
                }

                if(startFitrsMin != 55){
                    startlastHours = selectedFirstHours;
                }else
                {
                    startlastHours = selectedFirstHours+1;
                }

                endlastHours = endHours;

                if(startlastHours == selectedFirstHours){
                    startlastMin = startFitrsMin+5;
                }
                else{
                    startlastMin = 0;
                }

                if(startlastHours == endHours){
                    endlastMin = endMinutes;
                }
                else{
                    endlastMin=55;
                }


                startMinutesIntervalList.clear();
                endHoursIntervalList.clear();
                endMinutesIntervalList.clear();

                for(int i = startFitrsMin ; i <= endFitrsMin; i+=5){
                    startMinutesIntervalList.add(String.valueOf(i));
                }

                for (int i = startlastHours; i <= endlastHours; i++){
                    endHoursIntervalList.add(String.valueOf(i));
                }

                for(int i = startlastMin; i<= endlastMin; i+=5){
                    endMinutesIntervalList.add(String.valueOf(i));
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
                startMinutesIntervalListAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                endHoursIntervalListAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                endMinutesIntervalListAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                spinnerStartMinutes.setAdapter(startMinutesIntervalListAdapter);
                spinnerEndHours.setAdapter(endHoursIntervalListAdapter);
                spinnerEndMinutes.setAdapter(endMinutesIntervalListAdapter);
                break;
            case R.id.SpinnerFirstMinutes:

                int selectedFirstMinutes = Integer.valueOf(spinnerStartMinutes.getItemAtPosition(spinnerStartMinutes.getSelectedItemPosition()).toString());



                startlastHours = selectedFirstHours;

                if (selectedFirstMinutes == 55) {
                    startlastHours ++ ;
                }



                if(startlastHours == selectedFirstHours){
                    startlastMin = selectedFirstMinutes+5;
                }
                else{
                    startlastMin = 0;
                }

                if(startlastHours == endHours){
                    endlastMin = endMinutes;
                }
                else{
                    endlastMin=55;
                }

                endMinutesIntervalList.clear();
                endHoursIntervalList.clear();

                for(int i = startlastMin; i<= endlastMin; i+=5){
                    endMinutesIntervalList.add(String.valueOf(i));
                }

                for (int i = startlastHours; i <= endHours; i++) {
                    endHoursIntervalList.add(String.valueOf(i));
                }


                ArrayAdapter<String> endMinutesIntervalListAdapter1 = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                        R.layout.text_view,
                        endMinutesIntervalList.toArray(new String[endMinutesIntervalList.size()]));
                ArrayAdapter<String> endHoursIntervalListAdapter1 = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                        R.layout.text_view,
                        endHoursIntervalList.toArray(new String[endHoursIntervalList.size()]));
                endHoursIntervalListAdapter1.setDropDownViewResource(R.layout.spinner_dropdown_item);
                endMinutesIntervalListAdapter1.setDropDownViewResource(R.layout.spinner_dropdown_item);

                spinnerEndHours.setAdapter(endHoursIntervalListAdapter1);
                spinnerEndMinutes.setAdapter(endMinutesIntervalListAdapter1);
                break;

            case R.id.SpinnerSecondHours:
                int selectedEndHours = Integer.valueOf(spinnerEndHours.getItemAtPosition(spinnerEndHours.getSelectedItemPosition()).toString());
                selectedFirstMinutes = Integer.valueOf(spinnerStartMinutes.getItemAtPosition(spinnerStartMinutes.getSelectedItemPosition()).toString());

                if(selectedFirstHours == selectedEndHours){
                    startlastMin = selectedFirstMinutes+5;
                }
                else{
                    startlastMin = 0;
                }

                if(selectedEndHours == endHours){
                    endlastMin = endMinutes;
                }
                else{
                    endlastMin=55;
                }

                endMinutesIntervalList.clear();

                for(int i = startlastMin; i<= endlastMin; i+=5){
                    endMinutesIntervalList.add(String.valueOf(i));
                }


                ArrayAdapter<String> endMinutesIntervalListAdapter2 = new ArrayAdapter<>(ChooseTimeIntervalActivity.this,
                        R.layout.text_view,
                        endMinutesIntervalList.toArray(new String[endMinutesIntervalList.size()]));

                endMinutesIntervalListAdapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
                spinnerEndMinutes.setAdapter(endMinutesIntervalListAdapter2);
                break;
            case R.id.SpinnerSecondMinutes:


                int a = Integer.valueOf(spinnerStartHours.getItemAtPosition(spinnerStartHours.getSelectedItemPosition()).toString());
                int b = Integer.valueOf(spinnerStartMinutes.getItemAtPosition(spinnerStartMinutes.getSelectedItemPosition()).toString());
                int a1 = Integer.valueOf(spinnerEndHours.getItemAtPosition(spinnerEndHours.getSelectedItemPosition()).toString());
                int b1 = Integer.valueOf(spinnerEndMinutes.getItemAtPosition(spinnerEndMinutes.getSelectedItemPosition()).toString());
                newStartTime = a * 60 + b;
                newEndTime = a1 * 60 + b1;
                finalTime = newEndTime - newStartTime;
                if (finalTime < 0) {
                    textViewAllTime.setText("");
                    textViewStringMinutes.setText("Некорректное время");
                } else {
                    textViewAllTime.setText(String.valueOf(finalTime));
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
        if (finalTime < 0 || finalTime == 0) {
            Toast.makeText(this, "Некорректное время !", Toast.LENGTH_LONG).show();
        } else {
            Intent intent_description = new Intent(this, BookingDescriptionActivity.class);
            String location = getIntent().getStringExtra("location");
            String date = getIntent().getStringExtra("date");
            int reverseCableNumber = getIntent().getIntExtra("reverseCableNumber", 0);
            BookingController bookingController = new BookingController(date, location, reverseCableNumber, newStartTime, newEndTime, intent_description, this);
        }
    }

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fromPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();

                if (fromPosition < toPosition) {
                    super.onBackPressed();
                }
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}