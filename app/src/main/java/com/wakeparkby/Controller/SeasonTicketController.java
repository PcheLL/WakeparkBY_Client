package com.wakeparkby.Controller;


import com.wakeparkby.Client.RetrofitClient;

import com.wakeparkby.HTTPController.SeasonTicketHistory;
import com.wakeparkby.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class SeasonTicketController {
    private static String seasonTicket;
    private static List<SeasonTicketHistory> seasonTicketHistoryList = new ArrayList<>();
    private RetrofitClient retrofitClient = RetrofitClient.getRetrofitClient();
    private Observer observer = new Observer("SeasonTicketController") {
        @Override
        public void update() {
            if (observer.getStatus() == 10) {
                if (observer.getId() == 3) {
                    seasonTicketHistoryList.clear();
                    setSeasonTicketHistoryList(retrofitClient.getSeasonTicketHistoryList());
                    observer.notifyAllObservers(6);
                } else {
                }
            }
        }
    };

    public SeasonTicketController() {
        retrofitClient.getSeasonTicket();
    }

    public SeasonTicketController(String seasonTicket) {
        SeasonTicketController.seasonTicket = seasonTicket;
    }

    public static List<SeasonTicketHistory> getSeasonTicketHistoryList() {
        return seasonTicketHistoryList;
    }

    public static void setSeasonTicketHistoryList(List<SeasonTicketHistory> seasonTicketHistoryList) {
        SeasonTicketController.seasonTicketHistoryList = seasonTicketHistoryList;
    }

    public String getSeasonTicket() {
        return seasonTicket;
    }
}
