package google.android.svape.codelab_dogglers

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import google.android.svape.codelab_dogglers.BaseTest.DrawableMatcher.hashItemCount
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class VerticalListTest : BaseTest() {

    @get:Rule
    var scenarioRule: ActivityScenarioRule<VerticalListActivity> =
        ActivityScenarioRule(VerticalListActivity::class.java)


    @Test
    fun scrolling_element_list_at_first_item() {
        checkFirstPosition()
    }


    @Test
    fun scrolling_element_list_at_last_item() {
        onView(withId(R.id.vertical_recycler_view))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(lastPosition))
        onView(withText("Winslow Homer")).check(matches(isDisplayed()))
    }

    @Test
    fun vertical_scrolling() {
        onView(withId(R.id.vertical_recycler_view))
            .perform(swipeUp())
        onView(withText("Sandro Botticelli")).check(matches(isDisplayed()))
    }

    @Test
    fun recycler_view_item_count() {
        onView(withId(R.id.vertical_recycler_view)).check(hashItemCount(24))
    }

}