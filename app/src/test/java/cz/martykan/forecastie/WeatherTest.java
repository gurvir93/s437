package cz.martykan.forecastie;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import cz.martykan.forecastie.models.Weather;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by nickb on 4/10/2017.
 */

public class WeatherTest {
    private static final String PREFS_NAME = "AOP_PREFS";
    private SharedPreferences sharedPrefs = mock(SharedPreferences.class);
    private Context context;
    private Weather weather;

    @Before
    public void setUp(){
        sharedPrefs = mock(SharedPreferences.class);
        context = mock(Context.class);

        weather = new Weather();
    }

    @After
    public void tearDown() {
        context = null;
        sharedPrefs = null;
    }

    /*
    Wind related tests
     */

    @Test
    public void windDirectionToEnumTest(){
        weather.setWindDirectionDegree(0.0);

        assertEquals(Weather.WindDirection.NORTH, weather.getWindDirection());
    }

    @Test
    public void windDirectionNullTest(){
        assertEquals(false, weather.isWindDirectionAvailable());
    }

    /*
    Getters and setters
     */

    @Test
    public void getCityTest(){
        weather.setCity("Calgary");

        assertEquals(true, weather.getCity().equals("Calgary"));
    }

    @Test
    public void getCountryTest(){
        weather.setCountry("Canada");

        assertEquals(true, weather.getCountry().equals("Canada"));
    }

    @Test
    public void getTemperatureTest(){
        weather.setTemperature("-40");

        assertEquals(true, weather.getTemperature().equals("-40"));
    }

    @Test
    public void getDescriptionTest(){
        weather.setDescription("Cold");

        assertEquals(true, weather.getDescription().equals("Cold"));
    }

    @Test
    public void getWindTest(){
        weather.setWind("East");

        assertEquals(true, weather.getWind().equals("East"));
    }

    @Test
    public void getWindDirectionDegreeTest(){
        weather.setWindDirectionDegree(0.0);

        assertEquals(0.0, weather.getWindDirectionDegree());
    }

    @Test
    public void getWindDirectionDegreeTwoTest(){
        weather.setWindDirectionDegree(180.0);

        assertEquals(Weather.WindDirection.SOUTH, weather.getWindDirection(2));
    }

    @Test
    public void getPressureTest(){
        weather.setPressure("1000");

        assertEquals(true, weather.getPressure().equalsIgnoreCase("1000"));
    }

    @Test
    public void getHumidityTest(){
        weather.setHumidity("1000");

        assertEquals(true, weather.getHumidity().equalsIgnoreCase("1000"));
    }

    @Test
    public void getIconTest(){
        weather.setIcon("sample.png");

        assertEquals(true, weather.getIcon().equals("sample.png"));
    }

    @Test
    public void getIdTest(){
        weather.setId("test");

        assertEquals(true, weather.getId().equals("test"));
    }

    @Test
    public void getRainTest(){
        weather.setRain("wet");

        assertEquals(true, weather.getRain().equals("wet"));
    }

    @Test
    public void getLastUpdatedTest(){
        weather.setLastUpdated("today");

        assertEquals(true, weather.getLastUpdated().equals("today"));
    }

    /*
    Date-related tests
     */

    @Test
    public void getSunriseTest(){
        weather.setSunrise("1491810502");

        Date date = new Date(Long.parseLong("1491810502") * 1000);

        assertEquals(date, weather.getSunrise());
    }

    @Test
    public void getSunsetTest(){
        weather.setSunset("1491810502");

        Date date = new Date(Long.parseLong("1491810502") * 1000);

        assertEquals(date, weather.getSunset());
    }

    @Test
    public void getDateTest(){
        weather.setDate("1491810502");

        Date date = new Date(Long.parseLong("1491810502") * 1000);

        assertEquals(date, weather.getDate());
    }

    @Test
    public void getSunriseErrorTest(){
        weather.setSunrise("aaa");

        assertEquals(true, weather.getSunrise()!=null);
    }

    @Test
    public void getSunsetErrorTest(){
        weather.setSunset("aaa");

        assertEquals(true, weather.getSunset()!=null);
    }

    @Test
    public void getDateErrorTest(){
        weather.setDate("aaa");

        assertEquals(true, weather.getDate()!=null);
    }
    
    @Test
    public void getNumberOfDaysFromTest(){
        weather.setDate("1491811760");

        Date date = new Date(Long.parseLong("1491652800") * 1000);

        assertEquals(2, weather.getNumDaysFrom(date));
    }
}


