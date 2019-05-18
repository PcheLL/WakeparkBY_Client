package com.wakeparkby.Controller;

import com.wakeparkby.HTTPController.History;
import com.wakeparkby.Client.RetrofitClient;
import com.wakeparkby.Observer.Observer;

import java.util.ArrayList;

public class HistoryController {

    private RetrofitClient retrofitClient = RetrofitClient.getRetrofitClient();
    private static ArrayList<History> listHistory = new ArrayList<>();
    Observer observer = new Observer("HistoryController") {
        @Override
        public void update() {
            if (observer.getStatus() == 10) {
                if (observer.getId() == 4) {
                    listHistory.clear();
                    setListHistory(retrofitClient.getHistoryArrayList());
                    observer.notifyAllObservers(5);
                } else {
                }
            }
        }
    };

    public HistoryController(String userId) {
        retrofitClient.getUserHistory(userId);
    }

    public HistoryController() {
    }

    public static void setListHistory(ArrayList<History> listHistory) {
        HistoryController.listHistory = listHistory;
    }

    public static ArrayList<History> getListHistory() {
        return listHistory;
    }

    public void deleteHistory(String userId, String idHistory) {
        retrofitClient.deleteHistory(userId,idHistory);
    }
}
