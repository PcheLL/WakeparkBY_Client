package com.wakeparkby.Activity.History;

import com.wakeparkby.HTTPController.History;

public class AdapterHistory {
    History history;

    public AdapterHistory(History history) {
        this.history = history;
    }

   /* public String getTime() {
        return this.history.get();
    }*/

    public String getData() {
        return this.history.getData();
    }

    public String getLocationName() {
        String historyLocationName = null;
        if ((String.valueOf(history.getLocation()).equals("LOGOISK"))){
            historyLocationName = "Логойск";
        } else if ((String.valueOf(history.getLocation()).equals("DROZDI"))){
            historyLocationName = "Дрозды";
        }
        return historyLocationName;
    }

    public String getStatus() {
        return this.history.getStatus();
    }

    public String getReversNumber() {
        return this.history.getReversNumber();
    }

    public String getHistoryId() {
        return this.history.getId();
    }

    public String getTime() {
        int start = Integer.valueOf(history.getStartTime());
        int end = Integer.valueOf(history.getEndTime());
        int startHours = start / 60;
        int startMinutes = start - startHours * 60;
        int endHours = end / 60;
        int endMinutes = end - endHours * 60;
        String historyTime = null;

        if (startMinutes == 0) {
            if (endMinutes == 0) {
                historyTime = startHours + ":" + startMinutes + "0 - " + endHours + ":" + endMinutes + "0";
            } else {
                historyTime = startHours + ":" + startMinutes + "0 - " + endHours + ":" + endMinutes;
            }
        } else if (endMinutes == 0) {
            historyTime = startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes + "0";
        } else {
            historyTime = startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes;
        }
        return historyTime;
    }

    public String getStartTime (){
        return this.history.getStartTime();
    }
}