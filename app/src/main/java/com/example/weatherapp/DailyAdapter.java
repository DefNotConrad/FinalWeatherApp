package com.example.weatherapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
public class DailyAdapter extends ArrayAdapter<Weather>{

    public DailyAdapter(@NonNull Context context, ArrayList<Weather> weatherArrayList) {
        super(context, 0, weatherArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weather weather = getItem(position);

        String high = "High: ";
        String low = "Low: ";
        String degree = "°";
        String minTemp = low + weather.getMinTemp() + degree;
        String maxTemp = high + weather.getMaxTemp() + degree;
        
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_item, parent, false);

            for (int x = 0; x < 5; x++) {
                TextView dateTextView = convertView.findViewById(R.id.item_top_text);
                //ImageView iconView = convertView.findViewById(R.id.item_icon);
                TextView minTextView = convertView.findViewById(R.id.item_low);
                TextView maxTextView = convertView.findViewById(R.id.item_high);
                TextView precipView = convertView.findViewById(R.id.item_phrase);
                dateTextView.setText(weather.getDate());
                minTextView.setText(minTemp);
                maxTextView.setText(maxTemp);
                precipView.setText(weather.getIconPhrase());
                //iconView.setImageDrawable();
            }

        return convertView;
    }
}
