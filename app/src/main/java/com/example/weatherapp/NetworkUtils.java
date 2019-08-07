package com.example.weatherapp;

import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * NetworkUtils builds URL with Uri parse of strings and makes API call
 *
 */
public class NetworkUtils {
    private static final String TAG = "NetworkUtils";

    private final static String WEATHER_URL[]={ "http://dataservice.accuweather.com/forecasts/v1/daily/5day/335932",
                                                 "http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/335932",
                                                "http://dataservice.accuweather.com/currentconditions/v1/335932"};
    private final static String API_KEY = "x98DRnAbUEkow2nLw4XjPVkvuC3HHIGC";
    private final static String PARAM_API_KEY = "apikey";

    /**
     *
     * Creates Uri object from base weather url, api key and identifier.
     *  It then creates a URL from the Uri string and returns, throwing
     *  an exception if unable.
     *
     * @param x Counter to traverse WEATHER_URL array
     * @return url for API call
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

    /**
     *
     * Connects to URL and parses through return adding it to
     * InputStream object to be stored as a sting in main class.
     *
     * @param url URL for connecting and returngin API response
     * @return string from scanner
     * @throws IOException
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
