package com.wakeparkby.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.wakeparkby.Activity.Booking.DateSelectionActivity;
import com.wakeparkby.Client.RetrofitClient;
import com.wakeparkby.HTTPController.Booking;
import com.wakeparkby.HTTPController.TimeSpace;
import com.wakeparkby.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private String place;
    private String date;
    private int reverseCableNumber;
    //private Intent intent;
    private static List<TimeSpace> listTimeSpace = new ArrayList<>();
    private RetrofitClient retrofitClient = RetrofitClient.getRetrofitClient();
    Observer observer = new Observer("BookingController"){
        @Override
        public void update(){
            if (observer.getStatus() == 10){
                if(observer.getId() == 1){
                    setListTimeSpace(retrofitClient.getListTimeSpace());
                   // listTimeSpace = retrofitClient.getListTimeSpace();
                    observer.notifyAllObservers(2);
                }else{}
            }
        }
    };

    public BookingController(String place, String date, int reverseCableNumber, Intent intent_time, Context context) {
        //this.place = place;
        //this.date = date;
        //this.reverseCableNumber = reverseCableNumber;
        //this.intent = intent_time;
       // retrofitClient.postBooking(new Booking(data,place,1,1000,1200));
        intent_time.putExtra("place", place);
        intent_time.putExtra("date", date);
        intent_time.putExtra("reverseCableNumber",reverseCableNumber);
        start(context,intent_time);
        retrofitClient.getTimeSpace(place,date,reverseCableNumber);
       // retrofitClient.postBooking(new Booking(date ,place,reverseCableNumber,1000,1200));
    }

    public BookingController(String place, String date, int reverseCableNumber) {
        this.place = place;
        this.date = date;
        this.reverseCableNumber = reverseCableNumber;
    }

    public BookingController() {

    }

    public static void start(Context context, Intent intent_reverse) {
        context.startActivity(intent_reverse);
    }

    public List<TimeSpace> getListTimeSpace() {
        return listTimeSpace;
    }

    public static void setListTimeSpace(List<TimeSpace> listTimeSpace) {
        BookingController.listTimeSpace = listTimeSpace;
    }
}
