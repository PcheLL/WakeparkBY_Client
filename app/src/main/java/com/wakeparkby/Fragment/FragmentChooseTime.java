package com.wakeparkby.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.stomped.stomped.client.StompedClient;
import com.stomped.stomped.component.StompedFrame;
import com.stomped.stomped.listener.StompedListener;
import com.wakeparkby.Activity.Booking.newChooseTimeAdapter;
import com.wakeparkby.Activity.Booking.newChooseTimeItem;
import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.HTTPController.Time;
import com.wakeparkby.HTTPController.TimeSpace;
import com.wakeparkby.HTTPController.WsEventDto;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentChooseTime extends Fragment implements View.OnClickListener {
    RecyclerView NewsRecyclerView;
    com.wakeparkby.Activity.Booking.newChooseTimeAdapter newChooseTimeAdapter;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_choose_time, container, false);
        NewsRecyclerView = rootView.findViewById(R.id.recyclerViewTime);
        buttonChooseTime = rootView.findViewById(R.id.buttonChooseTime);
        buttonChooseTime.setOnClickListener(this);
        mData = new ArrayList<>();
        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle("Выберите время");
        openConnect();
        return rootView;
    }

    public static FragmentChooseTime newInstance() {
        return new FragmentChooseTime();
    }
    private void openConnect() {
       // final StompedClient client = new StompedClient.StompedClientBuilder().build("http://18.196.191.127:8080/jwtappdemo-0.0.1-SNAPSHOT/gs-guide-websocket/websocket");
        client.subscribe("/topic/activity", new StompedListener() {

            @Override
            public void onNotify(final StompedFrame frame) {
                getActivity().runOnUiThread(new Runnable() {
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
        newChooseTimeAdapter = new newChooseTimeAdapter(getContext(),mData, getArguments().getString("place"),getArguments().getString("date"),getArguments().getInt("reverseCableNumber",10));
        NewsRecyclerView.setAdapter(newChooseTimeAdapter);
        NewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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

   /* @Override
    public void onBackPressed() {
        observer.removeFromList(observer);
        client.disconnect();
        super.onBackPressed();
    }*/
}
