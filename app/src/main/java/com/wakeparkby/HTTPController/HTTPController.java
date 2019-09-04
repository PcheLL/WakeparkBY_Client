package com.wakeparkby.HTTPController;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface HTTPController {
    @GET("/jwtappdemo-0.0.1-SNAPSHOT/user/timeList")
    Call<List<TimeSpace>> getTimeSpace(@Header("Authorization") String token, @Header("location") String place, @Header("data") String data, @Header("reversNumber") int reverseCableNumber);

    @POST("/jwtappdemo-0.0.1-SNAPSHOT/user/booking")
    Call<Booking> postBooking(@Header("Authorization") String token, @Body Booking booking);

    @GET("/jwtappdemo-0.0.1-SNAPSHOT/user/seasonTicket")
    Call<String> getSeasonTicket(@Header("Authorization") String token);

    @GET("/jwtappdemo-0.0.1-SNAPSHOT/user/history")
    Call<List<History>> getUserHistory(@Header("Authorization") String token);

    @DELETE("/jwtappdemo-0.0.1-SNAPSHOT/user/booking/{id}")
    Call<ResponseBody> deleteHistory(@Header("Authorization") String token, @Path("id") String idHistory);

    @POST("/jwtappdemo-0.0.1-SNAPSHOT/login/regist")
    Call<String> postCreateAccountUser(@Body NewUser newUser);

    @POST("/jwtappdemo-0.0.1-SNAPSHOT/login")
    Call<UserResponse> postSignInUser(@Body User user);

    @GET("/jwtappdemo-0.0.1-SNAPSHOT/user/seasonTicketHistory")
    Call<List<SeasonTicketHistory>> getSeasonTicketHistory(@Header("Authorization") String token);

    @PUT("/jwtappdemo-0.0.1-SNAPSHOT/user/booking")
    Call<ResponseBody> putBookingList(@Header("Authorization") String token, @Body List<Booking> bookingList);
}
