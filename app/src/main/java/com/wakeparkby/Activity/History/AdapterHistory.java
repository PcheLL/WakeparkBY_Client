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
        return this.history.getLocation();
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

  /*  public String getImage() {
        return this.history.getImage();
    }*/
}