package com.wakeparkby.Activity.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.HTTPController.TimeSpace;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseTimeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<TimeSpace> listTimeSpace = new ArrayList<>();
    RelativeLayout relativeLayoutProgressBar;
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
                    updateChooseTime();
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
        relativeLayoutProgressBar = findViewById(R.id.relativeLayoutProgressBar);
        updateChooseTime();


    }

    private void updateChooseTime() {
        List<String> timeSpaceList = new ArrayList<String>();
        timeSpaceList = bookingController.getFinalTimeSpaceList();
        if (timeSpaceList.size() == 0){
            relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        }
        else{
            ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(ChooseTimeActivity.this,
                    R.layout.text_view,
                    timeSpaceList.toArray(new String[timeSpaceList.size()]));
            relativeLayoutProgressBar.setVisibility(View.GONE);
            listViewTime.setAdapter(timeAdapter);
        }

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String timeAtPosition = (String) adapterView.getItemAtPosition(position);
        int startHours = 0;
        try {
            startHours = Integer.parseInt(timeAtPosition.substring(0, 2));
        } catch (NumberFormatException ex){
            startHours = Integer.parseInt(timeAtPosition.substring(0, 1));
        }
        int startMinutes = 0;
        try {
            startMinutes = Integer.parseInt(timeAtPosition.substring(3, 5));
        } catch (NumberFormatException ex){
            startMinutes = Integer.parseInt(timeAtPosition.substring(2, 4));
        }
        int endHours = 0;
        try {
            endHours = Integer.parseInt(timeAtPosition.substring(8, 10));
        } catch (NumberFormatException ex){
            endHours = Integer.parseInt(timeAtPosition.substring(7, 9));
        }
        int endMinutes = 0;
        try {
            endMinutes = Integer.parseInt(timeAtPosition.substring(11, 13));
        } catch (StringIndexOutOfBoundsException ex){
            endMinutes = Integer.parseInt(timeAtPosition.substring(10, 12));
        }


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

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

}