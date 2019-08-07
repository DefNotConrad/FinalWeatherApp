package com.example.weatherapp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {


    public static String getDateNow(String newDate){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-HH:mm");
        Date date = null;

        try {
            date = dateFormat.parse(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat out = new SimpleDateFormat("MM-dd-yyyy");
        return out.format(date);

    }


}