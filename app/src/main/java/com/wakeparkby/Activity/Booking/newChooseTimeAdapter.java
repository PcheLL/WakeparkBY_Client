package com.wakeparkby.Activity.Booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wakeparkby.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class newChooseTimeAdapter extends RecyclerView.Adapter<newChooseTimeAdapter.NewsViewHolder> {

    Context mContext;
    List<newChooseTimeItem> mData;
    int positionDataSize = 0;

    public newChooseTimeAdapter(Context mContext, List<newChooseTimeItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        if (mData.get(positionDataSize).getStatus().equals("FREE")){
            layout = LayoutInflater.from(mContext).inflate(R.layout.item_choose_time_card_free,parent,false);
        }
        else {
            layout = LayoutInflater.from(mContext).inflate(R.layout.item_choose_time_card_waiting_booked,parent,false);
        }
        positionDataSize ++;
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.relativeLayoutCardView.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

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

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_startHours;
        TextView tv_startMinutes;
        TextView tv_endHours;
        TextView tv_endMinutes;
        RelativeLayout relativeLayoutCardView;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            relativeLayoutCardView = itemView.findViewById(R.id.relativeLayoutCardView);
            tv_startHours = itemView.findViewById(R.id.chooseTimeStartHours);
            tv_startMinutes = itemView.findViewById(R.id.chooseTimeStartMinutes);
            tv_endHours = itemView.findViewById(R.id.chooseTimeEndHours);
            tv_endMinutes = itemView.findViewById(R.id.chooseTimeEndMinutes);
        }

        @Override
        public void onClick(View v) {
            int pos = getLayoutPosition();
            String time = mData.get(pos).getStartHours() + "." + mData.get(pos).getStartMinutes() + " : " + mData.get(pos).getEndHours() + "." + mData.get(pos).getEndMinutes();
            System.out.print(time);
        }
    }
}
