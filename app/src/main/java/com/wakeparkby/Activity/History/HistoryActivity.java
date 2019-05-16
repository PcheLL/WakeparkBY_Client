package com.wakeparkby.Activity.History;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.Controller.HistoryController;
import com.wakeparkby.HTTPController.History;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements View.OnTouchListener {
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


}
