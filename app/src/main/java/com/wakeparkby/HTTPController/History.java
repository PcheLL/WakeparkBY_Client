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

    private String startHours;
    private String startMinutes;
    private String endHours;
    private String endMinutes;

    public History() {
    }


    public String getTime() {
        return getStartHours() + ":" + getStartMinutes()+ " - "
                + getEndHours() + ":" + getEndMinutes();
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
