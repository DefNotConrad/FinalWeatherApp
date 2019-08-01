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
 *  1.  Need to add location based updates
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Weather> weatherArrayList = new ArrayList<>();
    private ArrayList<Weather> dailyArrayList = new ArrayList<>();
    private ArrayList<Weather> hourlyArrayList = new ArrayList<>();
    private ArrayList<Weather> currentArrayList = new ArrayList<>();
    private ListView listView;
    private int key;
    private boolean metric = false;

    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }

    public boolean isMetric() {
        return metric;
    }
    public void setMetric(boolean metric) {
        this.metric = metric;
    }

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
        for(int x=0; x<3; x++){
            setKey(x);
            URL weatherUrl = NetworkUtils.buildUrlForWeather(getKey());
            new FetchWeatherDetails().execute(weatherUrl);
            Log.i(TAG, "onCreate: weatherUrl: " + weatherUrl);
        }
    }

    /*
     * API fetch, handles exception if no return. Also builds weather list from return.
     * Now: handles hourly, daily and current
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
         * Fills weatherArrayLists and displays the values in the log for troubleshooting
         * 1. Need to adjust based on the information we will be utilizing from API
         */
        @Override
        protected void onPostExecute(String weatherSearchResults) {
            if(weatherSearchResults != null && !weatherSearchResults.equals("")) {
                weatherArrayList = parseJSON(weatherSearchResults);
                Iterator itr = weatherArrayList.iterator();

                while(itr.hasNext()) {
                    Weather weatherInIterator = (Weather) itr.next();

                    switch(getKey()){
                        //daily
                        case 0:{
                            Log.i(TAG, "onPostExecute: Date: " + weatherInIterator.getDate()+
                                    " Min: " + weatherInIterator.getMinTemp() +
                                    " Max: " + weatherInIterator.getMaxTemp() +
                                    " Unit: " + weatherInIterator.getUnit() +
                                    "Icon: " + weatherInIterator.getIcon() +
                                    "IconPhrase: " + weatherInIterator.getIconPhrase() +
                                    "HasPrecip: " + weatherInIterator.getPrecipitation());
                        }
                        //hourly
                        case 1:{
                            Log.i(TAG, "onPostExecute: Date: " + weatherInIterator.getDate()+
                                    " IsDayLight: " + weatherInIterator.getDay() +
                                    " Temp: " + weatherInIterator.getTemp()+
                                    "HasPrecip: " + weatherInIterator.getPrecipitation()+
                                    "Precip Prob: " +  weatherInIterator.getPrecipitationProb()+
                                    " Unit: " + weatherInIterator.getUnit() +
                                    "Icon: " + weatherInIterator.getIcon() +
                                    "IconPhrase: " + weatherInIterator.getIconPhrase());
                        }
                        //current
                        case 2:{
                            Log.i(TAG, "onPostExecute: Date: " + weatherInIterator.getDate()+
                                    " Temp: " + weatherInIterator.getTemp()+
                                    " Unit: " + weatherInIterator.getUnit() +
                                    " Metric: " + isMetric() +
                                    "HasPrecip: " + weatherInIterator.getPrecipitation()+
                                    "PrecipType: " + weatherInIterator.getPrecipType() +
                                    "Icon: " + weatherInIterator.getIcon() +
                                    "IconPhrase: " + weatherInIterator.getIconPhrase());
                        }
                    }
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
     */
    private ArrayList<Weather> parseJSON(String weatherSearchResults) {
        if(weatherSearchResults != null) {

            switch(getKey()) {
                //daily
                case 0:{
                    if(dailyArrayList != null) {
                        dailyArrayList.clear();
                    }
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
                            String unit= temperatureObj.getJSONObject("Minimum").getString("Unit");
                            weather.setUnit(unit);
                            JSONObject dayObj = resultsObj.getJSONObject("Day");
                            String icon = dayObj.getString("Icon");
                            weather.setIcon(icon);
                            String iconPhrase = dayObj.getString("IconPhrase");
                            weather.setIconPhrase(iconPhrase);
                            String precip = dayObj.getString("HasPrecipitation");
                            weather.setPrecipitation(Boolean.parseBoolean(precip));

                            dailyArrayList.add(weather);
                        }
                            if(dailyArrayList != null) {
                                WeatherAdapter weatherAdapter = new WeatherAdapter(this, dailyArrayList);
                                listView.setAdapter(weatherAdapter);
                            }

                            return dailyArrayList;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                //hourly forecast
                case 1: {
                    if(hourlyArrayList != null) {
                        hourlyArrayList.clear();
                    }
                    try {
                        JSONObject rootObject = new JSONObject(weatherSearchResults);
                        JSONArray results = rootObject.getJSONArray("HourlyForecasts");

                        for (int i = 0; i < results.length(); i++) {
                            Weather weather = new Weather();
                            JSONObject resultsObj = results.getJSONObject(i);
                            String date = resultsObj.getString("Date");
                            weather.setDate(date);
                            String day = resultsObj.getString("IsDaylight");
                            weather.setDay(Boolean.parseBoolean(day));
                            String precip = resultsObj.getString("HasPrecipitation");
                            weather.setPrecipitation(Boolean.parseBoolean(precip));
                            JSONObject temperatureObj = resultsObj.getJSONObject("Temperature");
                            String temperature = temperatureObj.getString("Value");
                            weather.setTemp(temperature);
                            String unit = temperatureObj.getString("Unit");
                            weather.setUnit(unit);
                            String precipProb = resultsObj.getString("PrecipitationProbability");
                            weather.setPrecipitationProb(Integer.parseInt(precipProb));
                            String icon = resultsObj.getString("WeatherIcon");
                            weather.setIcon(icon);
                            String iconPhrase = resultsObj.getString("IconPhrase");
                            weather.setIconPhrase(iconPhrase);

                            hourlyArrayList.add(weather);
                        }
                            if(hourlyArrayList != null) {
                                WeatherAdapter weatherAdapter = new WeatherAdapter(this, weatherArrayList);
                                listView.setAdapter(weatherAdapter);
                            }

                            return hourlyArrayList;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                //current forecast
                case 2: {
                    if(currentArrayList != null) {
                        currentArrayList.clear();
                    }
                    try {
                        JSONObject rootObject = new JSONObject(weatherSearchResults);
                        JSONArray results = rootObject.getJSONArray("CurrentForecast");

                        for (int i = 0; i < results.length(); i++) {
                            Weather weather = new Weather();
                            JSONObject resultsObj = results.getJSONObject(i);
                            String date = resultsObj.getString("Date");
                            weather.setDate(date);
                            JSONObject temperatureObj = resultsObj.getJSONObject("Temperature");
                            if(metric){
                                JSONObject metricObj = temperatureObj.getJSONObject("Metric");
                                String temp = metricObj.getString("Value");
                                weather.setTemp(temp);
                                String unit = metricObj.getString("Unit");
                                weather.setUnit(unit);
                            }else{
                                JSONObject imperialObj = temperatureObj.getJSONObject("Imperial");
                                String temp = imperialObj.getString("Value");
                                weather.setTemp(temp);
                                String unit = imperialObj.getString("Unit");
                                weather.setUnit(unit);
                            }
                            String precip = resultsObj.getString("HasPrecipitation");
                            weather.setPrecipitation(Boolean.parseBoolean(precip));
                            String precipType = resultsObj.getString("PrecipitationType");
                            weather.setPrecipType(precipType);
                            String icon = resultsObj.getString("WeatherIcon");
                            weather.setIcon(icon);
                            String iconPhrase = resultsObj.getString("WeatherText");
                            weather.setIconPhrase(iconPhrase);


                                currentArrayList.add(weather);
                        }
                        if (currentArrayList!= null) {
                            WeatherAdapter weatherAdapter = new WeatherAdapter(this, currentArrayList);
                            listView.setAdapter(weatherAdapter);
                        }

                        return currentArrayList;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
