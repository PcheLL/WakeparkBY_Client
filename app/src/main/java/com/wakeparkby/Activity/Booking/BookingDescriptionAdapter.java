package com.wakeparkby.Activity.Booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wakeparkby.HTTPController.Booking;
import com.wakeparkby.R;

import java.util.List;

public class BookingDescriptionAdapter extends RecyclerView.Adapter<BookingDescriptionAdapter.NewsViewHolder> {

    private Context mContext;
    private List<Booking> bookingList;

    public BookingDescriptionAdapter(Context context, List<Booking> bookingList) {
        this.mContext = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_booking_info, parent, false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        //holder.relativeLayoutBookingDescriptionItem.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));
        holder.bookingDescriptionStartHours.setText(bookingList.get(position).getStartHours());
        holder.bookingDescriptionStartMinutes.setText(bookingList.get(position).getStartMinutes());
        holder.bookingDescriptionEndHours.setText(bookingList.get(position).getEndHours());
        holder.bookingDescriptionEndMinutes.setText(bookingList.get(position).getEndMinutes());

    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView bookingDescriptionStartHours;
        TextView bookingDescriptionStartMinutes;
        TextView bookingDescriptionEndHours;
        TextView bookingDescriptionEndMinutes;
        RelativeLayout relativeLayoutBookingDescriptionItem;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayoutBookingDescriptionItem = itemView.findViewById(R.id.relativeLayoutBookingDescriptionItem);
            bookingDescriptionStartHours = itemView.findViewById(R.id.bookingDescriptionStartHours);
            bookingDescriptionStartMinutes = itemView.findViewById(R.id.bookingDescriptionStartMinutes);
            bookingDescriptionEndHours = itemView.findViewById(R.id.bookingDescriptionEndHours);
            bookingDescriptionEndMinutes = itemView.findViewById(R.id.bookingDescriptionEndMinutes);
        }
    }
}
