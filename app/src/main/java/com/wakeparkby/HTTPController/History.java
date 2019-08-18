package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class History {
    @Expose
    private String id;
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
    @Expose
    private String status;

    public History() {}
}
