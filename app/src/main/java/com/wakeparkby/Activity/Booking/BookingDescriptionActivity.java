package com.wakeparkby.Activity.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.R;

public class BookingDescriptionActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonBackToMenu;

    TextView textViewInfoLocation;
    TextView textViewInfoDate;
    TextView textViewInfoTime;
    TextView textViewCableNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_description);
        buttonBackToMenu = findViewById(R.id.buttonBackToMenu);
        buttonBackToMenu.setOnClickListener(this);
        textViewInfoLocation = findViewById(R.id.textViewInfoLocation1);
        textViewInfoDate = findViewById(R.id.textViewInfoDate1);
        textViewInfoTime = findViewById(R.id.textViewInfoTime1);
        textViewCableNumber = findViewById(R.id.textViewCableNumber1);
        textViewInfoLocation.setText(getIntent().getStringExtra("location"));
        textViewInfoDate.setText(getIntent().getStringExtra("date"));
        textViewInfoTime.setText(getIntent().getStringExtra("finalTimeInterval"));
        textViewCableNumber.setText(String.valueOf(getIntent().getIntExtra("reverseCableNumber",0)));
    }
    @Override
    public void onBackPressed(){

    }

    @Override
    public void onClick(View v) {
        Intent intent_MainMenu = new Intent(this, MainMenuActivity.class);
        BookingController.start(this,intent_MainMenu);
    }
}
