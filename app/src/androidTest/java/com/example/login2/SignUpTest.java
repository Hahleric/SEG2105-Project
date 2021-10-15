package com.example.login2;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SignUpTest {
    @Rule
    public ActivityScenarioRule<RegisterActivity> ma = new ActivityScenarioRule<>(RegisterActivity.class);
    @Test
    public void checkNameEntered() throws Exception{
        onView(withId(R.id.eFName)).perform(typeText("233"),closeSoftKeyboard());
        onView(withText("233")).check(matches(isDisplayed()));
    }
    @Test
    public void checkPasswordEntered() throws Exception{
        onView(withId(R.id.ePassword)).perform(typeText("233"),closeSoftKeyboard());
        onView(withText("233")).check(matches(isDisplayed()));
    }

}
