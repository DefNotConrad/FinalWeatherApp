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
 *
 */
public class NetworkUtils {
    private static final String TAG = "NetworkUtils";

    /*
     *   1. Need to convert to openweather API url calls and API key
     *   2. Need to create more for the calls for other functionalities
     *
     */
    private final static String WEATHERDB_BASE_URL=
            "http://dataservice.accuweather.com/forecasts/v1/daily/5day/351193";

    private final static String API_KEY = "HXkkQq4yAK63PD7DwgbUa8WjYXImyPsA";
    private final static String PARAM_API_KEY = "apikey";

    /*
     *  Builds URL for API call specifics.
     *
     *  3. Need to add multiple url builds for the different weather display calls (hourly, etc.)
     */
    public static URL buildUrlForWeather() {
        Uri builtUri = Uri.parse(WEATHERDB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "buildUrlForWeather: url: "+url);
        return url;
    }

    /*
     * 1. Need to change delimeter to match Openweather API, may want to utilize another JSON parser
     */
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
