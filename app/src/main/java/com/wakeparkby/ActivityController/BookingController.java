package com.wakeparkby.ActivityController;

import com.wakeparkby.Client.RetrofitClient;
import com.wakeparkby.HTTPController.Booking;

public class BookingController {
    private RetrofitClient retrofitClient = RetrofitClient.getRetrofitClient();
    public BookingController(String place, String data) {
        retrofitClient.postBooking(new Booking(data,place,1,1000,1200));
       // retrofitClient.getTimeSpace(data);

    }
}
