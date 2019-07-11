package com.servicenow.skilledservice.view.Activities;

import android.app.Activity;
import android.content.Intent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class ActivityLaunchTest {
    private ActivityLaunch mActivityLaunch;
    private ActivityController<ActivityLaunch> mController;
    @Before
    public void setUp() throws Exception {
       mController = Robolectric.buildActivity(ActivityLaunch.class, createWithIntent("Intention"));
       mActivityLaunch = createActivityFullLifeCycle(mController);

    }

    public static <T extends Activity> T createActivityFullLifeCycle(ActivityController<T> controller) {
        try {
            return controller.create().start().resume().visible().get();
        } catch (Exception ex) {
            return controller.get();
        }
    }


    private Intent createWithIntent(String intention) {

        Intent intent = new Intent(RuntimeEnvironment.application, ActivityLaunch.class);
        intent.putExtra("Intention", intention);
        return intent;
    }

    @After
    public void tearDown() throws Exception {
    }

    private Intent getIntent(){
        return new Intent();
    }

    @Test
    public void testActivityLifeCycle(){
        Assert.assertEquals("Intention",mActivityLaunch.getIntent().getStringExtra("Intention"));
    }

    @Test
    public void BackButtonPress(){

    }
}