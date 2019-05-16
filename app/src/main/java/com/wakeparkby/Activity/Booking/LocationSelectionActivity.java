package com.wakeparkby.Activity.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.R;

public class LocationSelectionActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    Button buttonDrozdy;
    Button buttonLogoysk;
    private float fromPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_selection);
        buttonDrozdy = findViewById(R.id.buttonDrozdy);
        buttonDrozdy.setOnClickListener(this);
        buttonLogoysk = findViewById(R.id.buttonLogoysk);
        buttonLogoysk.setOnClickListener(this);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutLocationSelection);
        relativeLayout.setOnTouchListener(this);
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
    public boolean onTouch(View view, MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                fromPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();
                if (fromPosition < toPosition)
                {
                    Intent intent = new Intent(this, MainMenuActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.go_prev_in,R.anim.go_prev_out);
                }
            default:
                break;
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.go_prev_in,R.anim.go_prev_out);
    }
}
