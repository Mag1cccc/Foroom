package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignSystemR
import org.hamcrest.Matchers.allOf

class LoginPage {

    val usernameInputContainer = withId(R.id.userNameInput)
    val passwordInputContainer = withId(R.id.passwordInput)

    private val usernameInput = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.userNameInput))
    )

    private val passwordInput = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.passwordInput))
    )

    val usernameError = allOf(
        withId(DesignSystemR.id.descriptionTextView),
        isDescendantOfA(withId(R.id.userNameInput))
    )

    val passwordError = allOf(
        withId(DesignSystemR.id.descriptionTextView),
        isDescendantOfA(withId(R.id.passwordInput))
    )

    val loginButton = withId(R.id.logInButton)
    val signUpButton = withId(R.id.signUpButton)

    fun typeUsername(username: String) {
        onView(usernameInput).perform(click(), replaceText(username), closeSoftKeyboard())
    }

    fun typePassword(password: String) {
        onView(passwordInput).perform(click(), replaceText(password), closeSoftKeyboard())
    }

    fun tapLogin() {
        onView(loginButton).perform(click())
    }

    fun tapSignUp() {
        onView(signUpButton).perform(click())
    }
}