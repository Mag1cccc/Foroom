package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignSystemR
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.Helper.withIndex
import com.example.foroom.data.Constants
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith

class RegistrationPage {

    val usernameInputContainer = withId(R.id.userNameInput)
    val passwordInputContainer = withId(R.id.passwordInput)
    val repeatPasswordInputContainer = withId(R.id.repeatPasswordInput)

    private val usernameInput = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.userNameInput))
    )

    private val passwordInput = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.passwordInput))
    )

    private val repeatPasswordInput = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.repeatPasswordInput))
    )

    val listView = withId(R.id.listView)

    private val avatarItem = allOf(
        withClassName(endsWith("ImageChooserItemView")),
        isDescendantOfA(withId(R.id.listView))
    )

    val signUpButton = withId(R.id.signUpButton)

    val navBar = withId(R.id.navBar)

    fun typeUsername(username: String) {
        onView(usernameInput).perform(click(), replaceText(username), closeSoftKeyboard())
    }

    fun typePassword(password: String) {
        onView(passwordInput).perform(click(), replaceText(password), closeSoftKeyboard())
    }

    fun typeRepeatPassword(password: String) {
        onView(repeatPasswordInput).perform(click(), replaceText(password), closeSoftKeyboard())
    }

    fun selectFirstAvatar() {
        onView(listView).waitUntilVisible(Constants.WAIT_TIMEOUT)
        onView(withIndex(avatarItem, 0)).perform(click())
    }

    fun tapSignUp() {
        onView(signUpButton).perform(click())
    }
}