package com.wakeparkby.Activity.MainMenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.wakeparkby.Activity.Welcome.WelcomeActivity;
import com.wakeparkby.Database.App;
import com.wakeparkby.Database.DatabaseHelper;
import com.wakeparkby.Fragment.FragmentHistory;
import com.wakeparkby.Fragment.FragmentLocationSelection;
import com.wakeparkby.Fragment.FragmentMaps;
import com.wakeparkby.Fragment.FragmentSeasonTickets;
import com.wakeparkby.Fragment.FragmentWeather;
import com.wakeparkby.R;

import java.util.HashMap;
import java.util.Stack;

public class MainMenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private HashMap<String, Stack<Fragment>> mStacks;
    public static final String TAB_HOME = "tab_home";
    // private String mCurrentTab;
    private int fl_exit = 0;
    private DatabaseHelper databaseHelper;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.getMenu().findItem(R.id.nav_choosePl).setChecked(true);
        loadFragment(FragmentLocationSelection.newInstance());
        mStacks = new HashMap<String, Stack<Fragment>>();
        mStacks.put(TAB_HOME, new Stack<Fragment>());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Fragment fragment_locationSelection = new FragmentLocationSelection();
        mStacks.get("tab_home").push(fragment_locationSelection);
    }

    private void selectedTab(String tabId) {
        //mCurrentTab = tabId; - Если создавать несколько стеков фрагментов
        pushFragments(tabId, new FragmentLocationSelection(), true); // для запуска последнего фрагмента УДАЛИТЬ
        /*if(mStacks.get(tabId).size() == 0){
            if(tabId.equals(TAB_HOME)){
                pushFragments(tabId, new FragmentLocationSelection(),true);
            }
        }else {
            pushFragments(tabId, mStacks.get(tabId).lastElement(),false); // для запуска последнего фрагмента
        }*/
    }

    public void pushFragments(String tag, Fragment fragment, boolean shouldAdd) {
        fl_exit = 0;
        if (shouldAdd)
            mStacks.get(tag).push(fragment);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }

    public void popFragments() {
        /*
         *    Select the second last fragment in current tab's stack..
         *    which will be shown after the fragment transaction given below
         */
        if (mStacks.get(TAB_HOME).size() == 1) { // РЕШИТЬ ПРОБЛЕМУ С ОДИНАКОВЫМИ ФРАГМЕНТАМИ
            if (fl_exit == 1)
            {
               databaseHelper = App.getInstance().getDatabaseInstance();
               databaseHelper.clearAllTables();
                finish();
            }
            else {
                Toast.makeText(this, "Нажмите еще раз чтобы выйти", Toast.LENGTH_SHORT).show();
                fl_exit = 1;
            }
        } else {
            Fragment fragment = mStacks.get(TAB_HOME).elementAt(mStacks.get(TAB_HOME).size() - 2);

            /*pop current fragment from stack.. */
            mStacks.get(TAB_HOME).pop();

            /* We have the target fragment in hand.. Just show it.. Show a standard navigation animation*/
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fl_content, fragment);
            ft.commit();
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
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
            databaseHelper = App.getInstance().getDatabaseInstance();
            databaseHelper.clearAllTables();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_choosePl:
                fl_exit = 0;
                selectedTab(TAB_HOME);
                return true;
            case R.id.nav_mySeasonTickets:
                fl_exit = 0;
                loadFragment(FragmentSeasonTickets.newInstance());
                return true;
            case R.id.nav_maps:
                fl_exit = 0;
                loadFragment(FragmentMaps.newInstance());
                return true;
            case R.id.nav_history:
                fl_exit = 0;
                loadFragment(FragmentHistory.newInstance());
                return true;
            case R.id.nav_weather:
                fl_exit = 0;
                loadFragment(FragmentWeather.newInstance());
                return true;
        }
        return false;
    }

    public interface OnBackPressedListner {
        boolean onBackPressed();
    }

    @Override
    public void onBackPressed() {

        popFragments();
    }
}