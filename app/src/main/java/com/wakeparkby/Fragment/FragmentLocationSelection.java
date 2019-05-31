package com.wakeparkby.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.button.MaterialButton;
import com.wakeparkby.Activity.Booking.DateSelectionActivity;
import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.R;

public class FragmentLocationSelection extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    MaterialButton buttonDrozdy;
    MaterialButton buttonLogoysk;

    public FragmentLocationSelection() {
    }

    public static FragmentLocationSelection newInstance() {
        return new FragmentLocationSelection();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_location_selection, container, false);
        buttonDrozdy = rootView.findViewById(R.id.buttonDrozdy);
        buttonDrozdy.setOnClickListener(this);
        buttonLogoysk = rootView.findViewById(R.id.buttonLogoysk);
        buttonLogoysk.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        DialogFragment datePicker;
        Intent intent_Date = new Intent(getActivity(), DateSelectionActivity.class);
        switch (v.getId()) {
            case R.id.buttonDrozdy:
                intent_Date.putExtra("place", "DROZDI");
                datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(), "DatePicker");

            case R.id.buttonLogoysk:
                intent_Date.putExtra("place", "LOGOISK");
                datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(), "DatePicker");
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        System.out.print("");
    }

}
