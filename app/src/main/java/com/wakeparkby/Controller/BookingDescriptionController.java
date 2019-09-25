package com.wakeparkby.Controller;

import com.wakeparkby.Client.RetrofitClient;
import com.wakeparkby.HTTPController.Booking;

import java.util.List;

public class BookingDescriptionController {
    private RetrofitClient retrofitClient = RetrofitClient.getRetrofitClient();

    public BookingDescriptionController(List<Booking> bookingList) {
        retrofitClient.putBookingList(bookingList);
    }
}
