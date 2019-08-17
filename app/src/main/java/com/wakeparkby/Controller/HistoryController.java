package com.wakeparkby.Controller;

import com.wakeparkby.Database.App;
import com.wakeparkby.Database.DatabaseHelper;
import com.wakeparkby.HTTPController.History;
import com.wakeparkby.Client.RetrofitClient;
import com.wakeparkby.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class HistoryController {

    private RetrofitClient retrofitClient = RetrofitClient.getRetrofitClient();
    private static List<History> listHistory = new ArrayList<>();
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

    public HistoryController() {
       retrofitClient.getUserHistory();
    }

    public static void setListHistory(List<History> listHistory) {
        HistoryController.listHistory = listHistory;
    }

    public static List<History> getListHistory() {
        return listHistory;
    }

    public void deleteHistory(String userId, String idHistory) {
        retrofitClient.deleteHistory(userId,idHistory);
    }
}
