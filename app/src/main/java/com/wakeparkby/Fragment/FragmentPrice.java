package com.wakeparkby.Fragment;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wakeparkby.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FragmentPrice extends Fragment implements View.OnClickListener {
    private Button buttСhildren;
    private Button buttAdults;
    private Button buttRent;
    private LinearLayout layoutСhildren;
    private LinearLayout layoutAdults;
    private LinearLayout layoutRent;
    RelativeLayout relativeLayoutProgressBarPrice;
    private TextView textView23;
    private TextView textView33;
    private TextView textView43;
    private TextView textView53;
    private TextView textView63;
    private TextView textView73;
    private TextView textView83;
    private TextView textView93;
    private TextView textView24;
    private TextView textView34;
    private TextView textView44;
    private TextView textView54;
    private TextView textView64;
    private TextView textView74;
    private TextView textView84;
    private TextView textView94;
    //-------------------------Взрослые-----------------------
    private TextView textView_23;
    private TextView textView_33;
    private TextView textView_43;
    private TextView textView_53;
    private TextView textView_63;
    private TextView textView_73;
    private TextView textView_83;
    private TextView textView_93;
    //--------------------------Аренда------------------------
    private TextView textView__22;
    private TextView textView__23;
    private TextView textView__24;
    private TextView textView__32;
    private TextView textView__33;
    private TextView textView__34;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_price, container, false);
        relativeLayoutProgressBarPrice = rootView.findViewById(R.id.relativeLayoutProgressBarPrice);
        buttСhildren = (Button) rootView.findViewById(R.id.buttonСhildren);
        buttСhildren.setOnClickListener((View.OnClickListener) this);
        buttAdults = (Button) rootView.findViewById(R.id.buttonAdults);
        buttAdults.setOnClickListener((View.OnClickListener) this);
        buttRent = (Button) rootView.findViewById(R.id.buttonRent);
        buttRent.setOnClickListener((View.OnClickListener) this);
        layoutСhildren = (LinearLayout) rootView.findViewById(R.id.layoutСhildren);
        layoutAdults = (LinearLayout) rootView.findViewById(R.id.layoutAdults);
        layoutRent = (LinearLayout) rootView.findViewById(R.id.layoutRent);
        textView23 = (TextView) rootView.findViewById(R.id.textView23);
        textView33 = (TextView) rootView.findViewById(R.id.textView33);
        textView43 = (TextView) rootView.findViewById(R.id.textView43);
        textView53 = (TextView) rootView.findViewById(R.id.textView53);
        textView63 = (TextView) rootView.findViewById(R.id.textView63);
        textView73 = (TextView) rootView.findViewById(R.id.textView73);
        textView83 = (TextView) rootView.findViewById(R.id.textView83);
        textView93 = (TextView) rootView.findViewById(R.id.textView93);
        textView24 = (TextView) rootView.findViewById(R.id.textView24);
        textView34 = (TextView) rootView.findViewById(R.id.textView34);
        textView44 = (TextView) rootView.findViewById(R.id.textView44);
        textView54 = (TextView) rootView.findViewById(R.id.textView54);
        textView64 = (TextView) rootView.findViewById(R.id.textView64);
        textView74 = (TextView) rootView.findViewById(R.id.textView74);
        textView84 = (TextView) rootView.findViewById(R.id.textView84);
        textView94 = (TextView) rootView.findViewById(R.id.textView94);
        textView_23 = (TextView) rootView.findViewById(R.id.textView_23);
        textView_33 = (TextView) rootView.findViewById(R.id.textView_33);
        textView_43 = (TextView) rootView.findViewById(R.id.textView_43);
        textView_53 = (TextView) rootView.findViewById(R.id.textView_53);
        textView_63 = (TextView) rootView.findViewById(R.id.textView_63);
        textView_73 = (TextView) rootView.findViewById(R.id.textView_73);
        textView_83 = (TextView) rootView.findViewById(R.id.textView_83);
        textView_93 = (TextView) rootView.findViewById(R.id.textView_93);
        textView__22 = (TextView) rootView.findViewById(R.id.textView__22);
        textView__23 = (TextView) rootView.findViewById(R.id.textView__23);
        textView__24 = (TextView) rootView.findViewById(R.id.textView__24);
        textView__32 = (TextView) rootView.findViewById(R.id.textView__32);
        textView__33 = (TextView) rootView.findViewById(R.id.textView__33);
        textView__34 = (TextView) rootView.findViewById(R.id.textView__34);
        refreshPriceList();
        return rootView;
    }

    public FragmentPrice() {
    }

    public static FragmentPrice newInstance() {
        return new FragmentPrice();
    }

    public void refreshPriceList() {
        final List<String> list = new ArrayList<>();
        Thread newThread = new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void run() {
                Document doc;
                try {
                    relativeLayoutProgressBarPrice.setVisibility(View.VISIBLE);
                    doc = Jsoup.connect("http://wakepark.by/prices").get();
                    textView23.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(1) > td:nth-child(3)").first().childNode(0)));
                    textView33.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2)").first().childNode(0)));
                    textView43.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(3) > td:nth-child(2)").first().childNode(0)));
                    textView53.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(4) > td:nth-child(2)").first().childNode(0)));
                    textView63.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(5) > td:nth-child(3)").first().childNode(0)));
                    textView73.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(6) > td:nth-child(2)").first().childNode(0)));
                    textView83.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(7) > td:nth-child(2)").first().childNode(0)));
                    textView93.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(8) > td:nth-child(2)").first().childNode(0)));
                    textView24.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(1) > td:nth-child(4)").first().childNode(0)));
                    textView34.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(3)").first().childNode(0)));
                    textView44.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(3) > td:nth-child(3)").first().childNode(0)));
                    textView54.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(4) > td:nth-child(3)").first().childNode(0)));
                    textView64.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(5) > td:nth-child(4)").first().childNode(0)));
                    textView74.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(6) > td:nth-child(3)").first().childNode(0)));
                    textView84.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(7) > td:nth-child(3)").first().childNode(0)));
                    textView94.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(8) > td:nth-child(3)").first().childNode(0)));
                    textView_23.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(1) > td:nth-child(5)").first().childNode(0)));
                    textView_33.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(4)").first().childNode(0)));
                    textView_43.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(3) > td:nth-child(4)").first().childNode(0)));
                    textView_53.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(4) > td:nth-child(4)").first().childNode(0)));
                    textView_63.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(5) > td:nth-child(5)").first().childNode(0)));
                    textView_73.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(6) > td:nth-child(4)").first().childNode(0)));
                    textView_83.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(7) > td:nth-child(4)").first().childNode(0)));
                    textView_93.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(1) > table > tbody > tr:nth-child(8) > td:nth-child(4)").first().childNode(0)));
                    textView__22.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(4) > table > tbody > tr:nth-child(1) > td:nth-child(2)").first().childNode(0)));
                    textView__23.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(4) > table > tbody > tr:nth-child(1) > td:nth-child(3)").first().childNode(0)));
                    textView__24.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(4) > table > tbody > tr:nth-child(1) > td:nth-child(4)").first().childNode(0)));
                    textView__32.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(4) > table > tbody > tr:nth-child(2) > td:nth-child(2)").first().childNode(0)));
                    textView__33.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(4) > table > tbody > tr:nth-child(2) > td:nth-child(3)").first().childNode(0)));
                    textView__34.setText(String.valueOf(doc.select("#drozdy-wake > div:nth-child(4) > table > tbody > tr:nth-child(2) > td:nth-child(4)").first().childNode(0)));

                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                relativeLayoutProgressBarPrice.setVisibility(View.GONE);
                                buttСhildren.setVisibility(View.VISIBLE);
                                buttAdults.setVisibility(View.VISIBLE);
                                buttRent.setVisibility(View.VISIBLE);
                            }
                        });
                    } catch (NullPointerException ex){
                        Toast.makeText(getActivity(), "Дождитесь загрузки !!!", Toast.LENGTH_SHORT).show();


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        newThread.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonСhildren:
                layoutСhildren.setVisibility(View.VISIBLE);
                layoutAdults.setVisibility(View.GONE);
                layoutRent.setVisibility(View.GONE);
                break;
            case R.id.buttonAdults:
                layoutСhildren.setVisibility(View.GONE);
                layoutAdults.setVisibility(View.VISIBLE);
                layoutRent.setVisibility(View.GONE);
                break;
            case R.id.buttonRent:
                layoutСhildren.setVisibility(View.GONE);
                layoutAdults.setVisibility(View.GONE);
                layoutRent.setVisibility(View.VISIBLE);
                break;
        }
    }
}

