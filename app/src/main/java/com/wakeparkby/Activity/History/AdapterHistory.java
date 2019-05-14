package com.wakeparkby.Activity.History;

public class AdapterHistory {
    History history;

    public AdapterHistory(History history) {
        this.history = history;
    }

    public String getTime() {
        return this.history.getTime();
    }

    public String getData() {
        return this.history.getData();
    }

    public String getLocationName() {
        return this.history.getLocationName();
    }

    public String getStatus() {
        return this.history.getStatus();
    }

    public String getReverseNumber() {
        return this.history.getReverseNumber();
    }

    public String getImage() {
        return this.history.getImage();
    }
}
