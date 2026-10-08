package com.example.foroom.Helper

import android.graphics.Rect
import android.os.SystemClock
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher

fun waitUntil(timeoutSec: Long, description: String, assertion: () -> Unit) {
    val deadline = SystemClock.uptimeMillis() + timeoutSec * 1000
    var lastError: Throwable? = null
    while (SystemClock.uptimeMillis() < deadline) {
        try {
            assertion()
            return
        } catch (e: Throwable) {
            lastError = e
            SystemClock.sleep(100)
        }
    }
    throw AssertionError("Timed out after ${timeoutSec}s waiting for: $description", lastError)
}

fun isDisplayedNow(matcher: Matcher<View>): Boolean = try {
    onView(matcher).check(matches(isDisplayed()))
    true
} catch (e: Throwable) {
    false
}

fun screenBoundsOf(matcher: Matcher<View>): Rect {
    var bounds = Rect()
    onView(matcher).check { view, noViewFoundException ->
        if (view == null) throw noViewFoundException
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        bounds = Rect(location[0], location[1], location[0] + view.width, location[1] + view.height)
    }
    return bounds
}