package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class SeasonTicketHistory {
    @Expose
    private String date;
    @Expose
    private int time;
    @Expose
    private String status;
}
