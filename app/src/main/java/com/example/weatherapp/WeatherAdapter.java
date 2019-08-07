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
public class WeatherAdapter extends ArrayAdapter<Weather>{

    public WeatherAdapter(@NonNull Context context, ArrayList<Weather> weatherArrayList) {
        super(context, 0, weatherArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weather weather = getItem(position);
        int key = weather.getKey();

    
        String current = "Currently: ";
        String degree = "°";
        String currentText = current + weather.getTemp() + degree;
   


        /**
         * adds current values to listView
         */

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.top_view, parent, false);
            TextView dateTextView = convertView.findViewById(R.id.current_date);
            TextView tempTextView = convertView.findViewById(R.id.current_temp);
            TextView weatherText = convertView.findViewById(R.id.condition);
            dateTextView.setText(weather.getDate());
            tempTextView.setText(currentText);
            weatherText.setText(weather.getIconPhrase());
       
        return convertView;
    }
}
