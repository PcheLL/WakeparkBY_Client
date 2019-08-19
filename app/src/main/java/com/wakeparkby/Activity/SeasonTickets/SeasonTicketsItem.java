package com.wakeparkby.Activity.SeasonTickets;

import lombok.Data;

@Data
public class SeasonTicketsItem {
    private String date;
    private String time;
    private String status;

    public SeasonTicketsItem(String date, String time, String status) {
        this.date = date;
        this.time = time;
        this.status = status;
    }
}
