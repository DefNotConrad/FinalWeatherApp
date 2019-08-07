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
 * DailyAdapter adapts an array of daily Weather objects into a ListView
 *
 */
public class DailyAdapter extends ArrayAdapter<Weather>{

    /**
     * Constructor for DailyAdapter utilizes
     * inheritance from ArrayAdapter class for specified ArrayList
     *
     * @param context
     * @param weatherArrayList ArrayList of Weather objects holding hourly data
     */
    public DailyAdapter(@NonNull Context context, ArrayList<Weather> weatherArrayList) {
        super(context, 0, weatherArrayList);
    }

    /**
     *
     * Adds one Weather item to the ListView at time,
     * inflates the layout and fills with daily weather information
     *
     * @param position position in ArrayList
     * @param convertView View object
     * @param parent ViewGroup
     * @return convertView
     */
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

                ImageView weatherIcon = convertView.findViewById(R.id.daily_icon);
                Resources resources = convertView.getResources();
                final int resourceId = resources.getIdentifier("ic_"+weather.getIcon(), "drawable", getContext().getPackageName());

                Drawable myDrawable = convertView.getResources().getDrawable(resourceId);
                weatherIcon.setImageDrawable(myDrawable);
            }

        return convertView;
    }
}
