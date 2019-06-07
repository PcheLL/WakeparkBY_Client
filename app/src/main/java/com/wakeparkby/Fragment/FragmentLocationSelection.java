package com.wakeparkby.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.button.MaterialButton;
import com.wakeparkby.Activity.Booking.ChooseTimeActivity;
import com.wakeparkby.R;

import java.util.Calendar;

public class FragmentLocationSelection extends Fragment implements View.OnClickListener{
    MaterialButton buttonDrozdy;
    MaterialButton buttonLogoysk;
    Calendar c;
    DatePickerDialog datePicker;

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

        Intent intent_Date = new Intent(getActivity(), ChooseTimeActivity.class);
        switch (v.getId()) {
            case R.id.buttonDrozdy:
                intent_Date.putExtra("place", "DROZDI");
                showDatePicker(intent_Date,"Дрозды");
                break;

            case R.id.buttonLogoysk:
                intent_Date.putExtra("place", "LOGOISK");
                showDatePicker(intent_Date,"ГСОК Логойск");
                break;
        }
    }

    public void showDatePicker(Intent intent_Date, String place){
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                System.out.print("");
                startActivity(intent_Date);
            }
        }, day, month, year);
        datePicker.show();
    }
}
