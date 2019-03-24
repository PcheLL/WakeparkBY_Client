package com.wakeparkby.Activity.Booking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import com.wakeparkby.ActivityController.BookingController;
import com.wakeparkby.R;

public class Booking extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout relativeLayoutChoosePlace;
    RelativeLayout relativeLayoutChooseData;

    Button buttonDrozdy;
    Button buttonLogoysk;

    DatePicker datePicker;
    Button buttonSelectDate;

    private String place;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        relativeLayoutChooseData = findViewById(R.id.relativeLayoutChooseData);
        relativeLayoutChoosePlace = findViewById(R.id.relativeLayoutChoosePlace);
        buttonDrozdy = findViewById(R.id.buttonDrozdy);
        buttonDrozdy.setOnClickListener(this);
        buttonLogoysk = findViewById(R.id.buttonLogoysk);
        buttonLogoysk.setOnClickListener(this);
        buttonSelectDate = findViewById(R.id.buttonSelectDate);
        datePicker = findViewById(R.id.datePicker);
        buttonSelectDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDrozdy:
                this.place = "DROZDI";
                relativeLayoutChoosePlace.setVisibility(View.GONE);
                relativeLayoutChooseData.setVisibility(View.VISIBLE);
                break;
            case R.id.buttonLogoysk:
                this.place = "LOGOISK";
                relativeLayoutChoosePlace.setVisibility(View.GONE);
                relativeLayoutChooseData.setVisibility(View.VISIBLE);
                break;
            case R.id.buttonSelectDate:
                int mDay = datePicker.getDayOfMonth();
                int mMonth = datePicker.getMonth() + 1;
                int mYear = datePicker.getYear();
                if (mMonth < 10) {
                    this.date = mYear + "-0" + mMonth + "-" + mDay;
                } else {
                    this.date = mYear + "-" + mMonth + "-" + mDay;
                }
                BookingController bookingController = new BookingController(place,date);
                break;
        }
    }
}
