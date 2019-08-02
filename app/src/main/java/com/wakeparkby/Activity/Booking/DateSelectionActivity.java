package com.wakeparkby.Activity.Booking;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.R;

public class DateSelectionActivity  {
    DatePicker datePicker;
    Button buttonSelectDate;
    private String date;
    private float fromPosition;


  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        buttonSelectDate = findViewById(R.id.buttonSelectDate);
        datePicker = findViewById(R.id.datePicker);
        buttonSelectDate.setOnClickListener(this);
        long now = System.currentTimeMillis() - 1000;
        datePicker.setMinDate(now);
        datePicker.setMaxDate(now + (1000 * 60 * 60 * 24 * 5));
        RelativeLayout relativeLayoutCalendar = findViewById(R.id.relativeLayoutCalendar);
        relativeLayoutCalendar.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        int mDay = datePicker.getDayOfMonth();
        int mMonth = datePicker.getMonth() + 1;
        int mYear = datePicker.getYear();
        if (mDay < 10) {
            if (mMonth < 10) {
                this.date = "0" + mDay + ".0" + mMonth + "." + mYear;
            } else {
                this.date = "0" + mDay + "." + mMonth + "." + mYear;
            }
        } else if (mMonth < 10) {
            this.date = mDay + ".0" + mMonth + "." + mYear;
        } else {
            this.date = mDay + "." + mMonth + "." + mYear;
        }
        Intent intent_Reverse = new Intent(this, ReverseCableSelectionActivity.class);
        intent_Reverse.putExtra("place", getIntent().getStringExtra("place"));
        intent_Reverse.putExtra("date", date);
        BookingController.start(this, intent_Reverse);
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
    }*/
}
