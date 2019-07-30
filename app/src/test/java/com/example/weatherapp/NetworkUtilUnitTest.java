package com.example.weatherapp;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NetworkUtilUnitTest {
    NetworkUtils netTest;

    @Before
    public void createNetworkTest(){
        NetworkUtils netTest = new NetworkUtils();
    }

    @Test
    public void URLTest(){
        String url = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/351193.json?apikey=HXkkQq4yAK63PD7DwgbUa8WjYXImyPsA/";
        assertEquals(NetworkUtils.buildUrlForWeather(),url);
    }
}

