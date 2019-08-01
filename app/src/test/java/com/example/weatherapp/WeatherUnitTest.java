package com.example.weatherapp;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherUnitTest{

    Weather weatherTest;

    @Before
    public void createWeatherTest() {
        weatherTest = new Weather();
    }

    @Test
    public void date_check(){
        String dummyDate = "1234";
        weatherTest.setDate("1234");
        assertEquals(Integer.parseInt(weatherTest.getDate()),Integer.parseInt(dummyDate));
    }

    @Test
    public void min_check(){
        String dummyMin = "4321";
        weatherTest.setMinTemp("4321");
        assertEquals(Integer.parseInt(weatherTest.getMinTemp()),Integer.parseInt(dummyMin));
    }

    @Test
    public void max_check(){
        String dummyMax = "200";
        weatherTest.setMaxTemp("200");
        assertEquals(Integer.parseInt(weatherTest.getMaxTemp()),Integer.parseInt(dummyMax));
    }

    @Test
    public void temp_check(){
        String dummyTemp = "200";
        weatherTest.setTemp("200");
        assertEquals(Integer.parseInt(weatherTest.getTemp()),Integer.parseInt(dummyTemp));
    }
    @Test
    public void sunrise_check(){
        String dummySunrise = "200";
        weatherTest.setSunrise("200");
        assertEquals(Integer.parseInt(weatherTest.getSunrise()),Integer.parseInt(dummySunrise));
    }
    @Test
    public void sunset_check(){
        String dummySunset = "200";
        weatherTest.setSunset("200");
        assertEquals(Integer.parseInt(weatherTest.getSunset()),Integer.parseInt(dummySunset));
    }
    @Test
    public void unit_check(){
        String dummyUnit = "200";
        weatherTest.setUnit("200");
        assertEquals(Integer.parseInt(weatherTest.getUnit()),Integer.parseInt(dummyUnit));
    }
    @Test
    public void precip_type_check(){
        String dummyPrecipType = "200";
        weatherTest.setPrecipType("200");
        assertEquals(Integer.parseInt(weatherTest.getPrecipType()),Integer.parseInt(dummyPrecipType));
    }
    @Test
    public void precip_prob_check(){
        int dummyPrecipProb = 200;
        weatherTest.setPrecipitationProb(200);
        assertEquals(weatherTest.getPrecipitationProb(),dummyPrecipProb);
    }
    @Test
    public void day_check(){
        boolean dummyDay = true;
        weatherTest.setDay(true);
        assertEquals(weatherTest.getDay(),dummyDay);
    }
    @Test
    public void precip_check(){
        boolean dummyPrecip = true;
        weatherTest.setPrecipitation(true);
        assertEquals(weatherTest.getPrecipitation(),dummyPrecip);
    }
    @Test
    public void icon_check(){
        String dummyIcon = "200";
        weatherTest.setIcon("200");
        assertEquals(Integer.parseInt(weatherTest.getIcon()),Integer.parseInt(dummyIcon));
    }

    @Test
    public void icon_phrase_check(){
        String dummyIconPhrase = "200";
        weatherTest.setIconPhrase("200");
        assertEquals(Integer.parseInt(weatherTest.getIconPhrase()),Integer.parseInt(dummyIconPhrase));
    }
}
