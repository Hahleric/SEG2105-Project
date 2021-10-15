package com.example.login2;

import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

public class EmployeeTest {
    @Rule
    public ActivityScenarioRule<LogSucActivity> ma = new ActivityScenarioRule<>(LogSucActivity.class);
    @Test
    public void timeSet(){
        onView(withId(R.id.startTime)).perform(typeText("10.00"),closeSoftKeyboard());
        onView(withId(R.id.endTime)).perform(typeText("22.00"),closeSoftKeyboard());
        onView(withId(R.id.setTime)).perform(click());
        assertEquals(LogSucActivity.timeOne,"10.00");
    }
    @Test
    public void timeSet2(){
        onView(withId(R.id.startTime)).perform(typeText("10.00"),closeSoftKeyboard());
        onView(withId(R.id.endTime)).perform(typeText("22.00"),closeSoftKeyboard());
        onView(withId(R.id.setTime)).perform(click());
        assertEquals(LogSucActivity.timeTwo,"22.00");
    }
    @Test
    public void serviceSet(){
        String[] form = new String[3];
        form[0]="233";
        String[] doc= new String[3];
        doc[0]="233";
        Service testService = new Service("233",form,doc);
        testService.id = 0;
        adminSucActivity.services.add(testService);
        onView(withId(R.id.serviceName)).perform(typeText("233"),closeSoftKeyboard());
        onView(withId(R.id.addService)).perform(click());
        onView(withText("233")).check(matches(isDisplayed()));

    }
    @Test
    public void serviceDelete(){
        String[] form = new String[3];
        form[0]="233";
        String[] doc= new String[3];
        doc[0]="233";
        Service testService = new Service("233",form,doc);
        testService.id = 1;
        adminSucActivity.services.add(testService);

        onView(withId(R.id.serviceName)).perform(typeText("233"),closeSoftKeyboard());
        onView(withId(R.id.deleteService)).perform(click());
        onView(withId(R.id.serviceName)).perform(typeText("233"),closeSoftKeyboard());
        onView(withId(R.id.deleteService)).perform(click());
        assertEquals(0,LogSucActivity.getServices().size());



    }
}
