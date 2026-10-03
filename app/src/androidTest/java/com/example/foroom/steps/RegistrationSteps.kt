package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps {

    private val registrationPage = RegistrationPage()

    fun verifyRegistrationScreen(): RegistrationSteps {
        onView(registrationPage.usernameInputContainer)
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(isDisplayed()))
        onView(registrationPage.passwordInputContainer).check(matches(isDisplayed()))
        onView(registrationPage.repeatPasswordInputContainer).check(matches(isDisplayed()))
        onView(registrationPage.listView)
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(isDisplayed()))
        onView(registrationPage.signUpButton).check(matches(isDisplayed()))
        return this
    }

    fun enterUsername(username: String): RegistrationSteps {
        registrationPage.typeUsername(username)
        return this
    }

    fun enterPassword(password: String): RegistrationSteps {
        registrationPage.typePassword(password)
        return this
    }

    fun enterRepeatPassword(password: String): RegistrationSteps {
        registrationPage.typeRepeatPassword(password)
        return this
    }

    fun selectAvatar(): RegistrationSteps {
        registrationPage.selectFirstAvatar()
        return this
    }

    fun clickSignUp(): RegistrationSteps {
        registrationPage.tapSignUp()
        return this
    }

    fun verifyHomeScreen(): RegistrationSteps {
        onView(registrationPage.navBar)
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(isDisplayed()))
        return this
    }
}