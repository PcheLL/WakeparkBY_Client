package com.wakeparkby.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.wakeparkby.Activity.Booking.ReverseCableSelectionActivity;
import com.wakeparkby.R;

import java.util.Calendar;

public class FragmentLocationSelection extends Fragment implements View.OnClickListener{
    private MaterialButton buttonDrozdy;
    private MaterialButton buttonLogoysk;
    private Calendar c;
    private DatePickerDialog datePicker;

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

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle("Выберите место");
        return rootView;
    }

    @Override
    public void onClick(View v) {

        Intent intent_reverseCableSelection = new Intent(getActivity(), ReverseCableSelectionActivity.class);
        switch (v.getId()) {
            case R.id.buttonDrozdy:
                intent_reverseCableSelection.putExtra("place", "DROZDI");
                showDatePicker(intent_reverseCableSelection,"Дрозды");
                break;

            case R.id.buttonLogoysk:
                intent_reverseCableSelection.putExtra("place", "LOGOISK");
                showDatePicker(intent_reverseCableSelection,"ГСОК Логойск");
                break;
        }
    }

    public void showDatePicker(Intent intent_reverseCableSelection, String place){
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month ++;
                String monthOfYear = String.valueOf(month);
                String day = String.valueOf(dayOfMonth);
                if (dayOfMonth < 10 )
                {
                    day = "0" + String.valueOf(dayOfMonth);
                }
                if (month < 10 )
                {
                    monthOfYear = "0" + String.valueOf(month);
                }
                String date = day + "." + monthOfYear + "." + String.valueOf(year);
                intent_reverseCableSelection.putExtra("date",date);
                startActivity(intent_reverseCableSelection);
            }
        }, day, month, year);
        datePicker.getDatePicker().setMinDate(c.getTimeInMillis());
        datePicker.getDatePicker().setMaxDate(c.getTimeInMillis()+1000 * 60 * 60 * 24 * 5);
        datePicker.show();
    }
}
