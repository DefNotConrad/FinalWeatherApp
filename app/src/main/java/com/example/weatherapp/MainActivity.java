package com.example.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;


/*
 * The main controller class. Contains the main functionality of the program,
 *  may need to break it into internal classes to match the SRS.
 *
 *  1. Need to add hourly functionality
 *  2. Need to add location based updates
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    /*
     * 5 day weather list.
     *
     * 1. Need to add more variables to handle hourly and current
     */
    private ArrayList<Weather> weatherArrayList = new ArrayList<>();
    private ListView listView;


    /*
     * Creates API URL and call, as well as initial view
     * 1. Need to tie the initial view into current location
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.idListView);
        URL weatherUrl = NetworkUtils.buildUrlForWeather();
        new FetchWeatherDetails().execute(weatherUrl);
        Log.i(TAG, "onCreate: weatherUrl: " + weatherUrl);
    }

    /*
     * API fetch, handles exception if no return. Also builds weather list from return.
     * 1. need to allow for this call to handle multiple call types and returns (hourly, etc.)
     */
    private class FetchWeatherDetails extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /*
         *  Checks response from URL
         */
        @Override
        protected String doInBackground(URL... urls) {
            URL weatherUrl = urls[0];
            String weatherSearchResults = null;
            try {
                weatherSearchResults = NetworkUtils.getResponseFromHttpUrl(weatherUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "doInBackground: weatherSearchResults: " + weatherSearchResults);
            return weatherSearchResults;
        }

        /*
         * Fills weatherArrayList and displays the values in the log for troubleshooting
         * 1. Need to adjust based on the information we will be utilizing from API
         */

        @Override
        protected void onPostExecute(String weatherSearchResults) {
            if(weatherSearchResults != null && !weatherSearchResults.equals("")) {
                weatherArrayList = parseJSON(weatherSearchResults);
                Iterator itr = weatherArrayList.iterator();

                while(itr.hasNext()) {
                    Weather weatherInIterator = (Weather) itr.next();
                    Log.i(TAG, "onPostExecute: Date: " + weatherInIterator.getDate()+
                            " Min: " + weatherInIterator.getMinTemp() +
                            " Max: " + weatherInIterator.getMaxTemp() +
                            " Link: " + weatherInIterator.getLink());
                }
            }
            super.onPostExecute(weatherSearchResults);
        }
    }

    /*
     *
     * After checking the return is not null and emptying previous array call:
     * fills a Weather object and adds it to weatherArrayList. Then calls weather adapter
     * to convert it into view object.
     *
     * 1. Need to adjust the information for the OpenWeather API return
     * 2. Need to utilize for the other weather calls (hourly, etc)
     *
     */
    private ArrayList<Weather> parseJSON(String weatherSearchResults) {
        if(weatherArrayList != null) {
            weatherArrayList.clear();
        }

        if(weatherSearchResults != null) {
            try {
                JSONObject rootObject = new JSONObject(weatherSearchResults);
                JSONArray results = rootObject.getJSONArray("DailyForecasts");

                for (int i = 0; i < results.length(); i++) {
                    Weather weather = new Weather();
                    JSONObject resultsObj = results.getJSONObject(i);
                    String date = resultsObj.getString("Date");
                    weather.setDate(date);
                    JSONObject temperatureObj = resultsObj.getJSONObject("Temperature");
                    String minTemperature = temperatureObj.getJSONObject("Minimum").getString("Value");
                    weather.setMinTemp(minTemperature);
                    String maxTemperature = temperatureObj.getJSONObject("Maximum").getString("Value");
                    weather.setMaxTemp(maxTemperature);
                    String link = resultsObj.getString("Link");
                    weather.setLink(link);
                    weatherArrayList.add(weather);
                }
                if(weatherArrayList != null) {
                    WeatherAdapter weatherAdapter = new WeatherAdapter(this, weatherArrayList);
                    listView.setAdapter(weatherAdapter);
                }

                return weatherArrayList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
