package com.wakeparkby.Client;

import com.wakeparkby.HTTPController.History;
import com.wakeparkby.Controller.SeasonTicketController;
import com.wakeparkby.HTTPController.Booking;
import com.wakeparkby.HTTPController.HTTPController;
import com.wakeparkby.HTTPController.TimeSpace;
import com.wakeparkby.Observer.Observer;

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
    private ArrayList<History> historyArrayList= new ArrayList<>();

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


    public void getUserHistory(String userId) {
        httpController.getUserHistory(userId).enqueue(new Callback<ArrayList<History>>() {
            @Override
            public void onResponse(Call<ArrayList<History>> call, Response<ArrayList<History>> response) {
                System.out.println(response.toString());
                if (response.isSuccessful()) {
                    historyArrayList = response.body();
                 //   setListTimeSpace(listTimeSpace);
                    //BookingController bookingController = new BookingController(place,date,reverseCableNumber);
                    observer.notifyAllObservers(4);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<History>> call, Throwable t) {
            }
        });
    }

    public ArrayList<History> getHistoryArrayList() {
        return historyArrayList;
    }

    public void setHistoryArrayList(ArrayList<History> historyArrayList) {
        this.historyArrayList = historyArrayList;
    }
}
