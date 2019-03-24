package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class Booking {
    @Expose
    private String data;
    @Expose
    private String location;
    @Expose
    private int reversNumber;
    @Expose
    private int startTime;
    @Expose
    private int endTime;

    public Booking(){}
    public Booking(String data, String place, int i, int i1, int i2){
        this.data = data;
        this.location = place;
        this.reversNumber = i;
        this.startTime = i1;
        this.endTime = i2;
    }
}
