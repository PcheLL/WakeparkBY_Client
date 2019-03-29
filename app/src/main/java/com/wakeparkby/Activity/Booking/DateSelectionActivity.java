package com.wakeparkby.Activity.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.HTTPController.Booking;
import com.wakeparkby.R;

public class DateSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    DatePicker datePicker;
    Button buttonSelectDate;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        buttonSelectDate = findViewById(R.id.buttonSelectDate);
        datePicker = findViewById(R.id.datePicker);
        buttonSelectDate.setOnClickListener(this);
        long now = System.currentTimeMillis() - 1000;
        datePicker.setMinDate(now);
        datePicker.setMaxDate(now+(1000*60*60*24*13));
    }

    @Override
    public void onClick(View v) {
        int mDay = datePicker.getDayOfMonth();
        int mMonth = datePicker.getMonth() + 1;
        int mYear = datePicker.getYear();
                    if (mMonth < 10) {
                        this.date = mDay + "-0" + mMonth + "-" + mYear;
                    } else {
                        this.date = mDay + "-" + mMonth + "-" + mYear;
                    }
        Intent intent_Reverse = new Intent(this,ReverseCableSelectionActivity.class);
        intent_Reverse.putExtra("place",getIntent().getStringExtra("place"));
        intent_Reverse.putExtra("date",date);
        BookingController.start(this,intent_Reverse);
        startActivity(intent_Reverse);
    }
}
