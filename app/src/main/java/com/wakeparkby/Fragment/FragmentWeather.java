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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wakeparkby.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class FragmentWeather extends Fragment {
    private TextView textView_Air_Temperature_Logoysk;
    private TextView textView_Wind_Speed_Logoysk;
    private ImageView image_weather_Logoysk;
    private TextView textViewNameLogoysk;
    private TextView textViewNameDrozdy;
    private TextView textView_Air_Temperature_Drozdy;
    private TextView textView_Wind_Speed_Drozdy;
    private ImageView image_weather_Drozdy;
    private LinearLayout linearLayoutWeather;
    private RelativeLayout relativeLayoutProgressBarWeather;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_weather, container, false);
        linearLayoutWeather = rootView.findViewById(R.id.linearLayoutWeather);
        relativeLayoutProgressBarWeather = rootView.findViewById(R.id.relativeLayoutProgressBarWeather);
        textView_Air_Temperature_Logoysk = rootView.findViewById(R.id.textView_Air_Temperature_Logoysk);
        textView_Wind_Speed_Logoysk = rootView.findViewById(R.id.textView_Wind_Speed_Logoysk);
        image_weather_Logoysk = rootView.findViewById(R.id.image_logoysk);
        textView_Air_Temperature_Drozdy = rootView.findViewById(R.id.textView_Air_Temperature_Drozdy);
        textView_Wind_Speed_Drozdy = rootView.findViewById(R.id.textView_Wind_Speed_Drozdy);
        image_weather_Drozdy = rootView.findViewById(R.id.image_drozdy);
        textViewNameLogoysk = rootView.findViewById(R.id.textViewNameLogoysk);
        textViewNameDrozdy = rootView.findViewById(R.id.textViewNameDrozdy);
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

                    String weather = String.valueOf(docLogoysk.select("body > div.b-page__container > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.card.card_size_big > div.fact__temp-wrap > a > div.link__feelings.fact__feelings > div").first().childNode(0)).substring(1);

                    if (weather.equals("Ясно")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_clear));
                    }
                    else if (weather.equals("Облачно с прояснениями")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_cloudy));
                    }
                    else if (weather.equals("Небольшой снег")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_cloudy_and_light_snow));
                    }
                    else if (weather.equals("Пасмурно")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_overcast));
                    }
                    else if (weather.equals("Сильный дождь")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_overcast_and_rain));
                    }
                    else if (weather.equals("Снегопад")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_overcast_and_snow));
                    }
                    else if (weather.equals("Дождь со снегом")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_overcast_and_wet_snow));
                    }
                    else if (weather.equals("Сильный дождь, гроза")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_overcast_thunderstorm_with_rain));
                    }
                    else if (weather.equals("Малооблачно")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_partly_cloudy));
                    }
                    else if (weather.equals("Небольшой дождь")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_partly_cloudy_and_light_rain));
                    }
                    else if (weather.equals("Дождь")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_partly_cloudy_and_rain));
                    }
                    else if (weather.equals("Снег")){
                        image_weather_Logoysk.setImageDrawable(getResources().getDrawable(R.mipmap.ic_partly_cloudy_and_snow));
                    }

                    docDrozdy = Jsoup.connect("https://yandex.by/pogoda/minsk?lat=53.95676295721988&lon=27.445825327087764").get();

                    textView_Air_Temperature_Drozdy.setText(String.valueOf(docDrozdy.select("body > div.b-page__container > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.card.card_size_big > div.fact__temp-wrap > a > div.temp.fact__temp.fact__temp_size_s > span.temp__value").first().childNode(0)));

                    textView_Wind_Speed_Drozdy.setText(String.valueOf(docDrozdy.select("body > div.b-page__container > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.card.card_size_big > div.fact__props.fact__props_position_middle > dl.term.term_orient_v.fact__wind-speed > dd > span.wind-speed").first().childNode(0)));

                    weather = String.valueOf(docDrozdy.select("body > div.b-page__container > div.content.content_compressed > div.content__top > div.content__main > div.content__row > div.fact.card.card_size_big > div.fact__temp-wrap > a > div.link__feelings.fact__feelings > div").first().childNode(0)).substring(1);

                    if (weather.equals("Ясно")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_clear));
                    }
                    else if (weather.equals("Облачно с прояснениями")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_cloudy));
                    }
                    else if (weather.equals("Небольшой снег")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_cloudy_and_light_snow));
                    }
                    else if (weather.equals("Пасмурно")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_overcast));
                    }
                    else if (weather.equals("Сильный дождь")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_overcast_and_rain));
                    }
                    else if (weather.equals("Снегопад")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_overcast_and_snow));
                    }
                    else if (weather.equals("Дождь со снегом")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_overcast_and_wet_snow));
                    }
                    else if (weather.equals("Сильный дождь, гроза")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_overcast_thunderstorm_with_rain));
                    }
                    else if (weather.equals("Малооблачно")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_partly_cloudy));
                    }
                    else if (weather.equals("Небольшой дождь")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_partly_cloudy_and_light_rain));
                    }
                    else if (weather.equals("Дождь")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_partly_cloudy_and_rain));
                    }
                    else if (weather.equals("Снег")){
                        image_weather_Drozdy.setImageDrawable(getResources().getDrawable(R.mipmap.ic_partly_cloudy_and_snow));
                    }

                    try {
                        getActivity().runOnUiThread(new Runnable() {

                            @Override

                            public void run() {

                                relativeLayoutProgressBarWeather.setVisibility(View.GONE);

                                linearLayoutWeather.setVisibility(View.VISIBLE);

                            }

                        });
                    } catch (NullPointerException exNPE) {

                    }


                } catch (IOException e) {

                    textView_Air_Temperature_Logoysk.setText("-");

                    textView_Wind_Speed_Logoysk.setText("-");


                    textView_Air_Temperature_Drozdy.setText("-");

                    textView_Wind_Speed_Drozdy.setText("-");



                }

            }

        };

        newThread.start();

    }

}
