package com.wakeparkby.Activity.History;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.wakeparkby.HTTPController.History;
import com.wakeparkby.R;

import java.util.ArrayList;

public class AdapterHistoryArray extends ArrayAdapter<History> {
    private Context context;
    private ArrayList<History> historyArrayList = new ArrayList<>();

    public AdapterHistoryArray(@NonNull Context context, int resource, ArrayList<History> profileArrayList) {
        super(context, resource, profileArrayList);
        this.context = context;
        this.historyArrayList = profileArrayList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        History history = historyArrayList.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.property_layout, null);
        TextView data = (TextView) view.findViewById(R.id.textViewDateHistory);
        TextView time = (TextView) view.findViewById(R.id.textViewTimeHistory);
        TextView locationName = (TextView) view.findViewById(R.id.textViewLocationHistory);
        TextView reverseNumber = (TextView) view.findViewById(R.id.textViewReversNumberHistory);
        ImageView image = (ImageView) view.findViewById(R.id.iconStatus);
        data.setText(String.valueOf(history.getDate()));

        int start = Integer.valueOf(history.getStartTime());
        int end = Integer.valueOf(history.getEndTime());
        int startHours = start / 60;
        int startMinutes = start - startHours * 60;
        int endHours = end / 60;
        int endMinutes = end - endHours * 60;
        String historyLocationName = null;
        String historyTime = null;

        if (startMinutes == 0) {
            if (endMinutes == 0) {
                historyTime = String.valueOf(startHours + ":" + startMinutes + "0 - " + endHours + ":" + endMinutes + "0");
            } else if (endMinutes < 10 ){
                historyTime = String.valueOf(startHours + ":" + startMinutes + "0 - " + endHours + ":0" + endMinutes);
            }

            else {
                historyTime = String.valueOf(startHours + ":" + startMinutes + "0 - " + endHours + ":" + endMinutes);
            }
        } else if (endMinutes == 0) {
            if (startMinutes < 10) {
                historyTime = String.valueOf(startHours + ":0" + startMinutes + " - " + endHours + ":0" + endMinutes);
            } else {
                historyTime = String.valueOf(startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes + "0");
            }
        } else if (startMinutes < 10) {
            if (endMinutes < 10) {
                historyTime = String.valueOf(startHours + ":0" + startMinutes + " - " + endHours + ":0" + endMinutes);
            } else {
                historyTime = String.valueOf(startHours + ":0" + startMinutes + " - " + endHours + ":" + endMinutes);
            }
        } else if (endMinutes < 10 ) {
            historyTime = String.valueOf(startHours + ":" + startMinutes + " - " + endHours + ":0" + endMinutes);
        } else {
            historyTime = startHours + ":" + startMinutes + " - " + endHours + ":" + endMinutes;
        }
        time.setText(historyTime);

        if ((String.valueOf(history.getLocation()).equals("LOGOISK"))){
            historyLocationName = "Логойск";
        } else if ((String.valueOf(history.getLocation()).equals("DROZDI"))){
            historyLocationName = "Дрозды";
        }
        locationName.setText(historyLocationName);

        reverseNumber.setText(String.valueOf(history.getReversNumber()));
        if (String.valueOf(history.getStatus()).equals("BOOKED")) {
            int imageID = context.getResources().getIdentifier("ic_booked", "drawable", context.getPackageName());
            image.setImageResource(imageID);
        } else if (String.valueOf(history.getStatus()).equals("VISITED")) {
            int imageID = context.getResources().getIdentifier("ic_visited", "drawable", context.getPackageName());
            image.setImageResource(imageID);
        } else if (String.valueOf(history.getStatus()).equals("MISSED")) {
            int imageID = context.getResources().getIdentifier("ic_missed", "drawable", context.getPackageName());
            image.setImageResource(imageID);
        }
        return view;
    }
}
