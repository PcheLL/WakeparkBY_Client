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
import android.widget.Button;

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

public class MainMenuActivity extends AppCompatActivity {
    private HashMap<String, Stack<Fragment>> mStacks;
    public static final String TAB_HOME  = "tab_home";
    private String mCurrentTab;
    private DatabaseHelper databaseHelper;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_choosePl:
                    selectedTab(TAB_HOME);
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

    private void selectedTab(String tabId) {
        mCurrentTab = tabId;

        if(mStacks.get(tabId).size() == 0){
            /*
             *    First time this tab is selected. So add first fragment of that tab.
             *    Dont need animation, so that argument is false.
             *    We are adding a new fragment which is not present in stack. So add to stack is true.
             */
            if(tabId.equals(TAB_HOME)){
                pushFragments(tabId, new FragmentLocationSelection(),true);
            }
        }else {
            /*
             *    We are switching tabs, and target tab is already has atleast one fragment.
             *    No need of animation, no need of stack pushing. Just show the target fragment
             */
            pushFragments(tabId, mStacks.get(tabId).lastElement(),false);
        }
    }
    public void pushFragments(String tag, Fragment fragment, boolean shouldAdd){
        if(shouldAdd)
            mStacks.get(tag).push(fragment);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }

    public void popFragments(){
        /*
         *    Select the second last fragment in current tab's stack..
         *    which will be shown after the fragment transaction given below
         */
        Fragment fragment = mStacks.get(mCurrentTab).elementAt(mStacks.get(mCurrentTab).size() - 2);

        /*pop current fragment from stack.. */
        mStacks.get(mCurrentTab).pop();

        /* We have the target fragment in hand.. Just show it.. Show a standard navigation animation*/
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.commit();
    }

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
        mStacks = new HashMap<String, Stack<Fragment>>();
        mStacks.put(TAB_HOME, new Stack<Fragment>());
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
            databaseHelper = App.getInstance().getDatabaseInstance();
            databaseHelper.clearAllTables();
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}