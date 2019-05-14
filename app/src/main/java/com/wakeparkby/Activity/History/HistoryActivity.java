package com.wakeparkby.Activity.History;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.R;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private ArrayList<History> historyArrayList = new ArrayList<>();
    private float fromPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        historyArrayList.add(new History("19:45 - 19:55","20.05.2019","Логойск","1","2 реверс","ic_booked"));
        historyArrayList.add(new History("12:00 - 13:30","19.05.2019","Дрозды","1","1 реверс","ic_booked"));
        historyArrayList.add(new History("10:05 - 13:30","18.05.2019","Логойск","3","3 реверс","ic_cancellation"));
        historyArrayList.add(new History("20:10","17.05.2019","Абонемент","4","+50 минут",""));
        historyArrayList.add(new History("14:15 - 15:30","17.05.2019","Дрозды","3","1 реверс","ic_cancellation"));
        historyArrayList.add(new History("10:20 - 13:30","16.05.2019","Логойск","1","3 реверс","ic_booked"));
        historyArrayList.add(new History("9:25","16.05.2019","Абонемент","5","-15 минут",""));
        historyArrayList.add(new History("17:50 - 18:30","14.05.2019","Дрозды","3","1 реверс","ic_cancellation"));
        historyArrayList.add(new History("17:00 - 17:30","14.05.2019","Логойск","2","3 реверс","ic_perform"));
        historyArrayList.add(new History("15:30 - 15:35","14.05.2019","Логойск","2","2 реверс","ic_perform"));
        historyArrayList.add(new History("10:05 - 11:00","14.05.2019","Дрозды","2","1 реверс","ic_perform"));
        historyArrayList.add(new History("9:15 - 10:00","14.05.2019","Логойск","2","1 реверс","ic_perform"));

//create our new array adapter
        ArrayAdapter<History> adapter = new AdapterHistoryArray(this, 0, historyArrayList);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
    public boolean onTouch(View view, MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                fromPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();

                if (fromPosition < toPosition)
                {
                    Intent intent = new Intent(this, MainMenuActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.go_prev_in,R.anim.go_prev_out);
                }
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.go_prev_in,R.anim.go_prev_out);
    }
}
