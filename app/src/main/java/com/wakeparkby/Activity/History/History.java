package com.wakeparkby.Activity.History;

public class History {
    private String time;
    private String data;
    private String locationName;
    private String status;
    private String reverseNumber;
    private String image;

    public History(String time, String data, String locationName, String status, String reverseNumber, String image) {
        this.time = time;
        this.data = data;
        this.locationName = locationName;
        this.status = status;
        this.reverseNumber = reverseNumber;
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public String getData() {
        return data;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getStatus() {
        return status;
    }

    public String getReverseNumber() {
        return reverseNumber;
    }

    public String getImage(){
        return image;
    }
}
