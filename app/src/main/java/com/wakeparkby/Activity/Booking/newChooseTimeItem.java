package com.wakeparkby.Activity.Booking;

import lombok.Data;

@Data
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

}
