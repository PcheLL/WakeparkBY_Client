package com.wakeparkby.Activity.SeasonTickets;

import android.os.Bundle;

import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class newSeasonTicketsActivity extends AppCompatActivity {
    RecyclerView recyclerViewSeasonTicketsHistory;
    newSeasonTicketsAdapter adapterSeasonTicketHistory;

    List<newSeasonTicketsItem> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_season_tickets);

        recyclerViewSeasonTicketsHistory = findViewById(R.id.recyclerViewSeasonTicketsHistory);
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

        adapterSeasonTicketHistory = new newSeasonTicketsAdapter(this,mData);
        recyclerViewSeasonTicketsHistory.setAdapter(adapterSeasonTicketHistory);
        recyclerViewSeasonTicketsHistory.setLayoutManager(new LinearLayoutManager(this));
    }

}
