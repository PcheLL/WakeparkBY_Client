package com.wakeparkby.Controller;

import android.content.Context;
import android.content.Intent;

import com.wakeparkby.Activity.Booking.ChooseTimeIntervalActivity;
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
    private List<String> finalTimeSpaceList = new ArrayList<String>();
    List<String> startHoursIntervalList = new ArrayList<>();
    List<String> startMinutesIntervalList = new ArrayList<>();
    List<String> endHoursIntervalList = new ArrayList<>();
    List<String> endMinutesIntervalList = new ArrayList<>();
    private static List<TimeSpace> listTimeSpace = new ArrayList<>();
    private RetrofitClient retrofitClient = RetrofitClient.getRetrofitClient();
    Observer observer = new Observer("BookingController") {
        @Override
        public void update() {
            if (observer.getStatus() == 10) {
                if (observer.getId() == 1) {
                    listTimeSpace.clear();
                    setListTimeSpace(retrofitClient.getListTimeSpace());
                    observer.notifyAllObservers(2);
                } else {
                }
            }
        }
    };

    public BookingController(String place, String date, int reverseCableNumber, Intent intent_time, Context context) {
        intent_time.putExtra("place", place);
        intent_time.putExtra("date", date);
        intent_time.putExtra("reverseCableNumber", reverseCableNumber);
        start(context, intent_time);
        retrofitClient.getTimeSpace(place, date, reverseCableNumber);
    }

    public BookingController() {

    }

    public BookingController(String date, String location, int reverseCableNumber, int newStartTime, int newEndTime, Intent intent_description, ChooseTimeIntervalActivity chooseTimeIntervalActivity) {
        retrofitClient.postBooking(new Booking(date, location, reverseCableNumber, newStartTime, newEndTime));
        String finalTimeInterval = null;
        int startHours = newStartTime / 60;
        int startMinutes = newStartTime - startHours * 60;
        int endHours = newEndTime / 60;
        int endMinutes = newEndTime - endHours * 60;
        if (startMinutes == 0) {
            if (endMinutes == 0) {
                finalTimeInterval = String.valueOf(startHours + ":" + startMinutes + "0 - " + endHours + ":" + endMinutes + "0");
            } else if (endMinutes < 10 ){
                finalTimeInterval = String.valueOf(startHours + ":" + startMinutes + "0 - " + endHours + ":0" + endMinutes);
            }

            else {
                finalTimeInterval = String.valueOf(startHours + ":" + startMinutes + "0 - " + endHours + ":" + endMinutes);
            }
        } else if (endMinutes == 0) {
            if (startMinutes < 10) {
                finalTimeInterval = String.valueOf(startHours + ":0" + startMinutes + " - " + endHours + ":0" + endMinutes);
            } else {
                finalTimeInterval = String.valueOf(startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes + "0");
            }
        } else if (startMinutes < 10) {
            if (endMinutes < 10) {
                finalTimeInterval = String.valueOf(startHours + ":0" + startMinutes + " - " + endHours + ":0" + endMinutes);
            } else {
                finalTimeInterval = String.valueOf(startHours + ":0" + startMinutes + " - " + endHours + ":" + endMinutes);
            }
        } else if (endMinutes < 10 ) {
            finalTimeInterval = String.valueOf(startHours + ":" + startMinutes + " - " + endHours + ":0" + endMinutes);
        } else {
            finalTimeInterval = startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes;
        }
        if (location.equals("LOGOISK")){
            location = "Логойск";
        } else if (location.equals("DROZDI")){
            location = "Дрозды";
        }
        intent_description.putExtra("location", location);
        intent_description.putExtra("date", date);
        intent_description.putExtra("reverseCableNumber", reverseCableNumber);
        intent_description.putExtra("finalTimeInterval", finalTimeInterval);
        chooseTimeIntervalActivity.startActivity(intent_description);
    }

    public static void start(Context context, Intent intent) {
        context.startActivity(intent);
    }


    public List<TimeSpace> getListTimeSpace() {
        return listTimeSpace;
    }

    public static void setListTimeSpace(List<TimeSpace> listTimeSpace) {
        BookingController.listTimeSpace = listTimeSpace;
    }

    public List<String> getFinalTimeSpaceList() {
        finalTimeSpaceList.clear();
        for (int i = 0; i < listTimeSpace.size(); i++) {
            int start = listTimeSpace.get(i).getStart();
            int end = listTimeSpace.get(i).getEnd();
            int startHours = start / 60;
            int startMinutes = start - startHours * 60;
            int endHours = end / 60;
            int endMinutes = end - endHours * 60;
            if (startMinutes == 0) {
                if (endMinutes == 0) {
                    finalTimeSpaceList.add(startHours + ":" + startMinutes + "0 - " + endHours + ":" + endMinutes + "0");
                } else if (endMinutes < 10 ){
                    finalTimeSpaceList.add(startHours + ":" + startMinutes + "0 - " + endHours + ":0" + endMinutes);
                }

                else {
                    finalTimeSpaceList.add(startHours + ":" + startMinutes + "0 - " + endHours + ":" + endMinutes);
                }
            } else if (endMinutes == 0) {
                if (startMinutes < 10) {
                    finalTimeSpaceList.add(startHours + ":0" + startMinutes + " - " + endHours + ":0" + endMinutes);
                } else {
                    finalTimeSpaceList.add(startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes + "0");
                }
            } else if (startMinutes < 10) {
                if (endMinutes < 10) {
                    finalTimeSpaceList.add(startHours + ":0" + startMinutes + " - " + endHours + ":0" + endMinutes);
                } else {
                    finalTimeSpaceList.add(startHours + ":0" + startMinutes + " - " + endHours + ":" + endMinutes);
                }
            } else if (endMinutes < 10 ) {
                finalTimeSpaceList.add(startHours + ":" + startMinutes + " - " + endHours + ":0" + endMinutes);
            } else {
                finalTimeSpaceList.add(startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes);
            }
        }
        return finalTimeSpaceList;
    }


}