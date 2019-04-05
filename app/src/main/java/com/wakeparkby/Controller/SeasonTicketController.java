package com.wakeparkby.Controller;

import android.widget.RelativeLayout;

import com.wakeparkby.Client.RetrofitClient;

public class SeasonTicketController {
    private static String seasonTicket;
    private RetrofitClient retrofitClient = RetrofitClient.getRetrofitClient();

    public SeasonTicketController(String number) {
        retrofitClient.getSeasonTicket(number);
    }

    public SeasonTicketController() {

    }

    public String getSeasonTicket() {
        return seasonTicket;
    }

    public void setSeasonTicket(String seasonTicket) {
        this.seasonTicket = seasonTicket;
    }
}
