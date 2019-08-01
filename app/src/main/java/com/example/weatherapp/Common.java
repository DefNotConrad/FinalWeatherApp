package com.example.weatherapp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static String unixTimeStampToDateTime(double unixTimeStamp){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long)unixTimeStamp*1000);
        return dateFormat.format(date);
    }

    public static String getImage(String icon){
        return String.format("http://openweathermap.org/img/w/%s.png,icon");
    }

    public static String getDateNow(String newDate){
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss-HH:mm");
        Date date = null;

        try {
            date = dateFormat.parse(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat out = new SimpleDateFormat("MM-dd-YYYY");
        return out.format(date);

    }


}
