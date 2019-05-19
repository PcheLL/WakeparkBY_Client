package com.wakeparkby.Activity.Booking;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.HTTPController.TimeSpace;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseTimeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnTouchListener {
    private List<TimeSpace> listTimeSpace = new ArrayList<>();
    private float fromPosition;
    RelativeLayout relativeLayoutProgressBar;
    LinearLayout linearLayoutChooseTime;
    ListView listViewTime;
    private final static String TAG = "ChooseTimeActivity";
    private ArrayAdapter<String> timeAdapter;
    private List<String> timeSpaceList = new ArrayList<String>();
    BookingController bookingController = new BookingController();
    Observer observer = new Observer("Time") {
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
        relativeLayoutProgressBar = findViewById(R.id.relativeLayoutProgressBar11);
        linearLayoutChooseTime = findViewById(R.id.linearLayoutChooseTime11);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        linearLayoutChooseTime.setVisibility(View.GONE);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutChooseTime);
        relativeLayout.setOnTouchListener(this);

    }

    private void updateChooseTime() {
        timeSpaceList = bookingController.getFinalTimeSpaceList();
        if (timeSpaceList.size() == 0) {
            // relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        } else {
            timeAdapter = new ArrayAdapter<>(ChooseTimeActivity.this,
                    R.layout.text_view,
                    timeSpaceList.toArray(new String[timeSpaceList.size()]));
            //  relativeLayoutProgressBar.setVisibility(View.GONE);
            listViewTime.setAdapter(timeAdapter);
        }
        relativeLayoutProgressBar.setVisibility(View.GONE);
        linearLayoutChooseTime.setVisibility(View.VISIBLE);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        timeAdapter = null;
        String timeAtPosition = (String) adapterView.getItemAtPosition(position);
        int startHours = 0;
        try {
            startHours = Integer.parseInt(timeAtPosition.substring(0, 2));
        } catch (NumberFormatException ex) {
            startHours = Integer.parseInt(timeAtPosition.substring(0, 1));
        }
        int startMinutes = 0;
        try {
            startMinutes = Integer.parseInt(timeAtPosition.substring(3, 5));
        } catch (NumberFormatException ex) {
            startMinutes = Integer.parseInt(timeAtPosition.substring(2, 4));
        }
        int endHours = 0;
        try {
            endHours = Integer.parseInt(timeAtPosition.substring(7, 9));
        } catch (NumberFormatException ex) {
            try {
                endHours = Integer.parseInt(timeAtPosition.substring(7, 8));
            } catch (NumberFormatException ex1) {
                endHours = Integer.parseInt(timeAtPosition.substring(8, 10));
            }

        }
        int endMinutes = 0;
        try {
            endMinutes = Integer.parseInt(timeAtPosition.substring(10, 12));
        } catch (NumberFormatException ex) {
            try {
                endMinutes = Integer.parseInt(timeAtPosition.substring(9, 11));
            } catch (NumberFormatException ex2) {
                endMinutes = Integer.parseInt(timeAtPosition.substring(11, 13));
            }
        } catch (StringIndexOutOfBoundsException ex3){
            endMinutes = Integer.parseInt(timeAtPosition.substring(9, 11));
        }


        Intent intent_timeInterval = new Intent(this, ChooseTimeIntervalActivity.class);
        intent_timeInterval.putExtra("location", getIntent().getStringExtra("place"));
        intent_timeInterval.putExtra("date", getIntent().getStringExtra("date"));
        intent_timeInterval.putExtra("reverseCableNumber", getIntent().getIntExtra("reverseCableNumber", 0));
        intent_timeInterval.putExtra("startHours", startHours);
        intent_timeInterval.putExtra("startMinutes", startMinutes);
        intent_timeInterval.putExtra("endMinutes", endMinutes);
        intent_timeInterval.putExtra("endHours", endHours);
        BookingController.start(this, intent_timeInterval);
        listViewTime.invalidateViews();
        observer.removeFromList(observer);
        //finish();
    }

    @Override
    public void onBackPressed() {
        observer.removeFromList(observer);
        super.onBackPressed();
    }

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fromPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();

                if (fromPosition < toPosition) {
                    observer.removeFromList(observer);
                    super.onBackPressed();
                }
            default:
                break;
        }
        return true;
    }

}