package com.wakeparkby.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wakeparkby.Activity.Booking.BookingDescriptionAdapter;
import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.Controller.BookingDescriptionController;
import com.wakeparkby.HTTPController.Booking;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentBookingDescription extends Fragment implements View.OnClickListener, MainMenuActivity.OnBackPressedListner {
    Button buttonBooking;

    TextView textViewInfoLocation;
    TextView textViewInfoDate;
    TextView textViewInfoCableNumber;
    private RecyclerView newsRecyclerView;
    private BookingDescriptionAdapter bookingDescriptionAdapter;
    private List<Booking> bookingList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_booking_description, container, false);
        bookingList = (List<Booking>) getArguments().getSerializable("bookingList");
        buttonBooking = rootView.findViewById(R.id.button_booking);
        buttonBooking.setOnClickListener(this);
        newsRecyclerView = rootView.findViewById(R.id.recyclerViewInfoBooking);
        textViewInfoLocation = rootView.findViewById(R.id.textViewInfoLocation);
        textViewInfoDate = rootView.findViewById(R.id.textViewInfoDate);
        textViewInfoCableNumber = rootView.findViewById(R.id.textViewInfoReversNumber);
        String location = bookingList.get(0).getLocation();
        if (location.equals("LOGOISK")){
            textViewInfoLocation.setText("Логойск");
        }
        else {
            textViewInfoLocation.setText("Дрозды");
        }
        textViewInfoDate.setText(bookingList.get(0).getBookingDate());
        textViewInfoCableNumber.setText(String.valueOf(bookingList.get(0).getReversNumber()));
        bookingDescriptionAdapter = new BookingDescriptionAdapter(getContext(),bookingList);
        newsRecyclerView.setAdapter(bookingDescriptionAdapter);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       return rootView;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onClick(View v) {
      BookingDescriptionController bookingDescriptionController = new BookingDescriptionController(bookingList);
    }
}
