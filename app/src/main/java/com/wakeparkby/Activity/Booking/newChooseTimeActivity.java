package com.wakeparkby.Activity.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class newChooseTimeActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView NewsRecyclerView;
    newChooseTimeAdapter newsAdapter;

    List<newChooseTimeItem> mData;
    MaterialButton buttonChooseTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);
        NewsRecyclerView = findViewById(R.id.recyclerViewTime);
        buttonChooseTime = findViewById(R.id.buttonChooseTime);
        buttonChooseTime.setOnClickListener(this);
        mData = new ArrayList<>();
        mData.add(new newChooseTimeItem("9","00","9","10","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("9","10","9","20","FREE"));
        mData.add(new newChooseTimeItem("9","20","9","30","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("9","30","9","40","FREE"));
        mData.add(new newChooseTimeItem("9","40","9","50","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("9","50","10","00","FREE"));

        mData.add(new newChooseTimeItem("10","00","10","10","FREE"));
        mData.add(new newChooseTimeItem("10","10","10","20","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("10","20","10","30","FREE"));
        mData.add(new newChooseTimeItem("10","30","10","40","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("10","40","10","50","FREE"));
        mData.add(new newChooseTimeItem("10","50","11","00","FREE"));

        mData.add(new newChooseTimeItem("11","00","11","10","FREE"));
        mData.add(new newChooseTimeItem("11","10","11","20","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("11","20","11","30","FREE"));
        mData.add(new newChooseTimeItem("11","30","11","40","FREE"));
        mData.add(new newChooseTimeItem("11","40","11","50","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("11","50","12","00","FREE"));

        mData.add(new newChooseTimeItem("12","00","12","10","FREE"));
        mData.add(new newChooseTimeItem("12","10","12","20","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("12","20","12","30","FREE"));
        mData.add(new newChooseTimeItem("12","30","12","40","FREE"));
        mData.add(new newChooseTimeItem("12","40","12","50","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("12","50","13","00","FREE"));

        mData.add(new newChooseTimeItem("13","00","13","10","FREE"));
        mData.add(new newChooseTimeItem("13","10","13","20","FREE"));
        mData.add(new newChooseTimeItem("13","20","13","30","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("13","30","13","40","FREE"));
        mData.add(new newChooseTimeItem("13","40","13","50","FREE"));
        mData.add(new newChooseTimeItem("13","50","14","00","WAITING_BOOKED"));

        mData.add(new newChooseTimeItem("14","00","14","10","FREE"));
        mData.add(new newChooseTimeItem("14","10","14","20","FREE"));
        mData.add(new newChooseTimeItem("14","20","14","30","FREE"));
        mData.add(new newChooseTimeItem("14","30","14","40","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("14","40","14","50","FREE"));
        mData.add(new newChooseTimeItem("14","50","15","00","FREE"));

        mData.add(new newChooseTimeItem("15","00","15","10","FREE"));
        mData.add(new newChooseTimeItem("15","10","15","20","FREE"));
        mData.add(new newChooseTimeItem("15","20","15","30","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("15","30","15","40","FREE"));
        mData.add(new newChooseTimeItem("15","40","15","50","FREE"));
        mData.add(new newChooseTimeItem("15","50","16","00","FREE"));

        mData.add(new newChooseTimeItem("16","00","16","10","FREE"));
        mData.add(new newChooseTimeItem("16","10","16","20","FREE"));
        mData.add(new newChooseTimeItem("16","20","16","30","FREE"));
        mData.add(new newChooseTimeItem("16","30","16","40","FREE"));
        mData.add(new newChooseTimeItem("16","40","16","50","FREE"));
        mData.add(new newChooseTimeItem("16","50","17","00","FREE"));

        mData.add(new newChooseTimeItem("17","00","17","10","FREE"));
        mData.add(new newChooseTimeItem("17","10","17","20","FREE"));
        mData.add(new newChooseTimeItem("17","20","17","30","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("17","30","17","40","FREE"));
        mData.add(new newChooseTimeItem("17","40","17","50","FREE"));
        mData.add(new newChooseTimeItem("17","50","18","00","FREE"));

        mData.add(new newChooseTimeItem("18","00","18","10","FREE"));
        mData.add(new newChooseTimeItem("18","10","18","20","FREE"));
        mData.add(new newChooseTimeItem("18","20","18","30","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("18","30","18","40","FREE"));
        mData.add(new newChooseTimeItem("18","40","18","50","FREE"));
        mData.add(new newChooseTimeItem("18","50","19","00","FREE"));

        mData.add(new newChooseTimeItem("19","00","19","10","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("19","10","19","20","FREE"));
        mData.add(new newChooseTimeItem("19","20","19","30","FREE"));
        mData.add(new newChooseTimeItem("19","30","19","40","FREE"));
        mData.add(new newChooseTimeItem("19","40","19","50","FREE"));
        mData.add(new newChooseTimeItem("19","50","20","00","FREE"));

        mData.add(new newChooseTimeItem("20","00","20","10","FREE"));
        mData.add(new newChooseTimeItem("20","10","20","20","FREE"));
        mData.add(new newChooseTimeItem("20","20","20","30","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("20","30","20","40","FREE"));
        mData.add(new newChooseTimeItem("20","40","20","50","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("20","50","21","00","FREE"));

        mData.add(new newChooseTimeItem("21","00","21","10","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("21","10","21","20","FREE"));
        mData.add(new newChooseTimeItem("21","20","21","30","FREE"));
        mData.add(new newChooseTimeItem("21","30","21","40","FREE"));
        mData.add(new newChooseTimeItem("21","40","21","50","WAITING_BOOKED"));
        mData.add(new newChooseTimeItem("21","50","22","00","WAITING_BOOKED"));

        newsAdapter = new newChooseTimeAdapter(this,mData);
        NewsRecyclerView.setAdapter(newsAdapter);
        NewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        Intent intent_description = new Intent(this, BookingDescriptionActivity.class);
        startActivity(intent_description);
    }
}
