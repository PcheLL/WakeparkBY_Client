package com.wakeparkby.Activity.SeasonTickets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wakeparkby.Activity.Booking.ChooseTimeActivity;
import com.wakeparkby.Controller.SeasonTicketController;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

public class SeasonTicketsActivity extends Fragment {
    private String number =  "375336416565";
    TextView textViewSeasonTicketsTime;
    RelativeLayout relativeLayoutProgressBar;
    SeasonTicketController seasonTicketController;
    Observer observer = new Observer("SeasonTicket") {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_season_tickets, container, false);
        textViewSeasonTicketsTime = view.findViewById(R.id.textViewSeasonTicketsTime);
        seasonTicketController = new SeasonTicketController(number);
        textViewSeasonTicketsTime.setText(seasonTicketController.getSeasonTicket()); //переделать . При повторном запуске не отображается
        relativeLayoutProgressBar = view.findViewById(R.id.relativeLayoutProgressBar);
        relativeLayoutProgressBar = view.findViewById(R.id.relativeLayoutProgressBar);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    private void updateSeasonTicket() {
        textViewSeasonTicketsTime.setText(seasonTicketController.getSeasonTicket());
        textViewSeasonTicketsTime.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.GONE);
    }

}
