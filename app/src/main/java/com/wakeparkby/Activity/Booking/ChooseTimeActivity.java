package com.wakeparkby.Activity.Booking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.HTTPController.Booking;
import com.wakeparkby.HTTPController.TimeSpace;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ChooseTimeActivity extends AppCompatActivity {
    private List<TimeSpace> listTimeSpace = new ArrayList<>();
    ListView listViewTime;
    BookingController bookingController;
    Observer observer = new Observer("Time") {

        /**
         * override method of Observer class with new reaction for notify observers
         */

        @Override
        public void update() {
            int n = observer.getStatus();
            if (n == 10) {
                if (observer.getId() == 2) {
                    updateList();
                    observer.setId(0);
                } else {
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);
        listViewTime = (ListView) findViewById(R.id.listViewTime);
        bookingController = new BookingController(getIntent().getStringExtra("place"), getIntent().getStringExtra("date"),
                getIntent().getIntExtra("reverseCableNumber", 0));

    }

    private void updateList() {

        //bookingController.getListTimeSpace();
        List<TimeSpace> timeSpaceList = new ArrayList<TimeSpace>();
        List<String> finalTimeSpaceList = new ArrayList<String>();
        timeSpaceList = bookingController.getListTimeSpace();
        for (int i = 0; i < timeSpaceList.size(); i++) {
            int start = timeSpaceList.get(i).getStart();
            int end = timeSpaceList.get(i).getEnd();
            int startHours = start / 60;
            int startMinutes = start - startHours * 60;
            int endHours = end / 60;
            int endMinutes = end - endHours * 60;
            if (startMinutes == 0) {
                if (endMinutes == 0) {
                    finalTimeSpaceList.add(startHours + ":" + startMinutes + "0 - " + endHours + ":" + endMinutes + "0");
                } else {
                    finalTimeSpaceList.add(startHours + ":" + startMinutes + "0 - " + endHours + ":" + endMinutes);
                }
            } else if (endMinutes == 0) {
                finalTimeSpaceList.add(startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes + "0");
            } else {
                finalTimeSpaceList.add(startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes);
            }
        }


        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(ChooseTimeActivity.this,
                R.layout.text_view,
                finalTimeSpaceList.toArray(new String[finalTimeSpaceList.size()]));
        listViewTime.setAdapter(timeAdapter);
    }
}