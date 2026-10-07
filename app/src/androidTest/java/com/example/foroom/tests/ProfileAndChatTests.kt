package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    private lateinit var testUsername: String
    private val testPassword = Constants.PASSWORD

    @Before
    fun setUpTestUser() {
        testUsername = "test_${UUID.randomUUID().toString().take(8)}"

        loginSteps
            .verifyLoginScreen()
            .clickSignUp()
            .verifyRegistrationScreen()
            .enterUsername(testUsername)
            .enterPassword(testPassword)
            .enterRepeatPassword(testPassword)
            .selectAvatar()
            .clickSignUp()
            .verifyHomeScreen()

        profileSteps
            .openProfile()
            .signOut()

        loginSteps
            .verifyLoginScreen()
    }

    @Test
    fun changePasswordAndVerifyLogin() {
        loginSteps
            .login(testUsername, testPassword)
            .verifyHomeScreen()

        profileSteps
            .openProfile()
            .openChangePassword()
            .changePassword(Constants.NEW_PASSWORD)

        loginSteps
            .verifyLoginScreen()
            .login(testUsername, Constants.NEW_PASSWORD)
            .verifyHomeScreen()
    }

    @Test
    fun changeLanguageGeorgianEnglishAndBack() {
        loginSteps
            .login(testUsername, testPassword)
            .verifyHomeScreen()

        profileSteps
            .openProfile()
            .openChangeLanguage()
            .selectGeorgian()
            .verifyGeorgianProfile()

        profileSteps
            .openChangeLanguage()
            .selectEnglish()
            .verifyEnglishProfile()

        profileSteps
            .openChangeLanguage()
            .selectGeorgian()
            .verifyGeorgianProfile()
    }

    @Test
    fun createChatAndFindItInChatList() {
        loginSteps
            .login(testUsername, testPassword)
            .verifyHomeScreen()

        chatSteps
            .openCreateChat()
            .enterChatName(Constants.CHAT_NAME)
            .selectChatImage()
            .createChat()
            .verifyCreatedChat(Constants.CHAT_NAME)
            .closeChat()
            .searchChat(Constants.CHAT_NAME)
            .verifyChatInList(Constants.CHAT_NAME)
    }
}