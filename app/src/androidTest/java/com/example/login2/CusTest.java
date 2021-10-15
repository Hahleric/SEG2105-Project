package com.example.login2;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

public class CusTest {
    @Rule
    public ActivityScenarioRule<CusSucActivity> ma = new ActivityScenarioRule<>(CusSucActivity.class);
    @Test
    public void serviceSet(){
        Branch branch = new Branch();
        branch.branchName = "testBranch";
        branch.profileContent.add("0");
        branch.profileContent.add("1");
        BranchList.branches.add(branch);
        onView(withId(R.id.sts)).perform(typeText("0"),closeSoftKeyboard());
        onView(withId(R.id.ets)).perform(typeText("1"),closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        onView(withText("Branch name: "+branch.branchName +",  Rating: "+branch.getRating())).check(matches(isDisplayed()));

    }
}
