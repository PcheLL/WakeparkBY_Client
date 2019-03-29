package com.wakeparkby.Activity.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.HTTPController.Booking;
import com.wakeparkby.R;

public class ReverseCableSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    Button firstCable;
    Button secondCable;
    Button thirdCable;
    BookingController bookingController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_cable);
        firstCable = findViewById(R.id.buttonFirstReverse);
        firstCable.setOnClickListener(this);
        secondCable = findViewById(R.id.buttonSecondtReverse);
        secondCable.setOnClickListener(this);
        thirdCable = findViewById(R.id.buttonThirdReverse);
        thirdCable.setOnClickListener(this);
        if (getIntent().getStringExtra("place").equals("DROZDI")){
            thirdCable.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent_Time = new Intent(this,ChooseTimeActivity.class);
        BookingController bookingController;
        switch (v.getId()) {
            case R.id.buttonFirstReverse:
                this.bookingController = new BookingController(getIntent().getStringExtra("place"),getIntent().getStringExtra("date"),
                        1,intent_Time,this);
                break;
            case R.id.buttonSecondtReverse:
                this.bookingController = new BookingController(getIntent().getStringExtra("place"),getIntent().getStringExtra("date"),
                        2,intent_Time,this);
                break;
            case R.id.buttonThirdReverse:
                this.bookingController = new BookingController(getIntent().getStringExtra("place"),getIntent().getStringExtra("date"),
                        3,intent_Time,this);
                break;
        }
    }
}
