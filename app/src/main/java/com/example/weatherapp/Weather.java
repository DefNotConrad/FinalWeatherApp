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
    String temp;
    String sunrise;
    String sunset;
    String unit;

    Boolean day;

    Boolean precipitation;
    String PrecipType;
    int precipitationProb;
    int key;


    String icon;
    String iconPhrase;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getPrecipType() {
        return PrecipType;
    }

    public void setPrecipType(String precipType) {
        PrecipType = precipType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconPhrase() {
        return iconPhrase;
    }

    public void setIconPhrase(String iconPhrase) {
        this.iconPhrase = iconPhrase;
    }

    public Boolean getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Boolean precipitation) {
        this.precipitation = precipitation;
    }

    public int getPrecipitationProb() {
        return precipitationProb;
    }

    public void setPrecipitationProb(int precipitationProb) {
        this.precipitationProb = precipitationProb;
    }

    public Boolean getDay() {
        return day;
    }

    public void setDay(Boolean day) {
        this.day = day;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String currentTemp) {
        this.temp = currentTemp;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDate() {
        return date;
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

}
