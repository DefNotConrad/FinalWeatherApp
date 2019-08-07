package com.example.weatherapp;

import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
/*
 *
 * Handles network connection, API call.
 * Now: handles 5-day, hourly(12hrs), and current
 *
 */
public class NetworkUtils {
    private static final String TAG = "NetworkUtils";

    private final static String WEATHER_URL[]={ "http://dataservice.accuweather.com/forecasts/v1/daily/5day/335932",
                                                 "http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/335932",
                                                "http://dataservice.accuweather.com/currentconditions/v1/335932"};
    private final static String API_KEY = "HXkkQq4yAK63PD7DwgbUa8WjYXImyPsA";
    private final static String PARAM_API_KEY = "apikey";

    /*
     *  Builds URL for API call specifics.
     */
    public static URL buildUrlForWeather(int x) {
        URL url = null;
            //Daily weather call = 0, hourly =1, current = 2

                    Uri builtUri = Uri.parse(WEATHER_URL[x]).buildUpon()
                        .appendQueryParameter(PARAM_API_KEY, API_KEY)
                        .build();
                try {
                    url = new URL(builtUri.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
        Log.i(TAG, "buildUrlForWeather: url: "+url);
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in  = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
