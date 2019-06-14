package com.wakeparkby.Activity.Booking;

public class newChooseTimeItem {
    String startHours;
    String startMinutes;
    String endHours;
    String endMinutes;
    String status;


    public newChooseTimeItem(String startHours, String startMinutes, String endHours, String endMinutes, String status) {
        this.startHours = startHours;
        this.startMinutes = startMinutes;
        this.endHours = endHours;
        this.endMinutes = endMinutes;
        this.status = status;
    }

    public String getStartHours() {
        return startHours;
    }


    public String getStartMinutes() {
        return startMinutes;
    }


    public String getEndHours() {
        return endHours;
    }


    public String getEndMinutes() {
        return endMinutes;
    }

    public String getStatus() { return status; }
}
