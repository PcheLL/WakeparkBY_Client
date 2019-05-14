package com.wakeparkby.Activity.History;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.wakeparkby.R;

import java.util.ArrayList;

public class AdapterHistoryArray extends ArrayAdapter<History> {
private Context context;
private ArrayList<History> historyArrayList= new ArrayList<>();

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

        //set price and rental attributes
        data.setText(String.valueOf(history.getData()));
        time.setText(String.valueOf(history.getTime()));
        locationName.setText(String.valueOf(history.getLocationName()));
        reverseNumber.setText(String.valueOf(history.getReverseNumber()));

        //get the image associated with this property
        int imageID = context.getResources().getIdentifier(history.getImage(), "drawable", context.getPackageName());
        image.setImageResource(imageID);

        return view;
    }
}
