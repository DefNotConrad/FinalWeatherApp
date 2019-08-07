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
public class ListAdapter extends ArrayAdapter<Weather>{

    public ListAdapter(@NonNull Context context, ArrayList<Weather> weatherArrayList) {
        super(context, 0, weatherArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weather weather = getItem(position);
        int key = weather.getKey();
        
        String current = "Temp: ";
        String degree = "°";
        String currentText = current + weather.getTemp() + degree;

        /**
         * Adds hourly values to listView
         */
        if(weather.getKey()==1) {
            for (int x = 0; x < 12; x++) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_current_hourly, parent, false);
                TextView dateTextView = convertView.findViewById(R.id.hourly_title);
                //ImageView iconView = convertView.findViewById(R.id.item_icon);
                TextView tempView = convertView.findViewById(R.id.current_temp);
                TextView weatherText = convertView.findViewById(R.id.weather_text);
                tempView.setText(currentText);
                weatherText.setText(weather.getIconPhrase());
                dateTextView.setText(weather.getDate());
                //iconView.setImageDrawable();
            }
        }
        return convertView;
    }
}
