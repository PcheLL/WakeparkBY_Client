package com.wakeparkby.Activity.MainMenu;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;


import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.wakeparkby.Activity.Booking.LocationSelectionActivity;
import com.wakeparkby.Activity.History.HistoryActivity;
import com.wakeparkby.Activity.Onboarding.OnboardingActivity;
import com.wakeparkby.Activity.Price.PriceActivity;
import com.wakeparkby.Activity.SeasonTickets.SeasonTicketsActivity;
import com.wakeparkby.Activity.SignIn.SignInActivity;
import com.wakeparkby.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView textView_Air_Temperature_Logoysk;
    TextView textView_Wind_Speed_Logoysk;
    TextView textView_Weather_Logoysk;

    TextView textView_Air_Temperature_Drozdy;
    TextView textView_Wind_Speed_Drozdy;
    TextView textView_Weather_Drozdy;

    NavigationView navigationView;
    AppBarLayout appBarLayout;
    ConstraintLayout constraintLayout;
    RelativeLayout relativeLayoutProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences =
                getSharedPreferences("my_preferences", MODE_PRIVATE);

        if(!preferences.getBoolean("onboarding_complete",false)){

            Intent onboarding = new Intent(this, OnboardingActivity.class);
            startActivity(onboarding);

            finish();
            return;
        }
        else {

            setContentView(R.layout.activity_main_menu);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            appBarLayout = findViewById(R.id.appbar);
            constraintLayout = findViewById(R.id.content_main);
            relativeLayoutProgressBar = findViewById(R.id.relativeLayoutProgressBarMainMenu);
            textView_Air_Temperature_Logoysk = findViewById(R.id.textView_Air_Temperature_Logoysk);
            textView_Wind_Speed_Logoysk = findViewById(R.id.textView_Wind_Speed_Logoysk);
            textView_Weather_Logoysk = findViewById(R.id.textView_Weather_Logoysk);

            textView_Air_Temperature_Drozdy = findViewById(R.id.textView_Air_Temperature_Drozdy);
            textView_Wind_Speed_Drozdy = findViewById(R.id.textView_Wind_Speed_Drozdy);
            textView_Weather_Drozdy = findViewById(R.id.textView_Weather_Drozdy);

            //appBarLayout.setVisibility(View.GONE);
            constraintLayout.setVisibility(View.GONE);
            relativeLayoutProgressBar.setVisibility(View.VISIBLE);

            refreshWeather();
        }
    }

    private void refreshWeather() {
        Thread newThread = new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void run() {
                Document docLogoysk;
                Document docDrozdy;
                try {
                    docLogoysk = Jsoup.connect("https://yandex.by/pogoda/minsk?lat=54.18134156547551&lon=27.810362236397864").get();
                    textView_Air_Temperature_Logoysk.setText(String.valueOf(docLogoysk.select("body > div > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.fact_size_m.fact_with-hourly-prognosis > div.fact__temp-wrap > a > div.temp.fact__temp.fact__temp_size_s > span.temp__value").first().childNode(0)));
                    textView_Wind_Speed_Logoysk.setText(String.valueOf(docLogoysk.select("body > div > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.fact_size_m.fact_with-hourly-prognosis > div.fact__props.fact__props_position_middle > dl.term.term_orient_v.fact__wind-speed > dd > span.wind-speed").first().childNode(0)));
                    textView_Weather_Logoysk.setText(String.valueOf(docLogoysk.select("body > div > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.fact_size_m.fact_with-hourly-prognosis > div.fact__temp-wrap > a > div.link__feelings.fact__feelings > div").first().childNode(0)).substring(1));

                    docDrozdy = Jsoup.connect("https://yandex.by/pogoda/minsk?lat=53.95676295721988&lon=27.445825327087764").get();
                    textView_Air_Temperature_Drozdy.setText(String.valueOf(docDrozdy.select("body > div > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.fact_size_m.fact_with-hourly-prognosis > div.fact__temp-wrap > a > div.temp.fact__temp.fact__temp_size_s > span.temp__value").first().childNode(0)));
                    textView_Wind_Speed_Drozdy.setText(String.valueOf(docDrozdy.select("body > div > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.fact_size_m.fact_with-hourly-prognosis > div.fact__props.fact__props_position_middle > dl.term.term_orient_v.fact__wind-speed > dd > span.wind-speed").first().childNode(0)));
                    textView_Weather_Drozdy.setText(String.valueOf(docDrozdy.select("body > div > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.fact_size_m.fact_with-hourly-prognosis > div.fact__temp-wrap > a > div.link__feelings.fact__feelings > div").first().childNode(0)).substring(1));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            relativeLayoutProgressBar.setVisibility(View.GONE);
                         //   navigationView.setVisibility(View.VISIBLE);
                          //  appBarLayout.setVisibility(View.VISIBLE);
                            constraintLayout.setVisibility(View.VISIBLE);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        newThread.start();
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