package com.wakeparkby.Activity.History;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.Controller.HistoryController;
import com.wakeparkby.HTTPController.History;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity implements View.OnTouchListener, AdapterView.OnItemClickListener {
    private ArrayList<History> historyArrayList = new ArrayList<>();
    HistoryController historyController = new HistoryController();
    private float fromPosition;
    ListView listView;
    RelativeLayout relativeLayoutProgressBarHistory;
    RelativeLayout relativeLayoutHistory;
    private String userId = "1";

    Observer observer = new Observer("History") {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        relativeLayoutHistory = findViewById(R.id.relativeLayoutHistory);
        relativeLayoutHistory.setOnTouchListener(this);
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        relativeLayoutProgressBarHistory = findViewById(R.id.relativeLayoutProgressBarHistory);
        HistoryController historyController = new HistoryController(userId);
        listView.setVisibility(View.GONE);
        relativeLayoutProgressBarHistory.setVisibility(View.VISIBLE);
    }

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fromPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();

                if (fromPosition < toPosition) {
                    Intent intent = new Intent(this, MainMenuActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.go_prev_in, R.anim.go_prev_out);
                    observer.removeFromList(observer);
                }
            default:
                break;
        }
        return true;
    }

    private void updateHistoryList() {
        historyArrayList = HistoryController.getListHistory();
        ArrayAdapter<History> adapter = new AdapterHistoryArray(this, 0, historyArrayList);
        listView.setAdapter(adapter);
        relativeLayoutProgressBarHistory.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.go_prev_in, R.anim.go_prev_out);
        observer.removeFromList(observer);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        History history = historyArrayList.get(position);
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
                    Toast.makeText(getApplicationContext(), "Отмена невозможна" + System.lineSeparator() + "Осталось меньше 2-x часов", Toast.LENGTH_LONG).show();
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

        } else if (status.equals("MISSED")){
            Toast.makeText(getApplicationContext(), "Вы уже оменили бронирование", Toast.LENGTH_LONG).show();
        } else if (status.equals("VISITED")){
            Toast.makeText(getApplicationContext(), "Вы уже посетили вейкпарк", Toast.LENGTH_LONG).show();

        }


    }

    private void createTwoButtonsAlertDialog(String title, String content, String idHistory) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                        historyController.deleteHistory(userId,idHistory);
                        Toast.makeText(getApplicationContext(), "Вы отменили бронирование", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
