package cz.martykan.forecastie;

import android.content.SharedPreferences;
import android.content.Context;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import cz.martykan.forecastie.utils.UnitConvertor;

import static org.junit.Assert.*;
/**
 * Created by Gurvir's Surface on 2017-04-08.
 */



public class UnitConvertorTest{
    private static final String PREFS_NAME = "AOP_PREFS";
    private SharedPreferences sharedPrefs = mock(SharedPreferences.class);
    private Context context;

    @Before
    public void setUp(){
        sharedPrefs = mock(SharedPreferences.class);
        context = mock(Context.class);
    }

    @After
    public void tearDown() {
        context = null;
        sharedPrefs = null;
    }

    @Test
    public void tempKelvinToCelsius(){
        when(sharedPrefs.getString("unit","C")).thenReturn("°C");
        int tempInKelvin = 300;
        assertEquals((tempInKelvin - 273.15f), UnitConvertor.convertTemperature(tempInKelvin,sharedPrefs), 0.000001f);
    }

    @Test
    public void tempKelvinToFahrenheit(){
        when(sharedPrefs.getString("unit","C")).thenReturn("°F");
        int tempInKelvin = 300;
        float kevinTemp = (9*(tempInKelvin - 273.15f)/5) + 32;
        assertEquals(kevinTemp, UnitConvertor.convertTemperature(tempInKelvin,sharedPrefs), 0.000001f);
    }

    @Test
    public void noTempSpecified(){
        when(sharedPrefs.getString("unit","C")).thenReturn("");
        int tempInKelvin = 300;
        assertEquals(tempInKelvin, UnitConvertor.convertTemperature(tempInKelvin,sharedPrefs), 0.000001f);
    }
}
