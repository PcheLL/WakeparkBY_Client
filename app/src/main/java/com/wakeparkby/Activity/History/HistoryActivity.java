package com.wakeparkby.Activity.History;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.R;

public class HistoryActivity extends AppCompatActivity implements View.OnTouchListener {
    private float fromPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutHistory);
        relativeLayout.setOnTouchListener(this);
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
