package com.wakeparkby.Activity.Booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.wakeparkby.Controller.BookingController;
import com.wakeparkby.Fragment.FragmentChooseTime;
import com.wakeparkby.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ChooseTimeAdapter extends RecyclerView.Adapter<ChooseTimeAdapter.NewsViewHolder> {

    private Context mContext;
    private List<ChooseTimeItem> mData;
    private String place;
    private String date;
    private int reverseCableNumber;
    private static int fl = 0;

    public ChooseTimeAdapter(Context mContext, List<ChooseTimeItem> mData, String place, String date, int reverseCableNumber) {
        this.mContext = mContext;
        this.mData = mData;
        this.place = place;
        this.date = date;
        this.reverseCableNumber = reverseCableNumber;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        fl = 0;
        View layout = null;
        String bookingStatus = mData.get(viewType).getStatus();
        switch (bookingStatus) {
            case "FREE":
                layout = LayoutInflater.from(mContext).inflate(R.layout.item_choose_time_card_free, parent, false);
                break;
            case "MY_BOOKED_NO_ACCEPTED":
                layout = LayoutInflater.from(mContext).inflate(R.layout.item_choose_time_card_my_waiting_booked, parent, false);
                break;
            case "BOOKED_NO_ACCEPTED":
                layout = LayoutInflater.from(mContext).inflate(R.layout.item_choose_time_card_waiting_booked, parent, false);
                break;
        }
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder    (@NonNull NewsViewHolder holder, int position) {
        if (fl == 0){
            holder.relativeLayoutCardView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));
        }
        holder.tv_startHours.setText(mData.get(position).getStartHours());
        holder.tv_startMinutes.setText(mData.get(position).getStartMinutes());
        holder.tv_endHours.setText(mData.get(position).getEndHours());
        holder.tv_endMinutes.setText(mData.get(position).getEndMinutes());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static void setFl(int fl) {
        ChooseTimeAdapter.fl = fl;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_startHours;
        TextView tv_startMinutes;
        TextView tv_endHours;
        TextView tv_endMinutes;
        RelativeLayout relativeLayoutCardView;



        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            relativeLayoutCardView = itemView.findViewById(R.id.relativeLayoutCardViewBooking);
            tv_startHours = itemView.findViewById(R.id.chooseTimeStartHours);
            tv_startMinutes = itemView.findViewById(R.id.chooseTimeStartMinutes);
            tv_endHours = itemView.findViewById(R.id.chooseTimeEndHours);
            tv_endMinutes = itemView.findViewById(R.id.chooseTimeEndMinutes);
        }

        @Override
        public void onClick(View v) {
            int pos = getLayoutPosition();
            int startTime = (Integer.valueOf(mData.get(pos).getStartHours()) * 60 + Integer.valueOf(mData.get(pos).getStartMinutes()));
            int endTime = (Integer.valueOf(mData.get(pos).getEndHours()) * 60 + Integer.valueOf(mData.get(pos).getEndMinutes()));
            String status = mData.get(pos).getStatus();
            if(status.equals("MY_BOOKED_NO_ACCEPTED")){
                Thread newThread = new Thread() {
                    public void run() {
                        BookingController bookingController = new BookingController();
                        bookingController.cancelReservation(mData.get(pos).getId());
                    }
                };
                newThread.start();
            }else if(status.equals("FREE"))
            {
                Thread newThread = new Thread() {
                    public void run() {
                        BookingController bookingController = new BookingController(place, date, reverseCableNumber, startTime, endTime);

                    }
                };
                newThread.start();
            }
        }
    }
}
