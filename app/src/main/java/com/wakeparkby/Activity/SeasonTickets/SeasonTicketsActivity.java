package com.wakeparkby.Activity.SeasonTickets;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.Controller.SeasonTicketController;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

public class SeasonTicketsActivity extends AppCompatActivity implements View.OnTouchListener {
    private String number =  "375336416565";
    TextView textViewSeasonTicketsTime;
    RelativeLayout relativeLayoutProgressBar;
    SeasonTicketController seasonTicketController;
    LinearLayout linearLayoutTimeSeasonTicket;
    private float fromPosition;
    Observer observer = new Observer("SeasonTicket") {

        /**
         * override method of Observer class with new reaction for notify observers
         */

        @Override
        public void update() {
            int n = observer.getStatus();
            if (n == 10) {
                if (observer.getId() == 3) {
                    updateSeasonTicket();
                    observer.setId(0);
                } else {
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_season_tickets);
        textViewSeasonTicketsTime = findViewById(R.id.textViewSeasonTicketsTime);
        seasonTicketController = new SeasonTicketController(number);
        relativeLayoutProgressBar = findViewById(R.id.relativeLayoutProgressBar);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutSeasonTicket);
        relativeLayout.setOnTouchListener(this);
        RelativeLayout relativeLayout1 = findViewById(R.id.relativeLayoutHistory);
        relativeLayout.setOnTouchListener(this);
        linearLayoutTimeSeasonTicket = findViewById(R.id.linearLayoutTimeSeasonTicket);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        linearLayoutTimeSeasonTicket.setVisibility(View.GONE);
        //updateSeasonTicket();
    }


    private void updateSeasonTicket() {
        textViewSeasonTicketsTime.setText(seasonTicketController.getSeasonTicket());
        relativeLayoutProgressBar.setVisibility(View.GONE);
        linearLayoutTimeSeasonTicket.setVisibility(View.VISIBLE);
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
                    observer.removeFromList(observer);
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
        observer.removeFromList(observer);
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.go_prev_in,R.anim.go_prev_out);
    }
}
