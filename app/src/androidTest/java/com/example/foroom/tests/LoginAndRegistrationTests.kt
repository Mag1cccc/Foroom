package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.LoginSteps
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()

    @Test
    fun invalidPasswordLoginTest() {
        loginSteps
            .verifyLoginScreen()
            .enterUsername(Constants.VALID_USERNAME)
            .enterPassword(Constants.INVALID_PASSWORD)
            .clickLogin()
            .verifyPasswordError()
    }

    @Test
    fun invalidUsernameLoginTest() {
        loginSteps
            .verifyLoginScreen()
            .enterUsername(Constants.NON_EXISTING_USERNAME)
            .enterPassword(Constants.INVALID_PASSWORD)
            .clickLogin()
            .verifyUsernameError()
            .verifyPasswordError()
    }

    @Test
    fun successfulRegistrationTest() {
        val username = "automation_${UUID.randomUUID().toString().take(8)}"
        val password = "StrongPassword@1"

        loginSteps
            .verifyLoginScreen()
            .clickSignUp()
            .verifyRegistrationScreen()
            .enterUsername(username)
            .enterPassword(password)
            .enterRepeatPassword(password)
            .selectAvatar()
            .clickSignUp()
            .verifyHomeScreen()
    }
}