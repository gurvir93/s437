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
    public void test(){
        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPrefs);
        assertEquals((300 - 273.15f), UnitConvertor.convertTemperature(300,sharedPrefs));
    }
}
