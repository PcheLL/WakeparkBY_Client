package com.wakeparkby.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wakeparkby.Activity.SeasonTickets.SeasonTicketsAdapter;
import com.wakeparkby.Activity.SeasonTickets.SeasonTicketsItem;
import com.wakeparkby.Controller.SeasonTicketController;
import com.wakeparkby.HTTPController.SeasonTicketHistory;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentSeasonTickets extends Fragment {
    private String number =  "375336416565";
    TextView textViewSeasonTicketsTime;
    RelativeLayout relativeLayoutProgressBarSeasonTicket;
    SeasonTicketController seasonTicketController;
    LinearLayout linearLayoutTimeSeasonTicket;
    RecyclerView recyclerViewSeasonTicketsHistory;
    SeasonTicketsAdapter adapterSeasonTicketHistory;
    private List<SeasonTicketHistory> seasonTicketHistoryList = new ArrayList<>();
    List<SeasonTicketsItem> mData;
    Observer observer = new Observer("SeasonTicket") {

        /**
         * override method of Observer class with new reaction for notify observers
         */

        @Override
        public void update() {
            int n = observer.getStatus();
            if (n == 10) {
                if (observer.getId() == 6) {
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
        relativeLayoutProgressBarSeasonTicket = rootView.findViewById(R.id.relativeLayoutProgressBarSeasonTicket);
        linearLayoutTimeSeasonTicket = rootView.findViewById(R.id.linearLayoutTimeSeasonTicket);
        relativeLayoutProgressBarSeasonTicket.setVisibility(View.VISIBLE);
        linearLayoutTimeSeasonTicket.setVisibility(View.GONE);
        recyclerViewSeasonTicketsHistory = rootView.findViewById(R.id.recyclerViewSeasonTicketsHistory);
        recyclerViewSeasonTicketsHistory.setVisibility(View.GONE);
        seasonTicketController = new SeasonTicketController();
        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle("Мой абонемент");
        return rootView;
    }

    private void updateSeasonTicket() {
        textViewSeasonTicketsTime.setText(seasonTicketController.getSeasonTicket());
        seasonTicketHistoryList = SeasonTicketController.getSeasonTicketHistoryList();
        adapterSeasonTicketHistory = new SeasonTicketsAdapter(getContext(),seasonTicketHistoryList);
        recyclerViewSeasonTicketsHistory.setAdapter(adapterSeasonTicketHistory);
        recyclerViewSeasonTicketsHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        relativeLayoutProgressBarSeasonTicket.setVisibility(View.GONE);
        linearLayoutTimeSeasonTicket.setVisibility(View.VISIBLE);
        recyclerViewSeasonTicketsHistory.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        observer.removeFromList(observer);
    }
}
