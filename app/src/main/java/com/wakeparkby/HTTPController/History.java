package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class History {
    @Expose
    private String id;
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

    public History() {}
}
