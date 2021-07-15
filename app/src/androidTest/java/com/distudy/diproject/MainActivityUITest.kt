package com.distudy.diproject

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import android.view.WindowManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityUITest {
    //https://developer.android.com/training/testing/espresso/intents?hl=ko
    //Activity Scenario
    @get: Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    var intentsTestRule = IntentsTestRule(MainActivity::class.java)

    lateinit var fakeUri: Uri

    @Before
    fun setup() {
        fakeUri = Uri.parse("myScheme://info?code=%2fsomething")
    }

    @Test
    fun if_toolbar_menu_clicked_new_activity_launched() {
        //toolbar menu click
        onView(withId(R.id.menu_settings))
            .perform(click())

        //test that send Intent
        intended(
            allOf(
                IntentMatchers.hasAction(Intent.ACTION_VIEW)
            )
        )
    }

    @Test
    fun if_activity_finished_intent_receive() {
        val intent = Intent()
        intent.data = fakeUri
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, intent)

        intending(toPackage("com.distudy.diproject")).respondWith(result)

        onView(withId(R.id.menu_settings)).perform(click())

        onView(withId(R.id.token_test_text)).check(matches(withText("")))
    }

    /**
     * 분석 필요. 배껴
     */
    open class ToastMatcher(private val message: String) : TypeSafeMatcher<Root?>() {
        override fun matchesSafely(root: Root?): Boolean {
            root ?: return false
            val type: Int = root.windowLayoutParams.get().type
            if (type == WindowManager.LayoutParams.TYPE_TOAST) {
                val windowToken: IBinder = root.decorView.windowToken
                val appToken: IBinder = root.decorView.applicationWindowToken
                if (windowToken === appToken) {
                    // means this window isn't contained by any other windows.
                    return true
                }
            }
            return false
        }

        override fun describeTo(description: org.hamcrest.Description?) {
            description?.appendText(message)
        }

    }

}