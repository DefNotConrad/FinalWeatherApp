package com.example.weatherapp;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 *
 * WeatherAdapter adapts an array of current Weather objects into a ListView
 *
 */
public class WeatherAdapter extends ArrayAdapter<Weather>{


    /**
     * Constructor for WeatherAdapter utilizes
     * inheritance from ArrayAdapter class for specified ArrayList
     *
     * @param context
     * @param weatherArrayList ArrayList of Weather objects holding current data
     */
    public WeatherAdapter(@NonNull Context context, ArrayList<Weather> weatherArrayList) {
        super(context, 0, weatherArrayList);
    }

    /**
     *
     * Adds one weather item to the ListView and fills with current weather information
     *
     * @param position Position in Array List
     * @param convertView View object used to determine context
     * @param parent ViewGroup object
     * @return convertView
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weather weather = getItem(position);
        String current = "Currently: ";
        String degree = "°";
        String currentText = current + weather.getTemp() + degree;


            convertView = LayoutInflater.from(getContext()).inflate(R.layout.top_view, parent, false);
            TextView dateTextView = convertView.findViewById(R.id.current_date);
            TextView tempTextView = convertView.findViewById(R.id.current_temp);
            TextView weatherText = convertView.findViewById(R.id.condition);

            dateTextView.setText(weather.getDate());
            tempTextView.setText(currentText);
            weatherText.setText(weather.getIconPhrase());



        ImageView weatherIcon = convertView.findViewById(R.id.current_icon);
            Resources resources = convertView.getResources();
            final int resourceId = resources.getIdentifier("ic_"+weather.getIcon(), "drawable", getContext().getPackageName());

            Drawable myDrawable = convertView.getResources().getDrawable(resourceId);
            weatherIcon.setImageDrawable(myDrawable);

        return convertView;
    }
}
