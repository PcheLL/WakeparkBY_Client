package com.wakeparkby.Activity.History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wakeparkby.HTTPController.History;
import com.wakeparkby.R;

import java.util.ArrayList;
import java.util.List;

public class newHistoryAdapter  extends RecyclerView.Adapter<newHistoryAdapter.NewsViewHolder>{
    Context mContext;
    List<History> historyList;
    int positionDataSize = 0;

    public newHistoryAdapter(Context context, List<History> historyList) {
        this.mContext = context;
        this.historyList = historyList;
    }


    @NonNull
    @Override
    public newHistoryAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // positionDataSize = 0; // -решить проблему
        View layout = null;
        switch (historyList.get(positionDataSize).getStatus()) {
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
        positionDataSize ++;
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull newHistoryAdapter.NewsViewHolder holder, int position) {

        holder.relativeLayoutCardView.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));
        int startHours = historyList.get(position).getStartTime()/60;
        holder.tv_startHours.setText(String.valueOf(startHours));
        holder.tv_startMinutes.setText(String.valueOf(historyList.get(position).getStartTime() - startHours * 60));
        int endHours = historyList.get(position).getEndTime()/60;
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
         /*   int pos = getLayoutPosition();
            int startTime = (Integer.valueOf(mData.get(pos).getStartHours()) * 60 + Integer.valueOf(mData.get(pos).getStartMinutes()));
            int endTime = (Integer.valueOf(mData.get(pos).getEndHours()) *60 + Integer.valueOf(mData.get(pos).getEndMinutes()));
            Thread newThread = new Thread() {
                public void run() {
                    BookingController bookingController = new BookingController(place,date,reverseCableNumber,startTime,endTime);

                }
            };
            newThread.start();*/
        }
    }
}
