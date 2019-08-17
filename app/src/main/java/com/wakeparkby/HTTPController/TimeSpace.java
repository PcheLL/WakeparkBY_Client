package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class TimeSpace {
    //Добавить день
    @Expose
    private int endTime;
    @Expose
    private int startTime;
    @Expose
    private String status;

    public TimeSpace(int endTime, int startTime, String status) {
        this.endTime = endTime;
        this.startTime = startTime;
        this.status = status;
    }
}
