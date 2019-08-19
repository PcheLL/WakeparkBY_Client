package com.wakeparkby.Controller;

import android.content.Context;
import android.content.Intent;

import com.wakeparkby.Client.RetrofitClient;
import com.wakeparkby.HTTPController.Booking;
import com.wakeparkby.HTTPController.Time;
import com.wakeparkby.HTTPController.TimeSpace;
import com.wakeparkby.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private static List<TimeSpace> listTimeSpace = new ArrayList<>();
    private static List<Time> finalTimeSpaceList = new ArrayList<>();
    private List<Time> noAcceptedTimeList = new ArrayList<>();
    private RetrofitClient retrofitClient = RetrofitClient.getRetrofitClient();
    private Observer observer = new Observer("BookingController") {
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

    public BookingController(String place, String date, int reverseCableNumber) {
        setFreeTimeList();
        retrofitClient.getTimeSpace(place, date, reverseCableNumber);
    }

    public BookingController() {

    }

    public BookingController(String place, String date, int reverseCableNumber, int startTime, int endTime) {
        retrofitClient.postBooking(new Booking(date, place, reverseCableNumber, startTime, endTime));
    }


    public static void start(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public static void setListTimeSpace(List<TimeSpace> listTimeSpace) {
        BookingController.listTimeSpace = listTimeSpace;
    }

    public void setFreeTimeList() {
        finalTimeSpaceList.clear();
        int start = 540;
        int end = 1260;
        int fl = (end - start) / 10;
        for (int j = 0; j < fl; j++) {
            int startHours = start / 60;
            int startMinutes = start - startHours * 60;
            start += 10;
            int endHours = start / 60;
            int endMinutes = start - endHours * 60;
            String status = "FREE";
            if (startMinutes == 0) {
                if (endMinutes == 0) {
                    finalTimeSpaceList.add(j, new Time(String.valueOf(startHours), String.valueOf(startMinutes + "0"), String.valueOf(endHours), String.valueOf(endMinutes + "0"), status));
                } else if (endMinutes < 10) {
                    finalTimeSpaceList.add(j, new Time(String.valueOf(startHours), String.valueOf(startMinutes + "0"), String.valueOf(endHours), String.valueOf("0" + endMinutes), status));
                } else {
                    finalTimeSpaceList.add(j, new Time(String.valueOf(startHours), String.valueOf(startMinutes + "0"), String.valueOf(endHours), String.valueOf(endMinutes), status));
                }
            } else if (endMinutes == 0) {
                if (startMinutes < 10) {
                    finalTimeSpaceList.add(j, new Time(String.valueOf(startHours), String.valueOf("0" + startMinutes), String.valueOf(endHours), String.valueOf("0" + endMinutes), status));
                } else {
                    finalTimeSpaceList.add(j, new Time(String.valueOf(startHours), String.valueOf(startMinutes), String.valueOf(endHours), String.valueOf(endMinutes + "0"), status));
                }
            } else if (startMinutes < 10) {
                if (endMinutes < 10) {
                    finalTimeSpaceList.add(j, new Time(String.valueOf(startHours), String.valueOf("0" + startMinutes), String.valueOf(endHours), String.valueOf("0" + endMinutes), status));
                } else {
                    finalTimeSpaceList.add(j, new Time(String.valueOf(startHours), String.valueOf("0" + startMinutes), String.valueOf(endHours), String.valueOf(endMinutes), status));
                }
            } else if (endMinutes < 10) {
                finalTimeSpaceList.add(j, new Time(String.valueOf(startHours), String.valueOf(startMinutes), String.valueOf(endHours), String.valueOf("0" + endMinutes), status));
            } else {
                finalTimeSpaceList.add(j, new Time(String.valueOf(startHours), String.valueOf(startMinutes), String.valueOf(endHours), String.valueOf(endMinutes), status));
            }
        }
    }


    public List<Time> getFinalTimeSpaceList() {
        return finalTimeSpaceList;
    }

    public void refreshFinalTimeSpaceList() {
        noAcceptedTimeList.clear();
        int counter = 0;
        for (int i = 0; i < listTimeSpace.size(); i++) {
            int start = listTimeSpace.get(i).getStartTime();
            int end = listTimeSpace.get(i).getEndTime();
            int fl = (end - start) / 10;
            int startHours = start / 60;
            int startMinutes = start - startHours * 60;
            int endHours = end / 60;
            int endMinutes = end - endHours * 60;
            String status = listTimeSpace.get(i).getStatus();
            if (startMinutes == 0) {
                if (endMinutes == 0) {
                    noAcceptedTimeList.add(counter, new Time(String.valueOf(startHours), String.valueOf(startMinutes + "0"), String.valueOf(endHours), String.valueOf(endMinutes + "0"), status));
                } else if (endMinutes < 10) {
                    noAcceptedTimeList.add(counter, new Time(String.valueOf(startHours), String.valueOf(startMinutes + "0"), String.valueOf(endHours), String.valueOf("0" + endMinutes), status));
                } else {
                    noAcceptedTimeList.add(counter, new Time(String.valueOf(startHours), String.valueOf(startMinutes + "0"), String.valueOf(endHours), String.valueOf(endMinutes), status));
                }
            } else if (endMinutes == 0) {
                if (startMinutes < 10) {
                    noAcceptedTimeList.add(counter, new Time(String.valueOf(startHours), String.valueOf("0" + startMinutes), String.valueOf(endHours), String.valueOf("0" + endMinutes), status));
                } else {
                    noAcceptedTimeList.add(counter, new Time(String.valueOf(startHours), String.valueOf(startMinutes), String.valueOf(endHours), String.valueOf(endMinutes + "0"), status));
                }
            } else if (startMinutes < 10) {
                if (endMinutes < 10) {
                    noAcceptedTimeList.add(counter, new Time(String.valueOf(startHours), String.valueOf("0" + startMinutes), String.valueOf(endHours), String.valueOf("0" + endMinutes), status));
                } else {
                    noAcceptedTimeList.add(counter, new Time(String.valueOf(startHours), String.valueOf("0" + startMinutes), String.valueOf(endHours), String.valueOf(endMinutes), status));
                }
            } else if (endMinutes < 10) {
                noAcceptedTimeList.add(counter, new Time(String.valueOf(startHours), String.valueOf(startMinutes), String.valueOf(endHours), String.valueOf("0" + endMinutes), status));
            } else {
                noAcceptedTimeList.add(counter, new Time(String.valueOf(startHours), String.valueOf(startMinutes), String.valueOf(endHours), String.valueOf(endMinutes), status));
            }
            counter++;
        }
        for (int i = 0; i < finalTimeSpaceList.size(); i++) {
            int startTime1 = Integer.valueOf(finalTimeSpaceList.get(i).getStartHours()) * 60 + Integer.valueOf(finalTimeSpaceList.get(i).getStartMinutes());
            for (int j = 0; j < noAcceptedTimeList.size(); j++) {
                int startTime2 = Integer.valueOf(noAcceptedTimeList.get(j).getStartHours()) * 60 + Integer.valueOf(noAcceptedTimeList.get(j).getStartMinutes());
                if (startTime1 == startTime2) {
                    finalTimeSpaceList.get(i).setStatus("BOOKED_NO_ACCEPTED");
                }
            }
        }
        System.out.println("");
    }
}