package com.wakeparkby.HTTPController;

import com.wakeparkby.Database.App;
import com.wakeparkby.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface HTTPController {

    //@GET("/wakepark-Wakepark/{token}/timeList")
   // Call<List<TimeSpace>> getTimeSpace(@Path("token") String userToken, @Header("location") String place, @Header("data") String data, @Header("reversNumber") int reverseCableNumber);
    @GET("/jwtappdemo-0.0.1-SNAPSHOT/user/booking")
    Call<List<TimeSpace>> getTimeSpace(@Header("Authentication") String token, @Header("location") String place, @Header("data") String data, @Header("reversNumber") int reverseCableNumber);
    @POST("/jwtappdemo-0.0.1-SNAPSHOT/user/booking")
    Call<Booking> postBooking(@Body Booking booking, @Header("clientId") int clientId);
    @GET("/jwtappdemo-0.0.1-SNAPSHOT/users/{id}/seasonTicket")
    Call<String> getSeasonTicket(@Path("id") String number);
    @GET("/jwtappdemo-0.0.1-SNAPSHOT/users/{id}/booked")
    Call<ArrayList<History>> getUserHistory(@Path("id") String userId);
    @DELETE("/jwtappdemo-0.0.1-SNAPSHOT/users/{id}/booked/{bookedId}")
    Call<ResponseBody> deleteHistory(@Path("id") String id , @Path("bookedId") String idHistory);
    @POST("/jwtappdemo-0.0.1-SNAPSHOT/login/regist")
    Call<String> postCreateAccountUser(@Body NewUser newUser);
    @POST("/jwtappdemo-0.0.1-SNAPSHOT/login")
    Call<UserResponse> postSignInUser(@Body User user);
}
