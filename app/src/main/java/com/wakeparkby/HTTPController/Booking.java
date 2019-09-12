package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class Booking {
    @Expose
    private int clientId;
    @Expose
    private int id;
    @Expose
    private String bookingDate;
    @Expose
    private String location;
    @Expose
    private int reversNumber;
    @Expose
    private int startTime;
    @Expose
    private int endTime;

    private String startHours;
    private String startMinutes;
    private String endHours;
    private String endMinutes;

    public Booking(String data, String place, int reversNumber, int startTime, int endTime) {
        this.bookingDate = data;
        this.location = place;
        this.reversNumber = reversNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Booking(int id, String date, String place, int reverseCableNumber, int startTime, int endTime) {
        this.id = id;
        this.bookingDate = date;
        this.location = place;
        this.reversNumber = reversNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String getStartHours() {
        return String.valueOf(startTime / 60);
    }

    public String getStartMinutes() {
        if (startTime - (startTime / 60 * 60) == 0 ){
            return "00";
        }
        return String.valueOf(startTime - (startTime / 60 * 60));
    }

    public String getEndHours() {
        return String.valueOf(endTime / 60);
    }

    public String getEndMinutes() {
        if (endTime - (endTime / 60 * 60) == 0 ){
            return "00";
        }
        return String.valueOf(endTime - (endTime / 60 * 60));
    }
}
