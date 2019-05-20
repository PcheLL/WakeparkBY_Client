package com.wakeparkby.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.Controller.SeasonTicketController;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

public class FragmentSeasonTickets extends Fragment {
    private String number =  "375336416565";
    TextView textViewSeasonTicketsTime;
    RelativeLayout relativeLayoutProgressBar;
    SeasonTicketController seasonTicketController;
    LinearLayout linearLayoutTimeSeasonTicket;
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

    public FragmentSeasonTickets() {
    }

    public static FragmentSeasonTickets newInstance() {
        return new FragmentSeasonTickets();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_my_season_tickets, container, false);
        textViewSeasonTicketsTime = rootView.findViewById(R.id.textViewSeasonTicketsTime);
        seasonTicketController = new SeasonTicketController(number);
        relativeLayoutProgressBar = rootView.findViewById(R.id.relativeLayoutProgressBar);
        linearLayoutTimeSeasonTicket = rootView.findViewById(R.id.linearLayoutTimeSeasonTicket);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        linearLayoutTimeSeasonTicket.setVisibility(View.GONE);
        return rootView;
    }

    private void updateSeasonTicket() {
        textViewSeasonTicketsTime.setText(seasonTicketController.getSeasonTicket());
        relativeLayoutProgressBar.setVisibility(View.GONE);
        linearLayoutTimeSeasonTicket.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        observer.removeFromList(observer);
    }
}
