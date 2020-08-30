package com.mehadjebioussama.developerslife;

import com.mehadjebioussama.developerslife.db.GifDbModel;
import com.mehadjebioussama.developerslife.mainactivity.MainActivity;
import com.mehadjebioussama.developerslife.util.EspressoTestingIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoTestingIdlingResource.getIdlingResource());
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoTestingIdlingResource.getIdlingResource());
    }

    @Test
    public void mainTest(){
        onView(ViewMatchers.withId(R.id.card_view)).check(matches(isDisplayed()));

        activityTestRule.getActivity().runOnUiThread(()->activityTestRule.getActivity().showGif(new GifDbModel("hello", "http://static.devli.ru/public/images/gifs/201704/099595df-ad11-4a7a-83bd-b65de1cc044a.gif","latest")));
    }

    @Test
    public void onClick(){
        onView(ViewMatchers.withId(R.id.next)).check(matches(isClickable()));
        onView(ViewMatchers.withId(R.id.previous)).check(matches(isClickable()));
    }

    @Test
    public void viewPagerTest(){
        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withId(R.id.viewpager)).perform(swipeRight());
    }
}
