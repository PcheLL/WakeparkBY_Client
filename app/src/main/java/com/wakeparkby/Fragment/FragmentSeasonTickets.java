package com.wakeparkby.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wakeparkby.Activity.SeasonTickets.newSeasonTicketsAdapter;
import com.wakeparkby.Activity.SeasonTickets.newSeasonTicketsItem;
import com.wakeparkby.Controller.SeasonTicketController;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentSeasonTickets extends Fragment {
    private String number =  "375336416565";
    TextView textViewSeasonTicketsTime;
    RelativeLayout relativeLayoutProgressBar;
    SeasonTicketController seasonTicketController;
    LinearLayout linearLayoutTimeSeasonTicket;
    RecyclerView recyclerViewSeasonTicketsHistory;
    newSeasonTicketsAdapter adapterSeasonTicketHistory;

    List<newSeasonTicketsItem> mData;
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
       // seasonTicketController = new SeasonTicketController(number);
        relativeLayoutProgressBar = rootView.findViewById(R.id.relativeLayoutProgressBar);
        linearLayoutTimeSeasonTicket = rootView.findViewById(R.id.linearLayoutTimeSeasonTicket);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        linearLayoutTimeSeasonTicket.setVisibility(View.GONE);

        recyclerViewSeasonTicketsHistory = rootView.findViewById(R.id.recyclerViewSeasonTicketsHistory);
        recyclerViewSeasonTicketsHistory.setVisibility(View.GONE);
        mData = new ArrayList<>();
        mData.add(new newSeasonTicketsItem("13.05.2019","10","ADDED"));
        mData.add(new newSeasonTicketsItem("16.05.2019","25","WRITE_OFF"));
        mData.add(new newSeasonTicketsItem("17.05.2019","5","WRITE_OFF"));
        mData.add(new newSeasonTicketsItem("19.05.2019","9","ADDED"));
        mData.add(new newSeasonTicketsItem("20.05.2019","7","WRITE_OFF"));
        mData.add(new newSeasonTicketsItem("21.05.2019","41","WRITE_OFF"));
        mData.add(new newSeasonTicketsItem("22.05.2019","15","ADDED"));
        mData.add(new newSeasonTicketsItem("23.05.2019","34","WRITE_OFF"));
        mData.add(new newSeasonTicketsItem("26.05.2019","12","WRITE_OFF"));
        mData.add(new newSeasonTicketsItem("27.05.2019","5","ADDED"));
        mData.add(new newSeasonTicketsItem("28.05.2019","10","WRITE_OFF"));
        mData.add(new newSeasonTicketsItem("29.05.2019","8","WRITE_OFF"));

        adapterSeasonTicketHistory = new newSeasonTicketsAdapter(getContext(),mData);
        recyclerViewSeasonTicketsHistory.setAdapter(adapterSeasonTicketHistory);
        recyclerViewSeasonTicketsHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        updateSeasonTicket();
        return rootView;
    }

    private void updateSeasonTicket() {
      //  textViewSeasonTicketsTime.setText(seasonTicketController.getSeasonTicket());
        relativeLayoutProgressBar.setVisibility(View.GONE);
        linearLayoutTimeSeasonTicket.setVisibility(View.VISIBLE);
        recyclerViewSeasonTicketsHistory.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        observer.removeFromList(observer);
    }
}
