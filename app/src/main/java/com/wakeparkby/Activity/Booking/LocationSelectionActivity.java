package com.wakeparkby.Activity.Booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.Validator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.HTTPController.Booking;
import com.wakeparkby.R;

public class LocationSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonDrozdy;
    Button buttonLogoysk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_location_selection);
        buttonDrozdy = findViewById(R.id.buttonDrozdy);
        buttonDrozdy.setOnClickListener(this);
        buttonLogoysk = findViewById(R.id.buttonLogoysk);
        buttonLogoysk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent_Date = new Intent(this,DateSelectionActivity.class);
        switch (v.getId()) {
            case R.id.buttonDrozdy:
                intent_Date.putExtra("place","DROZDI");
                BookingController.start(this,intent_Date);
                break;
            case R.id.buttonLogoysk:
                intent_Date.putExtra("place","LOGOISK");
                BookingController.start(this,intent_Date);
                break;
        }
    }
}
