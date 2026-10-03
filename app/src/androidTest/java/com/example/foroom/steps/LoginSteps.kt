package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants
import com.example.foroom.pages.LoginPage
import org.hamcrest.Matchers.allOf

class LoginSteps {

    private val loginPage = LoginPage()

    fun verifyLoginScreen(): LoginSteps {
        onView(loginPage.usernameInputContainer)
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(isDisplayed()))
        onView(loginPage.passwordInputContainer).check(matches(isDisplayed()))
        onView(loginPage.loginButton).check(matches(isDisplayed()))
        onView(loginPage.signUpButton).check(matches(isDisplayed()))
        return this
    }

    fun enterUsername(username: String): LoginSteps {
        loginPage.typeUsername(username)
        return this
    }

    fun enterPassword(password: String): LoginSteps {
        loginPage.typePassword(password)
        return this
    }

    fun clickLogin(): LoginSteps {
        loginPage.tapLogin()
        return this
    }

    fun verifyUsernameError(): LoginSteps {
        onView(allOf(loginPage.usernameError, withText(Constants.USERNAME_ERROR)))
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(isDisplayed()))
        return this
    }

    fun verifyPasswordError(): LoginSteps {
        onView(allOf(loginPage.passwordError, withText(Constants.PASSWORD_ERROR)))
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(isDisplayed()))
        return this
    }

    fun clickSignUp(): RegistrationSteps {
        loginPage.tapSignUp()
        return RegistrationSteps()
    }
}