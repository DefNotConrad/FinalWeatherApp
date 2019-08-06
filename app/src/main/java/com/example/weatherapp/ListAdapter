package com.example.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

            String precipitation = "Precipitation: ";
            String precip = "Precip: ";
            String high = "High: ";
            String low = "Low: ";
            String current = "Currently: ";
            String percent = "%";
            String degree = "°";

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_item, parent, false);
            }

            switch (key) {
                case 2: {
                    TextView dateTextView = convertView.findViewById(R.id.current_date);
                    TextView tempTextView = convertView.findViewById(R.id.current_temp);
                    TextView precipView = convertView.findViewById(R.id.precipitation_info);
                    dateTextView.setText(weather.getDate());
                    tempTextView.setText(current + weather.getTemp() + degree);
                    precipView.setText(precipitation + weather.getPrecipitationProb() + percent);
                }
                case 1: {
                    TextView dateTextView = convertView.findViewById(R.id.item_top_text);
                    ImageView iconView = convertView.findViewById(R.id.item_icon);
                    TextView minTextView = convertView.findViewById(R.id.item_low);
                    TextView maxTextView = convertView.findViewById(R.id.item_high);
                    TextView precipView = convertView.findViewById(R.id.item_precip);
                    dateTextView.setText(weather.getDate());
                    minTextView.setText(low + weather.getMinTemp() + degree);
                    maxTextView.setText(high + weather.getMaxTemp() + degree);
                    precipView.setText(precip + weather.getPrecipitationProb() + percent);
                    //iconView.setImageDrawable();
                }
                case 0: {
                    TextView dateTextView = convertView.findViewById(R.id.item_top_text);
                    ImageView iconView = convertView.findViewById(R.id.item_icon);
                    TextView minTextView = convertView.findViewById(R.id.item_low);
                    TextView maxTextView = convertView.findViewById(R.id.item_high);
                    TextView precipView = convertView.findViewById(R.id.item_precip);
                    dateTextView.setText(weather.getDate());
                    minTextView.setText(low + weather.getMinTemp() + degree);
                    maxTextView.setText(high + weather.getMaxTemp() + degree);
                    precipView.setText(precip + weather.getPrecipitationProb() + percent);
                    //iconView.setImageDrawable();
                }
            }
                return convertView;
        }
}

