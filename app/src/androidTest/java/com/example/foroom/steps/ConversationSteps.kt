package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.Helper.isDisplayedNow
import com.example.foroom.Helper.screenBoundsOf
import com.example.foroom.Helper.swiper
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.ConversationPage
import com.example.foroom.pages.LoginPage
import org.hamcrest.Matchers.allOf

class ConversationSteps {

    private val loginPage = LoginPage()
    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatsPage = ChatsPage()
    private val page = ConversationPage()

    fun ensureLoggedOut(): ConversationSteps {
        var loggedIn = false

        waitUntil(
            Constants.WAIT_TIMEOUT,
            "login screen or home screen"
        ) {
            if (isDisplayedNow(loginPage.loginButton)) {
                loggedIn = false
            } else {
                onView(withId(R.id.navBar))
                    .check(matches(isDisplayed()))
                loggedIn = true
            }
        }

        if (loggedIn) {
            profileSteps
                .openProfile()
                .signOut()
        }

        loginSteps.verifyLoginScreen()
        return this
    }

    fun signInAs(
        username: String,
        password: String
    ): ConversationSteps {
        loginSteps
            .login(username, password)
            .verifyHomeScreen()

        return this
    }

    fun signOut(): ConversationSteps {
        profileSteps
            .openProfile()
            .signOut()

        loginSteps.verifyLoginScreen()
        return this
    }

    fun openChat(title: String): ConversationSteps {
        onView(chatsPage.searchChatInput)
            .perform(
                click(),
                replaceText(title),
                closeSoftKeyboard()
            )

        onView(
            allOf(
                chatsPage.chatTitleTextView,
                withText(title)
            )
        )
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(isDisplayed()))

        onView(chatsPage.chatSendMessageButton)
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .perform(click())

        return verifyConversationOpen()
    }

    fun verifyConversationOpen(): ConversationSteps {
        onView(page.chatHeader)
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(isDisplayed()))

        onView(page.sendButton)
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(isDisplayed()))

        onView(page.messagesList)
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(isDisplayed()))

        return this
    }

    fun typeMessage(text: String): ConversationSteps {
        onView(page.messageInputField)
            .perform(
                click(),
                replaceText(text),
                closeSoftKeyboard()
            )

        return this
    }

    fun tapSend(): ConversationSteps {
        onView(page.sendButton)
            .perform(click())

        return this
    }

    fun sendMessage(text: String): ConversationSteps {
        waitUntil(
            Constants.WAIT_TIMEOUT,
            "send button enabled"
        ) {
            onView(page.sendButton)
                .check(matches(isEnabled()))
        }

        typeMessage(text)
        tapSend()

        waitUntil(
            Constants.WAIT_TIMEOUT,
            "input cleared after sending '$text'"
        ) {
            onView(page.messageInputField)
                .check(matches(withText("")))
        }

        return this
    }

    fun verifyMessageDisplayed(
        text: String,
        sender: String? = null
    ): ConversationSteps {

        val matcher = if (sender == null) {
            page.messageText(text)
        } else {
            page.message(text, sender)
        }

        waitUntil(
            Constants.WAIT_TIMEOUT,
            "message '$text'${sender?.let { " from $it" } ?: ""} displayed"
        ) {
            onView(matcher)
                .check(matches(isDisplayed()))
        }

        return this
    }

    fun closeConversation(): ConversationSteps {
        onView(page.closeButton)
            .perform(click())

        onView(chatsPage.chatsRecyclerView)
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(isDisplayed()))

        return this
    }

    fun swipeDownToOlderMessages(): ConversationSteps {
        val bounds = screenBoundsOf(page.messagesList)

        swiper(
            start = bounds.top + (bounds.height() * 0.35).toInt(),
            end = bounds.top + (bounds.height() * 0.90).toInt(),
            delay = 100,
            x = bounds.exactCenterX()
        )

        return this
    }

    fun swipeToOlderMessage(
        text: String,
        sender: String
    ): ConversationSteps {

        val matcher = page.message(text, sender)
        var swipes = 0

        while (!isDisplayedNow(matcher) && swipes < MAX_SWIPES) {
            swipeDownToOlderMessages()
            swipes++
        }

        waitUntil(
            3,
            "message '$text' from $sender after $swipes swipes"
        ) {
            onView(matcher)
                .check(matches(isDisplayed()))
        }

        return this
    }

    private companion object {
        const val MAX_SWIPES = 25
    }
}