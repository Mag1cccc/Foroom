package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ConversationSteps
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class ConversationTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val steps = ConversationSteps()

    private fun uniqueSuffix(): String =
        UUID.randomUUID()
            .toString()
            .take(Constants.UNIQUE_SUFFIX_LENGTH)

    @Before
    fun startFromLoginScreen() {
        steps.ensureLoggedOut()
    }

    @Test
    fun userASendsMessageInJohnWeekAndSeesItAfterReopeningChat() {
        val message =
            "${Constants.MESSAGE_DRINK} ${uniqueSuffix()}"

        steps
            .signInAs(
                Constants.USER_A,
                Constants.PASSWORD_A
            )
            .openChat(Constants.CHAT_JOHN_WEEK)
            .sendMessage(message)
            .verifyMessageDisplayed(
                message,
                Constants.USER_A
            )
            .closeConversation()
            .openChat(Constants.CHAT_JOHN_WEEK)
            .verifyMessageDisplayed(
                message,
                Constants.USER_A
            )
    }

    @Test
    fun userASendsQuestionInOwnNameChat() {
        val question =
            "${Constants.MESSAGE_QUESTION} ${uniqueSuffix()}"

        steps
            .signInAs(
                Constants.USER_A,
                Constants.PASSWORD_A
            )
            .openChat(Constants.CHAT_NAME)
            .sendMessage(question)
            .verifyMessageDisplayed(
                question,
                Constants.USER_A
            )
    }

    @Test
    fun userBFindsOlderGreetingFromUserAAndRepliesInSharedChat() {
        val suffix = uniqueSuffix()

        val greeting =
            "${Constants.MESSAGE_GREETING} $suffix"

        val reply =
            "${Constants.MESSAGE_REPLY} $suffix"

        steps
            .signInAs(
                Constants.USER_A,
                Constants.PASSWORD_A
            )
            .openChat(Constants.CHAT_SOMETHING)
            .sendMessage(greeting)
            .verifyMessageDisplayed(
                greeting,
                Constants.USER_A
            )

        repeat(Constants.EXTRA_MESSAGES_COUNT) { index ->
            steps.sendMessage(
                "${Constants.MESSAGE_FILLER_PREFIX} ${index + 1} $suffix"
            )
        }

        steps
            .closeConversation()
            .signOut()
            .signInAs(
                Constants.USER_B,
                Constants.PASSWORD_B
            )
            .openChat(Constants.CHAT_SOMETHING)
            .swipeToOlderMessage(
                greeting,
                Constants.USER_A
            )
            .sendMessage(reply)
            .verifyMessageDisplayed(
                reply,
                Constants.USER_B
            )
            .closeConversation()
            .signOut()
            .signInAs(
                Constants.USER_A,
                Constants.PASSWORD_A
            )
            .openChat(Constants.CHAT_SOMETHING)
            .verifyMessageDisplayed(
                reply,
                Constants.USER_B
            )
    }
}