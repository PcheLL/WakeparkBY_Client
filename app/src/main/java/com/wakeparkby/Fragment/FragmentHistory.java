package com.wakeparkby.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.wakeparkby.Activity.History.HistoryAdapter;
import com.wakeparkby.Controller.HistoryController;
import com.wakeparkby.HTTPController.History;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment {
    private LinearLayout linearLayoutHistory;
    private RecyclerView NewsRecyclerView;
    private HistoryAdapter historyAdapter;
    private List<History> historyList = new ArrayList<>();
    private RelativeLayout relativeLayoutProgressBarHistory;


    private Observer observer = new Observer("FragmentChooseTime") {
        @Override
        public void update() {
            int n = observer.getStatus();
            if (n == 10) {
                if (observer.getId() == 5) {
                    updateHistoryList();
                    observer.setId(0);
                } else {

                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_history, container, false);
        NewsRecyclerView = rootView.findViewById(R.id.recyclerViewHistory);
        relativeLayoutProgressBarHistory = rootView.findViewById(R.id.relativeLayoutProgressBarHistory);
        linearLayoutHistory = rootView.findViewById(R.id.linearLayoutHistory);
        HistoryController historyController = new HistoryController();
        /*if (isNetworkAvailable(getContext())) {
        } else {
            System.out.print("Нет соединения");
            // если сети нет показываем Тост или
            // кидаем на активити с красивым дизайном где просим сделать реконнект
        }*/
        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle("История");
        linearLayoutHistory.setVisibility(View.GONE);
        relativeLayoutProgressBarHistory.setVisibility(View.VISIBLE);
        return rootView;
    }

    public FragmentHistory() {
    }

    public static FragmentHistory newInstance() {
        return new FragmentHistory();
    }

    private void updateHistoryList() {
        historyList = HistoryController.getListHistory();
        historyAdapter = new HistoryAdapter(getContext(), historyList);
        NewsRecyclerView.setAdapter(historyAdapter);
        NewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        relativeLayoutProgressBarHistory.setVisibility(View.GONE);
        linearLayoutHistory.setVisibility(View.VISIBLE);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        observer.removeFromList(observer);
    }
}
