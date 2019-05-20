package com.wakeparkby.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.wakeparkby.Activity.Booking.DateSelectionActivity;
import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.R;

public class FragmentLocationSelection extends Fragment implements View.OnClickListener {
    Button buttonDrozdy;
    Button buttonLogoysk;

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
        Intent intent_Date = new Intent(getActivity(),DateSelectionActivity.class);
        switch (v.getId()) {
            case R.id.buttonDrozdy:
                intent_Date.putExtra("place","DROZDI");
                BookingController.start(getActivity(),intent_Date);
                break;
            case R.id.buttonLogoysk:
                intent_Date.putExtra("place","LOGOISK");
                BookingController.start(getActivity(),intent_Date);
                break;
        }
    }



}
