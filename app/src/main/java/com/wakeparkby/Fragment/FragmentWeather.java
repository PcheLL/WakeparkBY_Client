package com.wakeparkby.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.R;

import zh.wang.android.yweathergetter4a.WeatherInfo;
import zh.wang.android.yweathergetter4a.YahooWeather;
import zh.wang.android.yweathergetter4a.YahooWeatherInfoListener;

public class FragmentWeather extends Fragment implements View.OnClickListener, YahooWeatherInfoListener {
    TextView textView_Air_Temperature_Logoysk;
    TextView textView_Wind_Speed_Logoysk;
    TextView textView_Weather_Logoysk;
    TextView textViewNameLogoysk;
    TextView textViewNameDrozdy;
    TextView textView_Air_Temperature_Drozdy;
    TextView textView_Wind_Speed_Drozdy;
    TextView textView_Weather_Drozdy;
    LinearLayout linearLayoutWeather;
    RelativeLayout relativeLayoutProgressBarMainMenu;
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
        relativeLayoutProgressBarMainMenu = rootView.findViewById(R.id.relativeLayoutProgressBarMainMenu);
        textView_Air_Temperature_Logoysk = rootView.findViewById(R.id.textView_Air_Temperature_Logoysk);
        textView_Wind_Speed_Logoysk = rootView.findViewById(R.id.textView_Wind_Speed_Logoysk);
        textView_Weather_Logoysk = rootView.findViewById(R.id.textView_Weather_Logoysk);
        textView_Air_Temperature_Drozdy = rootView.findViewById(R.id.textView_Air_Temperature_Drozdy);
        textView_Wind_Speed_Drozdy = rootView.findViewById(R.id.textView_Wind_Speed_Drozdy);
        textView_Weather_Drozdy = rootView.findViewById(R.id.textView_Weather_Drozdy);
        textViewNameLogoysk = rootView.findViewById(R.id.textViewNameLogoysk);
        textViewNameDrozdy = rootView.findViewById(R.id.textViewNameDrozdy);
        textViewNameLogoysk.setOnClickListener(this);
        textViewNameDrozdy.setOnClickListener(this);
        //appBarLayout.setVisibility(View.GONE);
        linearLayoutWeather.setVisibility(View.GONE);
        relativeLayoutProgressBarMainMenu.setVisibility(View.VISIBLE);
        // refreshWeather();
        searchByPlaceName();
        return rootView;
    }

    public FragmentWeather() {
    }

    public static FragmentWeather newInstance() {
        return new FragmentWeather();
    }


    @Override
    public void gotWeatherInfo(WeatherInfo weatherInfo, YahooWeather.ErrorType errorType) {
        if (weatherInfo != null) {
            // Add your code here
            // weatherInfo object contains all information returned by Yahoo Weather API
            // if `weatherInfo` is null, you can get the error from `errorType`
            System.out.println("date: " + weatherInfo.getCurrentConditionDate());
            System.out.println("weather: " + weatherInfo.getCurrentText());
            System.out.println("temperature in ºC: " + weatherInfo.getCurrentTemp());
            System.out.println("wind chill: " + weatherInfo.getWindChill());
            System.out.println("wind direction: " + weatherInfo.getWindDirection());
            System.out.println("wind speed: " + weatherInfo.getWindSpeed());
            System.out.println("Humidity: " + weatherInfo.getAtmosphereHumidity());
            System.out.println("Pressure: " + weatherInfo.getAtmospherePressure());
            System.out.println("Visibility: " + weatherInfo.getAtmosphereVisibility());
            System.out.println("");
        }
    }


    private void searchByPlaceName() {
        mYahooWeather.setNeedDownloadIcons(true);
        mYahooWeather.setUnit(YahooWeather.UNIT.CELSIUS);

        //mYahooWeather.queryYahooWeatherByLatLon(getContext(), 53.907973, 27.573521, FragmentWeather.this);
        String location = "Tokyo Japan";
        mYahooWeather.queryYahooWeatherByPlaceName(getContext(), location, FragmentWeather.this);
    }

    @Override
    public void onClick(View v) {
       /* Intent intent_Date = new Intent(getActivity(), DateSelectionActivity.class);
        switch (v.getId()) {

            case R.id.textViewNameLogoysk:

                intent_Date.putExtra("place", "LOGOISK");

                BookingController.start(getContext(), intent_Date);

                break;

            case R.id.textViewNameDrozdy:

                intent_Date.putExtra("place", "DROZDI");

                BookingController.start(getContext(), intent_Date);

                break;

        }*/

    }
}
