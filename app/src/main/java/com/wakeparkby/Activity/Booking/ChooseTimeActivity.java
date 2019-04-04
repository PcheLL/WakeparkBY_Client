package com.wakeparkby.Activity.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

public class ChooseTimeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<TimeSpace> listTimeSpace = new ArrayList<>();
    ListView listViewTime;
    BookingController bookingController = new BookingController();
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
        listViewTime.setOnItemClickListener(this);
        updateList();


    }

    private void updateList() {
        List<String> timeSpaceList = new ArrayList<String>();
        timeSpaceList = bookingController.getFinalTimeSpaceList();
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(ChooseTimeActivity.this,
                R.layout.text_view,
                timeSpaceList.toArray(new String[timeSpaceList.size()]));
        listViewTime.setAdapter(timeAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String timeAtPosition = (String) adapterView.getItemAtPosition(position);
        int startHours = Integer.parseInt(timeAtPosition.substring(0, 2));
        int startMinutes = Integer.parseInt(timeAtPosition.substring(3, 5));
        int endHours = Integer.parseInt(timeAtPosition.substring(8, 10));
        int endMinutes = Integer.parseInt(timeAtPosition.substring(11, 13));
        Intent intent_timeInterval = new Intent(this,ChooseTimeIntervalActivity.class);
        intent_timeInterval.putExtra("location", getIntent().getStringExtra("place"));
        intent_timeInterval.putExtra("date", getIntent().getStringExtra("date"));
        intent_timeInterval.putExtra("reverseCableNumber", getIntent().getIntExtra("reverseCableNumber", 0));
        intent_timeInterval.putExtra("startHours",startHours);
        intent_timeInterval.putExtra("startMinutes", startMinutes);
        intent_timeInterval.putExtra("endMinutes", endMinutes);
        intent_timeInterval.putExtra("endHours", endHours);
        BookingController.start(this,intent_timeInterval);
    }
}