package cz.martykan.forecastie;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import android.util.Pair;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import android.test.suitebuilder.annotation.SmallTest;
import com.robotium.solo.Solo;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import cz.martykan.forecastie.activities.MainActivity;


/**
 * Created by Gurvir's Surface on 2017-04-11.
 */

@RunWith(AndroidJUnit4.class)
public class InstrumentedTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public InstrumentedTest() {
        super("cz.martykan.forecastie.activities", MainActivity.class);
    }

    private Solo solo;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        solo.finishOpenedActivities();
    }

    @Test
    public void firstActivityTest() {
        //solo.waitForActivity(MainActivity.class, 100000);
        solo.assertCurrentActivity("Test first activity", MainActivity.class);
    }
}
