package com.wakeparkby.Activity.History;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wakeparkby.Controller.HistoryController;
import com.wakeparkby.HTTPController.History;
import com.wakeparkby.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.NewsViewHolder> {
    private Context mContext;
    private List<History> historyList;

    public HistoryAdapter(Context context, List<History> historyList) {
        this.mContext = context;
        this.historyList = historyList;
    }


    @NonNull
    @Override
    public HistoryAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = null;
        switch (historyList.get(viewType).getStatus()) {
            case "BOOKED":
                layout = LayoutInflater.from(mContext).inflate(R.layout.item_history_card_booked, parent, false);
                break;
            case "BOOKED_NO_ACCEPTED":
                layout = LayoutInflater.from(mContext).inflate(R.layout.item_history_card_booked_no_accepted, parent, false);
                break;
            case "MISSED":
                layout = LayoutInflater.from(mContext).inflate(R.layout.item_history_card_missed, parent, false);
                break;
            case "MISSED_ADMIN":
                layout = LayoutInflater.from(mContext).inflate(R.layout.item_history_card_missed_admin, parent, false);
                break;
            case "VISITED":
                layout = LayoutInflater.from(mContext).inflate(R.layout.item_history_card_visited, parent, false);
                break;
        }
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.NewsViewHolder holder, int position) {

        holder.relativeLayoutCardView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));
        int startHours = historyList.get(position).getStartTime() / 60;
        holder.tv_startHours.setText(String.valueOf(startHours));
        holder.tv_startMinutes.setText(String.valueOf(historyList.get(position).getStartTime() - startHours * 60));
        int endHours = historyList.get(position).getEndTime() / 60;
        holder.tv_endHours.setText(String.valueOf(endHours));
        holder.tv_endMinutes.setText(String.valueOf(historyList.get(position).getEndTime() - endHours * 60));
        holder.tv_place.setText(historyList.get(position).getLocation());
        holder.tv_date.setText(historyList.get(position).getBookingDate());
        holder.tv_revers_number.setText(String.valueOf(historyList.get(position).getReversNumber()));

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_startHours;
        TextView tv_startMinutes;
        TextView tv_endHours;
        TextView tv_endMinutes;
        TextView tv_place;
        TextView tv_date;
        TextView tv_revers_number;
        RelativeLayout relativeLayoutCardView;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            relativeLayoutCardView = itemView.findViewById(R.id.relativeLayoutCardViewHistory);
            tv_startHours = itemView.findViewById(R.id.textViewHistoryStartHours);
            tv_startMinutes = itemView.findViewById(R.id.textViewHistoryStartMinutes);
            tv_endHours = itemView.findViewById(R.id.textViewHistoryEndHours);
            tv_endMinutes = itemView.findViewById(R.id.textViewHistoryEndMinutes);
            tv_date = itemView.findViewById(R.id.textViewHistoryDate);
            tv_place = itemView.findViewById(R.id.textViewHistoryPlace);
            tv_revers_number = itemView.findViewById(R.id.textViewHistoryReversNumber);
        }

        @Override
        public void onClick(View v) {
            int pos = getLayoutPosition();
            String status = historyList.get(pos).getStatus();
            if (status.equals("BOOKED") || status.equals("BOOKED_NO_ACCEPTED")) {
                String data = historyList.get(pos).getBookingDate();
                String time = historyList.get(pos).getTime();
                long yourmilliseconds = System.currentTimeMillis();
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date resultDate = new Date(yourmilliseconds);
                String dataNow = dataFormat.format(resultDate);
                if (dataNow.equals(data)) {
                    long milliseconds = System.currentTimeMillis();
                    SimpleDateFormat timeHoursFormat = new SimpleDateFormat("HH");
                    SimpleDateFormat timeMinutesFormat = new SimpleDateFormat("mm");
                    Date resultTime = new Date(milliseconds);
                    String hoursNow = timeHoursFormat.format(resultTime);
                    String minutesNow = timeMinutesFormat.format(resultTime);
                    int timeNow = Integer.valueOf(hoursNow) * 60 + Integer.valueOf(minutesNow);
                    if (timeNow > Integer.valueOf(historyList.get(pos).getStartTime()) - 120) {
                        Toast.makeText(mContext, "Отмена невозможна" + System.lineSeparator() + "Осталось меньше 2-x часов", Toast.LENGTH_LONG).show();
                    } else {
                        String idHistory = String.valueOf(pos);
                        String location = historyList.get(pos).getLocation();
                        int reversNumber = historyList.get(pos).getReversNumber();
                        System.out.print(idHistory);
                        createTwoButtonsAlertDialog("Отмена бронирования", "Отменить броинрование ?" + System.lineSeparator() + System.lineSeparator() + "Место: " + location + " ( " +
                                reversNumber + " реверс )" + System.lineSeparator() + "Дата: " + data + System.lineSeparator() + "Время: " + time, idHistory);
                    }
                } else {
                    String idHistory = historyList.get(pos).getId();
                    String location = historyList.get(pos).getLocation();
                    int reversNumber = historyList.get(pos).getReversNumber();
                    System.out.print(idHistory);
                    createTwoButtonsAlertDialog("Отмена бронирования", "Отменить броинрование ?" + System.lineSeparator() + System.lineSeparator() + "Место: " + location + " ( " +
                            reversNumber + " реверс )" + System.lineSeparator() + "Дата: " + data + System.lineSeparator() + "Время: " + time, idHistory);
                }

            } else if (status.equals("MISSED")) {
                Toast.makeText(mContext, "Вы уже отменили вашу бронь", Toast.LENGTH_LONG).show();
            } else if (status.equals("MISSED_ADMIN")) {
                Toast.makeText(mContext, "Ваша бронь была отменена администратором", Toast.LENGTH_LONG).show();
            } else if (status.equals("VISITED")) {
                Toast.makeText(mContext, "Вы уже посетили вейкпарк", Toast.LENGTH_LONG).show();
            }
        }

        private void createTwoButtonsAlertDialog(String title, String content, String idHistory) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle(title);
            builder.setMessage(content);
            builder.setNegativeButton("Вернуться",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            dialog.dismiss();
                        }
                    });
            builder.setPositiveButton("Отменить",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            HistoryController historyController = new HistoryController(idHistory);
                            Toast.makeText(mContext, "Вы отменили бронирование", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
    }
}
