package com.millapps.flickrfeed;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;

import com.millapps.flickrfeed.view.PublicFeedListActivity;
import com.millapps.flickrfeed.view.PublicFeedListFragment;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PublicFeedListActivityTest {

    @Rule
    public ActivityTestRule activityTestRule =
            new ActivityTestRule<>(PublicFeedListActivity.class);

    @Test
    public void testContainsListFeedFragment() {
        PublicFeedListActivity activity = (PublicFeedListActivity) activityTestRule.getActivity();
        Fragment fragment =
                activity.getSupportFragmentManager().findFragmentByTag(
                        PublicFeedListFragment.class.getName());
        assertThat(fragment, is(notNullValue()));
    }

    @Test
    public void testActivityAllViewsDisplayed() {
        onView(withId(R.id.toolbar))
                .check(matches(isDisplayed()));

        onView(withId(R.id.app_bar))
                .check(matches(isDisplayed()));

        onView(withId(R.id.item_list))
                .check(matches(isDisplayed()));

    }
}
