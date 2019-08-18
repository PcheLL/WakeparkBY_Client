package com.wakeparkby.Fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wakeparkby.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import zh.wang.android.yweathergetter4a.YahooWeather;

public class FragmentWeather extends Fragment {
    TextView textView_Air_Temperature_Logoysk;
    TextView textView_Wind_Speed_Logoysk;
    TextView textView_Weather_Logoysk;
    TextView textViewNameLogoysk;
    TextView textViewNameDrozdy;
    TextView textView_Air_Temperature_Drozdy;
    TextView textView_Wind_Speed_Drozdy;
    TextView textView_Weather_Drozdy;
    LinearLayout linearLayoutWeather;
    RelativeLayout relativeLayoutProgressBarWeather;
    private YahooWeather mYahooWeather = YahooWeather.getInstance(5000, true);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_weather, container, false);
        // SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        // if (!preferences.getBoolean("onboarding_complete", false)) {
           /* Intent onboarding = new Intent(getActivity(), OnboardingActivity.class);
            startActivity(onboarding);
            rootView.finish();
            return rootView;*/
        // } else
        //    {
        linearLayoutWeather = rootView.findViewById(R.id.linearLayoutWeather);
        relativeLayoutProgressBarWeather = rootView.findViewById(R.id.relativeLayoutProgressBarWeather);
        textView_Air_Temperature_Logoysk = rootView.findViewById(R.id.textView_Air_Temperature_Logoysk);
        textView_Wind_Speed_Logoysk = rootView.findViewById(R.id.textView_Wind_Speed_Logoysk);
        textView_Weather_Logoysk = rootView.findViewById(R.id.textView_Weather_Logoysk);
        textView_Air_Temperature_Drozdy = rootView.findViewById(R.id.textView_Air_Temperature_Drozdy);
        textView_Wind_Speed_Drozdy = rootView.findViewById(R.id.textView_Wind_Speed_Drozdy);
        textView_Weather_Drozdy = rootView.findViewById(R.id.textView_Weather_Drozdy);
        textViewNameLogoysk = rootView.findViewById(R.id.textViewNameLogoysk);
        textViewNameDrozdy = rootView.findViewById(R.id.textViewNameDrozdy);
        //appBarLayout.setVisibility(View.GONE);
        linearLayoutWeather.setVisibility(View.GONE);
        relativeLayoutProgressBarWeather.setVisibility(View.VISIBLE);
        refreshWeather();
        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle("Погода");
        return rootView;
    }

    public FragmentWeather() {
    }

    public static FragmentWeather newInstance() {
        return new FragmentWeather();
    }

    private void refreshWeather() {

        Thread newThread = new Thread() {

            @RequiresApi(api = Build.VERSION_CODES.N)

            public void run() {

                Document docLogoysk;

                Document docDrozdy;

                try {

                    docLogoysk = Jsoup.connect("https://yandex.by/pogoda/minsk?lat=54.18134156547551&lon=27.810362236397864").get();

                    textView_Air_Temperature_Logoysk.setText(String.valueOf(docLogoysk.select("body > div.b-page__container > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.card.card_size_big > div.fact__temp-wrap > a > div.temp.fact__temp.fact__temp_size_s > span.temp__value").first().childNode(0)));

                    textView_Wind_Speed_Logoysk.setText(String.valueOf(docLogoysk.select("body > div.b-page__container > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.card.card_size_big > div.fact__props.fact__props_position_middle > dl.term.term_orient_v.fact__wind-speed > dd > span.wind-speed").first().childNode(0)));

                    textView_Weather_Logoysk.setText(String.valueOf(docLogoysk.select("body > div.b-page__container > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.card.card_size_big > div.fact__temp-wrap > a > div.link__feelings.fact__feelings > div").first().childNode(0)).substring(1));


                    docDrozdy = Jsoup.connect("https://yandex.by/pogoda/minsk?lat=53.95676295721988&lon=27.445825327087764").get();

                    textView_Air_Temperature_Drozdy.setText(String.valueOf(docDrozdy.select("body > div.b-page__container > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.card.card_size_big > div.fact__temp-wrap > a > div.temp.fact__temp.fact__temp_size_s > span.temp__value").first().childNode(0)));

                    textView_Wind_Speed_Drozdy.setText(String.valueOf(docDrozdy.select("body > div.b-page__container > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.card.card_size_big > div.fact__props.fact__props_position_middle > dl.term.term_orient_v.fact__wind-speed > dd > span.wind-speed").first().childNode(0)));

                    textView_Weather_Drozdy.setText(String.valueOf(docDrozdy.select("body > div.b-page__container > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.card.card_size_big > div.fact__temp-wrap > a > div.link__feelings.fact__feelings > div").first().childNode(0)).substring(1));

                    getActivity().runOnUiThread(new Runnable() {

                        @Override

                        public void run() {

                            relativeLayoutProgressBarWeather.setVisibility(View.GONE);

                            linearLayoutWeather.setVisibility(View.VISIBLE);

                        }

                    });

                } catch (IOException e) {

                    textView_Air_Temperature_Logoysk.setText("-");

                    textView_Wind_Speed_Logoysk.setText("-");

                    textView_Weather_Logoysk.setText("-");

                    textView_Air_Temperature_Drozdy.setText("-");

                    textView_Wind_Speed_Drozdy.setText("-");

                    textView_Weather_Drozdy.setText("-");

                }

            }

        };

        newThread.start();

    }

}
