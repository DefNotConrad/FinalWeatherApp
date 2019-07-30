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
    public void link_check(){
        String dummyLink = "300@isntIt.gov";
        weatherTest.setLink("300@isntIt.gov");
        assertEquals(weatherTest.getLink(),dummyLink);
    }
}