package com.wakeparkby.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.wakeparkby.Activity.History.HistoryAdapter;
import com.wakeparkby.Controller.HistoryController;
import com.wakeparkby.HTTPController.History;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment implements AdapterView.OnItemClickListener {
    private LinearLayout linearLayoutHistory;
    private RecyclerView NewsRecyclerView;
    private HistoryAdapter HistoryAdapter;
    private List<History> historyList = new ArrayList<>();
    private RelativeLayout relativeLayoutProgressBarHistory;


    Observer observer = new Observer("FragmentChooseTime") {
        @Override
        public void update() {
            int n = observer.getStatus();
            if (n == 10) {
                if (observer.getId() == 5) {
                    updateHistoryList();
                    observer.setId(0);
                } else {
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_history, container, false);
        NewsRecyclerView = rootView.findViewById(R.id.recyclerViewHistory);
        relativeLayoutProgressBarHistory = rootView.findViewById(R.id.relativeLayoutProgressBarHistory);
        linearLayoutHistory = rootView.findViewById(R.id.linearLayoutHistory);
        HistoryController historyController = new HistoryController();
        /*if (isNetworkAvailable(getContext())) {
        } else {
            System.out.print("Нет соединения");
            // если сети нет показываем Тост или
            // кидаем на активити с красивым дизайном где просим сделать реконнект
        }*/
        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle("История");
        linearLayoutHistory.setVisibility(View.GONE);
        relativeLayoutProgressBarHistory.setVisibility(View.VISIBLE);
        return rootView;
    }

    public FragmentHistory() {
    }

    public static FragmentHistory newInstance() {
        return new FragmentHistory();
    }

    private void updateHistoryList() {
        historyList = HistoryController.getListHistory();

       /* for (int i = 0; i < bookingController.getFinalTimeSpaceList().size();i++) {
            mData.add(new ChooseTimeItem(bookingController.getFinalTimeSpaceList().get(i).getStartHours(), bookingController.getFinalTimeSpaceList().get(i).getStartMinutes(),
                    bookingController.getFinalTimeSpaceList().get(i).getEndHours(), bookingController.getFinalTimeSpaceList().get(i).getEndMinutes(),
                    bookingController.getFinalTimeSpaceList().get(i).getStatus()));
        }*/
        HistoryAdapter = new HistoryAdapter(getContext(), historyList);
        NewsRecyclerView.setAdapter(HistoryAdapter);
        NewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        relativeLayoutProgressBarHistory.setVisibility(View.GONE);
        linearLayoutHistory.setVisibility(View.VISIBLE);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      /*  History history = historyList.get(position);
        AdapterHistory adapterHistory = new AdapterHistory(history);
        String status = adapterHistory.getStatus();
        if (status.equals("BOOKED")) {
            String data = adapterHistory.getData();
            String time = adapterHistory.getTime();
            long yourmilliseconds = System.currentTimeMillis();
            SimpleDateFormat dataFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date resultDate = new Date(yourmilliseconds);
            String dataNow = dataFormat.format(resultDate);
            if (dataNow.equals(data)) {
                long milliseconds = System.currentTimeMillis();
                SimpleDateFormat timeHoursFormat = new SimpleDateFormat("HH");
                SimpleDateFormat timeMinutesFormat = new SimpleDateFormat("mm");
                Date resultTime = new Date(milliseconds);
                String hoursNow = timeHoursFormat.format(resultTime);
                String minutesNow = timeMinutesFormat.format(resultTime);
                int timeNow = Integer.valueOf(hoursNow) * 60 + Integer.valueOf(minutesNow);
                if (timeNow > Integer.valueOf(adapterHistory.getStartTime()) - 120) {
                    Toast.makeText(getContext(), "Отмена невозможна" + System.lineSeparator() + "Осталось меньше 2-x часов", Toast.LENGTH_LONG).show();
                } else {
                    String idHistory = adapterHistory.getHistoryId();
                    String location = adapterHistory.getLocationName();
                    String reversNumber = adapterHistory.getReversNumber();
                    System.out.print(idHistory);
                    createTwoButtonsAlertDialog("Отмена бронирования", "Отменить броинрование ?" + System.lineSeparator() + System.lineSeparator() + "Место: " + location + " ( " +
                            reversNumber + " реверс )" + System.lineSeparator() + "Дата: " + data + System.lineSeparator() + "Время: " + time, idHistory);
                }
            } else {
                String idHistory = adapterHistory.getHistoryId();
                String location = adapterHistory.getLocationName();
                String reversNumber = adapterHistory.getReversNumber();
                System.out.print(idHistory);
                createTwoButtonsAlertDialog("Отмена бронирования", "Отменить броинрование ?" + System.lineSeparator() + System.lineSeparator() + "Место: " + location + " ( " +
                        reversNumber + " реверс )" + System.lineSeparator() + "Дата: " + data + System.lineSeparator() + "Время: " + time, idHistory);
            }

        } else if (status.equals("MISSED")) {
            Toast.makeText(getContext(), "Вы уже оменили бронирование", Toast.LENGTH_LONG).show();
        } else if (status.equals("VISITED")) {
            Toast.makeText(getContext(), "Вы уже посетили вейкпарк", Toast.LENGTH_LONG).show();

        }

*/
    }

    private void createTwoButtonsAlertDialog(String title, String content, String idHistory) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNegativeButton("Вернуться",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                });
        builder.setPositiveButton("Отменить",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                       // historyController.deleteHistory(userId, idHistory);
                        Toast.makeText(getContext(), "Вы отменили бронирование", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        observer.removeFromList(observer);
    }
}
