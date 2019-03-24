package com.wakeparkby.HTTPController;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface HTTPController {
    @GET("/wakepark-Wakepark/timeList")
    Call<List<TimeSpace>> getTimeSpace(@Header("data") String data);
    @POST("/wakepark-Wakepark/timeList")
    Call<Booking> postBooking(@Body Booking booking,@Header("clientId") int clientId);
}
