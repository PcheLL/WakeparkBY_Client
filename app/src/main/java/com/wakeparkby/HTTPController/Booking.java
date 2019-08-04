package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class Booking {
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

    public Booking(){}
    public Booking(String data, String place, int reversNumber, int startTime, int endTime){
        this.bookingDate = data;
        this.location = place;
        this.reversNumber = reversNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
