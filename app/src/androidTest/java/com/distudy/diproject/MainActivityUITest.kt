package com.distudy.diproject

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
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
import com.distudy.diproject.CustomMatchers.Companion.withItemCount
import com.distudy.diproject.CustomMatchers.Companion.withTextViewContent
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader


@RunWith(AndroidJUnit4::class)
@LargeTest
//@LargeTest 주석은 필수는 아니지만 UI 테스트에서 권장됩니다. 테스트 기간이 1 초 이상일 수 있음을 나타냅니다.
class MainActivityUITest {
    //https://developer.android.com/training/testing/espresso/intents?hl=ko
    //Activity Scenario
    @get: Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    var intentsTestRule = IntentsTestRule(MainActivity::class.java)

    lateinit var fakeUri: Uri

    private val mockWebServer = MockWebServer()


    @Before
    fun setup() {
        fakeUri = Uri.parse("myScheme://info?code=%2fsomething")
        mockWebServer.start(8080)
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun recyclerView_onLaunched() {
        onView(withId(R.id.user_list_recyclerview)).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerView_shouldShowElements() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(getStringFromFile("sample.json")))
        SystemClock.sleep(2000)
        onView(withId(R.id.user_list_recyclerview)).check(matches(withItemCount(30)))
        onView(withId(R.id.user_list_recyclerview)).check(matches(withTextViewContent("mojombo")))
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

    private fun getJsonContent(fileName: String): String {
        return InputStreamReader(
            this.javaClass.classLoader?.getResourceAsStream(fileName)
        ).use { it.readText() }
    }

    internal fun getStringFromFile(filePath: String, debug: Boolean = false): String {
        val classLoader = this::class.java.classLoader
        if (classLoader != null) {
            try {
                val inputString = classLoader.getResourceAsStream(filePath).bufferedReader().use { it.readText() }
                if (debug) println("Output from inputfile is: $inputString")
                return inputString
            } catch (e: FileNotFoundException) {
                println("Could not find the specified file: $filePath")
                throw e
            }
        } else {
            throw IllegalStateException(
                """Classloader is null. Can't open an inputstream for the specified file: $filePath without it."""
            )
        }
    }


}