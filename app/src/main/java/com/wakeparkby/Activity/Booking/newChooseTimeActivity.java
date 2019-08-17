package com.wakeparkby.Activity.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.stomped.stomped.client.StompedClient;
import com.stomped.stomped.component.StompedFrame;
import com.stomped.stomped.listener.StompedListener;
import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.HTTPController.Time;
import com.wakeparkby.HTTPController.TimeSpace;
import com.wakeparkby.HTTPController.WsEventDto;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class newChooseTimeActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView NewsRecyclerView;
    newChooseTimeAdapter newChooseTimeAdapter;
    final StompedClient client = new StompedClient.StompedClientBuilder().build("http://18.196.191.127:8080/jwtappdemo-0.0.1-SNAPSHOT/gs-guide-websocket/websocket");
    private List<newChooseTimeItem> mData;
    MaterialButton buttonChooseTime;
    BookingController bookingController = new BookingController();
    private List<TimeSpace> responseTimeSpaceList = new ArrayList<>();
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
        NewsRecyclerView = findViewById(R.id.recyclerViewTime);
        buttonChooseTime = findViewById(R.id.buttonChooseTime);
        buttonChooseTime.setOnClickListener(this);
        mData = new ArrayList<>();
        openConnect();
    }

    private void openConnect() {
       // final StompedClient client = new StompedClient.StompedClientBuilder().build("http://18.196.191.127:8080/jwtappdemo-0.0.1-SNAPSHOT/gs-guide-websocket/websocket");
        client.subscribe("/topic/activity", new StompedListener() {

            @Override
            public void onNotify(final StompedFrame frame) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(frame.getStompedBody());
                        String response = frame.getStompedBody().toString().substring(0,frame.getStompedBody().length() - 1);
                        Gson gson = new Gson();
                        WsEventDto wsEventDto = gson.fromJson(response,WsEventDto.class);
                        for (int i = 0 ; i < mData.size();i++){
                            int startTime = Integer.valueOf(mData.get(i).getStartHours()) * 60 + Integer.valueOf(mData.get(i).getStartMinutes());
                            if (startTime == wsEventDto.getBody().getStartTime()){
                                mData.get(i).setStatus("BOOKED_NO_ACCEPTED");
                                newChooseTimeAdapter.notifyItemChanged(i);
                            }
                            }
                        //   WsEventDto wsEventDto = frame.getStompedBody();
                        //TimeSpace timeSpace = new TimeSpace(frame.getStompedBody());
                        // TimeSpace timeSpace = frame.getStompedBody();
                      //  responseTimeSpaceList = frame.getStompedBody();

                        //  output.setText(frame.getStompedBody());
                        //  client.disconnect();
                    }
                });
            }
        });
    }

    private void updateChooseTime() {

        mData.clear();
        bookingController.refreshFinalTimeSpaceList();
        for (int i = 0; i < bookingController.getFinalTimeSpaceList().size();i++) {
            mData.add(new newChooseTimeItem(bookingController.getFinalTimeSpaceList().get(i).getStartHours(), bookingController.getFinalTimeSpaceList().get(i).getStartMinutes(),
                    bookingController.getFinalTimeSpaceList().get(i).getEndHours(), bookingController.getFinalTimeSpaceList().get(i).getEndMinutes(),
                    bookingController.getFinalTimeSpaceList().get(i).getStatus()));
        }
        newChooseTimeAdapter = new newChooseTimeAdapter(this,mData, getIntent().getStringExtra("place"),getIntent().getStringExtra("date"),getIntent().getIntExtra("reverseCableNumber",10));
        NewsRecyclerView.setAdapter(newChooseTimeAdapter);
        NewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

      /*  if (timeSpaceList.size() == 0) {
            // relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        } else {
            timeAdapter = new ArrayAdapter<>(ChooseTimeActivity.this,
                    R.layout.text_view,
                    timeSpaceList.toArray(new String[timeSpaceList.size()]));
            //  relativeLayoutProgressBar.setVisibility(View.GONE);
            listViewTime.setAdapter(timeAdapter);
        }
        relativeLayoutProgressBar.setVisibility(View.GONE);
        linearLayoutChooseTime.setVisibility(View.VISIBLE);*/
    }

    @Override
    public void onClick(View view) {
      //  Intent intent_description = new Intent(this, BookingDescriptionActivity.class);
      //  startActivity(intent_description);
      /*  mData.add(2,new newChooseTimeItem("9","10",
               "9","40",
                "FREE"));
        newChooseTimeAdapter.notifyItemInserted(2);*/
    }

    @Override
    public void onBackPressed() {
        observer.removeFromList(observer);
        client.disconnect();
        super.onBackPressed();
    }
}
