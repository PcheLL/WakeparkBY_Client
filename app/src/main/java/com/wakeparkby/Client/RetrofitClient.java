package com.wakeparkby.Client;

import com.wakeparkby.Database.App;
import com.wakeparkby.Database.DataModel;
import com.wakeparkby.Database.DatabaseHelper;
import com.wakeparkby.HTTPController.History;
import com.wakeparkby.Controller.SeasonTicketController;
import com.wakeparkby.HTTPController.Booking;
import com.wakeparkby.HTTPController.HTTPController;
import com.wakeparkby.HTTPController.SeasonTicketHistory;
import com.wakeparkby.HTTPController.NewUser;
import com.wakeparkby.HTTPController.TimeSpace;
import com.wakeparkby.HTTPController.UserResponse;
import com.wakeparkby.HTTPController.User;
import com.wakeparkby.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient retrofitClient = new RetrofitClient();
    private DatabaseHelper databaseHelper;
    private Retrofit retrofit;
    private HTTPController httpController;
    private List<TimeSpace> listTimeSpace = new ArrayList<>();
    private List<SeasonTicketHistory> seasonTicketHistoryList = new ArrayList<>();
    private List<History> historyArrayList = new ArrayList<>();
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
        databaseHelper = App.getInstance().getDatabaseInstance();
        String token = "Bearer_" + databaseHelper.getDataDao().getByTitle("UserToken").get(0).getDescription().toString();
        httpController.getTimeSpace(token, place, date, reverseCableNumber).enqueue(new Callback<List<TimeSpace>>() {
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
        String token = "Bearer_" + databaseHelper.getDataDao().getByTitle("UserToken").get(0).getDescription().toString();
        Call<Booking> call = httpController.postBooking(token,booking);
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

    public void getSeasonTicket() {
        databaseHelper = App.getInstance().getDatabaseInstance();
        String token = "Bearer_" + databaseHelper.getDataDao().getByTitle("UserToken").get(0).getDescription().toString();
        httpController
                .getSeasonTicket(token).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.toString());
                if (response.isSuccessful()) {
                    String seasonTicket = response.body();
                    SeasonTicketController seasonTicketController = new SeasonTicketController(seasonTicket);
                 //   observer.notifyAllObservers(3);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });

        httpController
                .getSeasonTicketHistory(token).enqueue(new Callback<List<SeasonTicketHistory>>() {
            @Override
            public void onResponse(Call<List<SeasonTicketHistory>> call, Response<List<SeasonTicketHistory>> response) {
                System.out.println(response.toString());
                if (response.isSuccessful()) {
                    seasonTicketHistoryList = response.body();
           //         observer.notifyAllObservers(3);
                }
            }

            @Override
            public void onFailure(Call<List<SeasonTicketHistory>> call, Throwable t) {
            }
        });

    }

    public List<SeasonTicketHistory> getSeasonTicketHistoryList() {
        return seasonTicketHistoryList;
    }

    public void getUserHistory() {
        databaseHelper = App.getInstance().getDatabaseInstance();
        String token = "Bearer_" + databaseHelper.getDataDao().getByTitle("UserToken").get(0).getDescription().toString();
        httpController.getUserHistory(token).enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                System.out.println(response.toString());
                if (response.isSuccessful()) {
                    historyArrayList = response.body();
                    //   setListTimeSpace(listTimeSpace);
                    //BookingController bookingController = new BookingController(place,date,reverseCableNumber);
                    observer.notifyAllObservers(4);
                }
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {
            }
        });
    }

    public List<History> getHistoryArrayList() {
        return historyArrayList;
    }

    public void deleteHistory(String userId, String idHistory) {
        httpController.deleteHistory(userId, idHistory).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                 //   getUserHistory(userId);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void postCreateAccountUser(NewUser newUser) {
        Call<String> call = httpController.postCreateAccountUser(newUser);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.toString());
                if (response.isSuccessful()) {
                    String answer = response.body().toString();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void postSignInUser(User userBody) {
        Call<UserResponse> call = httpController.postSignInUser(userBody);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                System.out.println(response.toString());
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    DatabaseHelper databaseHelper = App.getInstance().getDatabaseInstance();
                    DataModel model = new DataModel();
                    model.setTitle("UserToken");
                    model.setDescription(response.body().getToken().toString());
                    databaseHelper.clearAllTables();
                    databaseHelper.getDataDao().insert(model);
                    observer.notifyAllObservers(8);
                } else {
                    observer.notifyAllObservers(9);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
    }

}
