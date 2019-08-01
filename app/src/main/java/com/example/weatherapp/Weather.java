package com.example.weatherapp;

/*
 *  Model class which holds and returns information but does not inform of a change.
 *
 *  In order for class to resemble MVC would need to add change notification through an event handler
 *  This can be the class that handles if location changes possibly. If we need it.
 *
 */
public class Weather {
    String date;
    String minTemp;
    String maxTemp;
    String link;



    public String getDate() {

        String newDate = null;

        newDate = Common.getDateNow(date);
        return newDate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
