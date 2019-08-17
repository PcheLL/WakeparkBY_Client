package com.wakeparkby.Activity.MainMenu;



import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.wakeparkby.Database.DatabaseHelper;
import com.wakeparkby.Fragment.FragmentHistory;
import com.wakeparkby.Fragment.FragmentLocationSelection;
import com.wakeparkby.Fragment.FragmentMaps;
import com.wakeparkby.Fragment.FragmentPrice;
import com.wakeparkby.Fragment.FragmentSeasonTickets;
import com.wakeparkby.Fragment.FragmentWeather;
import com.wakeparkby.R;

public class MainMenuActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_choosePl:
                    loadFragment(FragmentLocationSelection.newInstance());
                    return true;
                case R.id.nav_mySeasonTickets:
                    loadFragment(FragmentSeasonTickets.newInstance());
                    return true;
                case R.id.nav_maps:
                    loadFragment(FragmentMaps.newInstance());
                    return true;
                case R.id.nav_history:
                    loadFragment(FragmentHistory.newInstance());
                    return true;
                case R.id.nav_weather:
                    loadFragment(FragmentWeather.newInstance());
                    return true;
            }
            return false;
        }
    };


    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().findItem(R.id.nav_choosePl).setChecked(true);
        loadFragment(FragmentLocationSelection.newInstance());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            databaseHelper.getDataDao().getByTitle("UserToken").clear();
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}