package com.wakeparkby.Activity.MainMenu;



import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import com.wakeparkby.Fragment.FragmentHistory;
import com.wakeparkby.Fragment.FragmentLocationSelection;
import com.wakeparkby.Fragment.FragmentPrice;
import com.wakeparkby.Fragment.FragmentSeasonTickets;
import com.wakeparkby.Fragment.FragmentWeather;
import com.wakeparkby.R;

public class MainMenuActivity extends AppCompatActivity {

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
                case R.id.nav_price:
                    loadFragment(FragmentPrice.newInstance());
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
    }

}