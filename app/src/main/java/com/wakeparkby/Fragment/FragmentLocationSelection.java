package com.wakeparkby.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.R;

import java.util.Calendar;

public class FragmentLocationSelection extends Fragment implements View.OnClickListener {
    private MaterialButton buttonDrozdy;
    private MaterialButton buttonLogoysk;
    private Calendar c;
    private DatePickerDialog datePicker;
    private Fragment fragment;

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
        fragment = new FragmentReverseCableSelection();
        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle("Выберите место");
        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonDrozdy:
                showDatePicker("DROZDI");
                break;

            case R.id.buttonLogoysk:
                showDatePicker("LOGOISK");
                break;
        }
    }

    public void showDatePicker(String place) {
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
            datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month++;
                    String monthOfYear = String.valueOf(month);
                    String day = String.valueOf(dayOfMonth);
                    if (dayOfMonth < 10) {
                        day = "0" + String.valueOf(dayOfMonth);
                    }
                    if (month < 10) {
                        monthOfYear = "0" + String.valueOf(month);
                    }
                    String date = day + "." + monthOfYear + "." + String.valueOf(year);
                    long minDate = c.getTimeInMillis();
                    long maxDate = c.getTimeInMillis() + 1000 * 86400 * 5;
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year,month-1,dayOfMonth);
                    long nowTime = calendar.getTimeInMillis();
                    if (minDate<= nowTime && maxDate >= nowTime){
                        Bundle args_fragment = new Bundle();
                        args_fragment.putString("place", place);
                        args_fragment.putString("date", date);
                        Fragment fragment = new FragmentReverseCableSelection();
                        fragment.setArguments(args_fragment);
                        ((MainMenuActivity) getActivity()).pushFragments(MainMenuActivity.TAB_HOME, fragment, true);
                    }
                        else {
                        Toast.makeText(getContext(), "Эти дни недоступны !", Toast.LENGTH_SHORT).show();
                    }
                }
            }, day, month, year);
        datePicker.getDatePicker().setMinDate(c.getTimeInMillis());
        datePicker.getDatePicker().setMaxDate(c.getTimeInMillis() + 1000 * 86400 * 5);
        datePicker.show();
    }
}
