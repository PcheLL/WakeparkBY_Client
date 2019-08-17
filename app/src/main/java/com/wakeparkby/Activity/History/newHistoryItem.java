package com.wakeparkby.Activity.History;

public class newHistoryItem {
    String startHours;
    String startMinutes;
    String endHours;
    String endMinutes;
    String status;
    String place;
    String date;
    String reversNumber;

    public newHistoryItem(String startHours, String startMinutes, String endHours, String endMinutes, String status, String place, String date, String reversNumber) {
        this.startHours = startHours;
        this.startMinutes = startMinutes;
        this.endHours = endHours;
        this.endMinutes = endMinutes;
        this.status = status;
        this.place = place;
        this.date = date;
        this.reversNumber = reversNumber;
    }
}
