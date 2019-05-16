package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class History {
    @Expose
    private String data;
    @Expose
    private String location;
    @Expose
    private String reversNumber;
    @Expose
    private String startTime;
    @Expose
    private String endTime;
    @Expose
    private String status;
    //@Expose
    //private String image;

    public History() {}

    public History(String data, String location, String reversNumber, String startTime, String endTime, String status) {
        this.data = data;
        this.location = location;
        this.reversNumber = reversNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
}
