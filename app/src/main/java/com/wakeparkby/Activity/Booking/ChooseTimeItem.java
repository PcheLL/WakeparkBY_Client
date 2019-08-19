package com.wakeparkby.Activity.Booking;

import lombok.Data;

@Data
public class ChooseTimeItem {
    private String startHours;
    private String startMinutes;
    private String endHours;
    private String endMinutes;
    private String status;


    public ChooseTimeItem(String startHours, String startMinutes, String endHours, String endMinutes, String status) {
        this.startHours = startHours;
        this.startMinutes = startMinutes;
        this.endHours = endHours;
        this.endMinutes = endMinutes;
        this.status = status;
    }

}
