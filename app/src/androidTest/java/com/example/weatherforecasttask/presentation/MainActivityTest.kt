package com.example.weatherforecasttask.presentation


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.weatherforecasttask.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val actionMenuItemView = onView(
            allOf(
                withId(R.id.action_search), withContentDescription("Search"),
                childAtPosition(
                    childAtPosition(
                        withId(androidx.constraintlayout.widget.R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        val searchAutoComplete = onView(
            allOf(
                withId(androidx.constraintlayout.widget.R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(androidx.constraintlayout.widget.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.constraintlayout.widget.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(replaceText("cair"), closeSoftKeyboard())

        val searchAutoComplete2 = onView(
            allOf(
                withId(androidx.constraintlayout.widget.R.id.search_src_text), withText("cair"),
                childAtPosition(
                    allOf(
                        withId(androidx.constraintlayout.widget.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.constraintlayout.widget.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete2.perform(click())

        val searchAutoComplete3 = onView(
            allOf(
                withId(androidx.constraintlayout.widget.R.id.search_src_text), withText("cair"),
                childAtPosition(
                    allOf(
                        withId(androidx.constraintlayout.widget.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.constraintlayout.widget.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete3.perform(replaceText("cairo"))

        val searchAutoComplete4 = onView(
            allOf(
                withId(androidx.constraintlayout.widget.R.id.search_src_text), withText("cairo"),
                childAtPosition(
                    allOf(
                        withId(androidx.constraintlayout.widget.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.constraintlayout.widget.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete4.perform(closeSoftKeyboard())

        val searchAutoComplete5 = onView(
            allOf(
                withId(androidx.constraintlayout.widget.R.id.search_src_text), withText("cairo"),
                childAtPosition(
                    allOf(
                        withId(androidx.constraintlayout.widget.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.constraintlayout.widget.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete5.perform(pressImeActionButton())

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Collapse"),
                childAtPosition(
                    allOf(
                        withId(androidx.constraintlayout.widget.R.id.action_bar),
                        childAtPosition(
                            withId(androidx.constraintlayout.widget.R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val actionMenuItemView2 = onView(
            allOf(
                withId(R.id.action_search), withContentDescription("Search"),
                childAtPosition(
                    childAtPosition(
                        withId(androidx.constraintlayout.widget.R.id.action_bar),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView2.perform(click())

        val searchAutoComplete6 = onView(
            allOf(
                withId(androidx.constraintlayout.widget.R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(androidx.constraintlayout.widget.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.constraintlayout.widget.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete6.perform(replaceText("Alexandria "), closeSoftKeyboard())

        val searchAutoComplete7 = onView(
            allOf(
                withId(androidx.constraintlayout.widget.R.id.search_src_text),
                withText("Alexandria "),
                childAtPosition(
                    allOf(
                        withId(androidx.constraintlayout.widget.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.constraintlayout.widget.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete7.perform(pressImeActionButton())

        val tabView = onView(
            allOf(
                withContentDescription("Tomorrow"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_layout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(click())

        val tabView2 = onView(
            allOf(
                withContentDescription("Latter"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_layout),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        tabView2.perform(click())

        val appCompatImageButton2 = onView(
            allOf(
                withContentDescription("Collapse"),
                childAtPosition(
                    allOf(
                        withId(androidx.constraintlayout.widget.R.id.action_bar),
                        childAtPosition(
                            withId(androidx.constraintlayout.widget.R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
