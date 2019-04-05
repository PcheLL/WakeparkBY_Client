package com.wakeparkby.Client;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.Controller.SeasonTicketController;
import com.wakeparkby.HTTPController.Booking;
import com.wakeparkby.HTTPController.HTTPController;
import com.wakeparkby.HTTPController.TimeSpace;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient retrofitClient = new RetrofitClient();

    private Retrofit retrofit;
    private HTTPController httpController;
    private List<TimeSpace> listTimeSpace = new ArrayList<>();
    private Observer observer = new Observer("Retrofit");


    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.TIMESPACE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        httpController = retrofit.create(HTTPController.class);
        observer.setStatus(5);

    }

    public static RetrofitClient getRetrofitClient() {
        return retrofitClient;
    }

    public void getTimeSpace(final String place, final String date, final int reverseCableNumber) {
        httpController.getTimeSpace(place, date, reverseCableNumber).enqueue(new Callback<List<TimeSpace>>() {
            @Override
            public void onResponse(Call<List<TimeSpace>> call, Response<List<TimeSpace>> response) {
                System.out.println(response.toString());
                if (response.isSuccessful()) {
                    listTimeSpace = response.body();
                    setListTimeSpace(listTimeSpace);
                    //BookingController bookingController = new BookingController(place,date,reverseCableNumber);
                    observer.notifyAllObservers(1);
                }
            }

            @Override
            public void onFailure(Call<List<TimeSpace>> call, Throwable t) {
            }
        });
    }

    public void postBooking(Booking booking) {
        Call<Booking> call = httpController.postBooking(booking, 1);
        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                System.out.println(response.toString());
                if (response.isSuccessful()) {
                    Booking booking = response.body();
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
            }
        });
    }

    public void setListTimeSpace(List<TimeSpace> listTimeSpace) {
        this.listTimeSpace = listTimeSpace;
    }

    public List<TimeSpace> getListTimeSpace() {
        return listTimeSpace;
    }

    public void getSeasonTicket(String number) {
        httpController.getSeasonTicket(number).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.toString());
                if (response.isSuccessful()) {
                    String seasonTicket = response.body();
                    setListTimeSpace(listTimeSpace);
                    SeasonTicketController seasonTicketController = new SeasonTicketController();
                    seasonTicketController.setSeasonTicket(seasonTicket);
                    observer.notifyAllObservers(3);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
