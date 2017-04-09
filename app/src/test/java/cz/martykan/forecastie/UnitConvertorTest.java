package cz.martykan.forecastie;

import android.content.SharedPreferences;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.mock.*;


import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import cz.martykan.forecastie.utils.UnitConvertor;

import static org.junit.Assert.*;
/**
 * Created by Gurvir's Surface on 2017-04-08.
 */



public class UnitConvertorTest extends InstrumentationTestCase {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Context context;
    public static final String PREFS_NAME = "AOP_PREFS";

    @Before
    public void setUp(){
        context = new MockContext();
        sp = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    @After
    public void tearDown() {
        editor = null;
        context = null;
        sp = null;
    }

    @Test
    public void test(){
        editor.putString("unit", "C");
        editor.commit();

        assertEquals((300 - 273.15f), UnitConvertor.convertTemperature(300,sp));
    }
}
