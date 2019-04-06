package com.wakeparkby.Activity.MainMenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;


import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.wakeparkby.Activity.Booking.LocationSelectionActivity;
import com.wakeparkby.Activity.History.HistoryActivity;
import com.wakeparkby.Activity.Price.PriceActivity;
import com.wakeparkby.Activity.SeasonTickets.SeasonTicketsActivity;
import com.wakeparkby.Activity.SignIn.SignInActivity;
import com.wakeparkby.R;

public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent_LocationSelection = new Intent(this, LocationSelectionActivity.class);
        Intent intent_SeasonTickets = new Intent(this, SeasonTicketsActivity.class);
        Intent intent_Price = new Intent(this, PriceActivity.class);
        Intent intent_History = new Intent(this, HistoryActivity.class);
        Intent intent_SignIn = new Intent(this, SignInActivity.class);
        int id = item.getItemId();

        if (id == R.id.nav_ChoosePl) {
            startActivity(intent_LocationSelection);
        } else if (id == R.id.nav_MySeasonTickets) {
            startActivity(intent_SeasonTickets);
        } else if (id == R.id.nav_Price) {
            startActivity(intent_Price);
        } else if (id == R.id.nav_History) {
            startActivity(intent_History);
        } else if (id == R.id.nav_Exit) {
            FirebaseAuth.getInstance().signOut();
            startActivity(intent_SignIn);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}