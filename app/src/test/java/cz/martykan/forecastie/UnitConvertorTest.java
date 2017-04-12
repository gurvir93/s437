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

    // TEMPERATURE Unit Conversion Tests
    @Test
    public void tempKelvinToCelsius(){
        when(sharedPrefs.getString("unit","C")).thenReturn("°C");
        int tempInKelvin = 300;
        float convertToCelsius = tempInKelvin - 273.15f;
        assertEquals(convertToCelsius, UnitConvertor.convertTemperature(tempInKelvin, sharedPrefs), 0.00001f);
    }

    @Test
    public void tempKelvinToFahrenheit(){
        when(sharedPrefs.getString("unit","C")).thenReturn("°F");
        int tempInKelvin = 300;
        float covertToFahrenheit = (9 * (tempInKelvin - 273.15f) / 5) + 32;
        assertEquals(covertToFahrenheit, UnitConvertor.convertTemperature(tempInKelvin, sharedPrefs), 0.00001f);
    }

    @Test
    public void noTempSpecified(){
        when(sharedPrefs.getString("unit","C")).thenReturn("");
        int tempInKelvin = 300;
        assertEquals(tempInKelvin, UnitConvertor.convertTemperature(tempInKelvin, sharedPrefs), 0.00001f);
    }

    // RAIN Unit Conversion Tests
    @Test
    public void rainInMM(){
        when(sharedPrefs.getString("lengthUnit","mm")).thenReturn("mm");
        double rainInMM = 0.01;
        assertEquals(" (<0.1 mm)", UnitConvertor.getRainString(rainInMM, sharedPrefs));

        rainInMM = 0.1;
        assertEquals(" (0.1 mm)", UnitConvertor.getRainString(rainInMM, sharedPrefs));

        rainInMM = 9;
        assertEquals(" (9.0 mm)", UnitConvertor.getRainString(rainInMM, sharedPrefs));
    }

    @Test
    public void rainInInch(){
        when(sharedPrefs.getString("lengthUnit","mm")).thenReturn("in");
        double rainInMM = 0.2;
        double rainInch = rainInMM / 25.4;
        assertEquals(" (<0.01 in)", UnitConvertor.getRainString(rainInMM, sharedPrefs));

        rainInMM = 25.4;
        rainInch = rainInMM / 25.4;
        assertEquals(" (1.00 in)", UnitConvertor.getRainString(rainInMM, sharedPrefs));

        rainInMM = 100;
        rainInch = rainInMM / 25.4;
        assertEquals(" (3.94 in)", UnitConvertor.getRainString(rainInMM, sharedPrefs));
    }

    @Test
    public void rainNoneTest(){
        when(sharedPrefs.getString("lengthUnit","mm")).thenReturn("mm");
        double rainInMM = 0;
        assertEquals("", UnitConvertor.getRainString(rainInMM, sharedPrefs));
    }

    // PRESSURE Unit Conversion Tests
    @Test
    public void pressureInKPA(){
        when(sharedPrefs.getString("pressureUnit", "hPa")).thenReturn("kPa");
        float pressureInHPA = 3.14f;
        float convertToKPA = pressureInHPA / 10;
        assertEquals(convertToKPA, UnitConvertor.convertPressure(pressureInHPA, sharedPrefs), 0.00001f);
    }

    @Test
    public void pressureInMMHG(){
        when(sharedPrefs.getString("pressureUnit", "hPa")).thenReturn("mm Hg");
        float pressureInHPA = 3.14f;
        float convertToMMHG = pressureInHPA * 0.750061561303f;
        assertEquals(convertToMMHG, UnitConvertor.convertPressure(pressureInHPA, sharedPrefs), 0.00001f);
    }

    @Test
    public void pressureInINHG(){
        when(sharedPrefs.getString("pressureUnit", "hPa")).thenReturn("in Hg");
        float pressureInHPA = 3.14f;
        float convertToINHG = pressureInHPA * 0.0295299830714f;
        assertEquals(convertToINHG, UnitConvertor.convertPressure(pressureInHPA, sharedPrefs), 0.00001f);
    }

    @Test
    public void pressureNoUnitSpecified(){
        when(sharedPrefs.getString("pressureUnit", "hPa")).thenReturn("");
        float pressureInHPA = 3.14f;
        assertEquals(pressureInHPA, UnitConvertor.convertPressure(pressureInHPA, sharedPrefs), 0.00001f);
    }


    // WIND Unit Conversion Tests
    @Test
    public void windMStoKMH(){
        when(sharedPrefs.getString("speedUnit", "m/s")).thenReturn("kph");
        int windSpeed = 20;     // meters/second
        double convertMStoKPH = (windSpeed * 3600 /*seconds*/) / 1000 /*meters*/;
        assertEquals(convertMStoKPH, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);
    }

    @Test
    public void windMStoMPH(){
        when(sharedPrefs.getString("speedUnit", "m/s")).thenReturn("mph");
        int windSpeed = 20;     // meters/second
        double convertMStoMPH = (windSpeed * 3600 /*seconds*/) / (1000 /*meters*/ * 1.609344);
        assertEquals(convertMStoMPH, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);
    }

    @Test
    public void windMStoKnot(){
        when(sharedPrefs.getString("speedUnit", "m/s")).thenReturn("kn");
        int windSpeed = 20;     // meters/second
        double convertMStoKN = windSpeed * 1.9438444924574;
        assertEquals(convertMStoKN, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);
    }

    @Test
    public void windBeaufortNumber() {
        when(sharedPrefs.getString("speedUnit", "m/s")).thenReturn("bft");
        int beaufort = -1;

        double windSpeed = 0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 1.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 3.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 5.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 7.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 10.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 13.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 17.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 20.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 24.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 28.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 32.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

        windSpeed = 40.0;
        beaufort = findBeaufort(windSpeed);
        assertEquals(beaufort, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);

    }

    @Test
    public void windNotSpecified(){
        when(sharedPrefs.getString("speedUnit", "m/s")).thenReturn("m/s");
        int windSpeed = 20;     // meters/second
        assertEquals(windSpeed, UnitConvertor.convertWind(windSpeed, sharedPrefs), 0.00001f);
    }

    @Test
    public void windBeaufortName() {
        int beaufort = 0;
        assertEquals("Calm", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 1;
        assertEquals("Light air", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 2;
        assertEquals("Light breeze", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 3;
        assertEquals("Gentle breeze", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 4;
        assertEquals("Moderate breeze", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 5;
        assertEquals("Fresh breeze", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 6;
        assertEquals("Strong breeze", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 7;
        assertEquals("High wind", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 8;
        assertEquals("Gale", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 9;
        assertEquals("Strong gale", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 10;
        assertEquals("Storm", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 11;
        assertEquals("Violent storm", UnitConvertor.getBeaufortName(beaufort));

        beaufort = 12;
        assertEquals("Hurricane", UnitConvertor.getBeaufortName(beaufort));
    }

    private int findBeaufort(double windSpeed) {
        if (windSpeed < 0.3)
            return 0; // Calm
        if (windSpeed < 1.5)
            return 1; // Light air
        if (windSpeed < 3.3)
            return 2; // Light breeze
        if (windSpeed < 5.5)
            return 3; // Gentle breeze
        if (windSpeed < 7.9)
            return 4; // Moderate breeze
        if (windSpeed < 10.7)
            return 5; // Fresh breeze
        if (windSpeed < 13.8)
            return 6; // Strong breeze
        if (windSpeed < 17.1)
            return 7; // High wind, moderate gale
        if (windSpeed < 20.7)
            return 8; // Gale
        if (windSpeed < 24.4)
            return 9; // Strong gale
        if (windSpeed < 28.4)
            return 10; // Storm, whole gale
        if (windSpeed < 32.6)
            return 11; // Violent storm
        else
            return 12; // Hurricane
    }
}
