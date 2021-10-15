package com.example.login2;

import junit.extensions.ActiveTestSuite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Test;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Rule;
import org.junit.runner.RunWith;

public class Deliveriable2Test {
    private View decorView;

    @Rule
    public ActivityScenarioRule<adminSucActivity> ma = new ActivityScenarioRule<>(adminSucActivity.class);
    @Before
    public void setUp() {
        ma.getScenario().onActivity(new ActivityScenario.ActivityAction<adminSucActivity>() {
            @Override
            public void perform(adminSucActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }

        });
    }
    @Test
    public void addServiceTest() throws InterruptedException {
        onView(withId(R.id.editServiceName)).perform(typeText("233"),closeSoftKeyboard());
        onView(withId(R.id.editTextForms)).perform(typeText("233"),closeSoftKeyboard());
        onView(withId(R.id.editTextTextPersonName3)).perform(typeText("233"),closeSoftKeyboard());
        onView(withId(R.id.btnAdd)).perform(click());
        Thread.sleep(1000);
        onView(withText("233")).check(matches(isDisplayed()));

    }

}
