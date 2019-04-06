package com.wakeparkby.Activity.SeasonTickets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wakeparkby.Activity.Booking.ChooseTimeActivity;
import com.wakeparkby.Controller.SeasonTicketController;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

public class SeasonTicketsActivity extends AppCompatActivity {
    private String number =  "375336416565";
    TextView textViewSeasonTicketsTime;
    RelativeLayout relativeLayoutProgressBar;
    SeasonTicketController seasonTicketController;
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

    }


    private void updateSeasonTicket() {
        textViewSeasonTicketsTime.setText(seasonTicketController.getSeasonTicket());
        textViewSeasonTicketsTime.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.GONE);
    }
}
