package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

class ChatSteps {

    private val createChatPage = CreateChatPage()
    private val chatsPage = ChatsPage()

    fun openCreateChat(): ChatSteps {
        createChatPage.openCreateChat()
        return this
    }

    fun enterChatName(name: String): ChatSteps {
        createChatPage.typeChatName(name)
        return this
    }

    fun selectChatImage(): ChatSteps {
        createChatPage.selectImage()
        return this
    }

    fun createChat(): ChatSteps {
        createChatPage.tapCreateChat()
        return this
    }

    fun verifyCreatedChat(name: String): ChatSteps {
        onView(withText(name))
            .check(matches(isDisplayed()))
        return this
    }

    fun closeChat(): ChatSteps {
        createChatPage.tapClose()
        return this
    }

    fun searchChat(name: String): ChatSteps {
        onView(chatsPage.searchChatInput)
            .perform(
                click(),
                replaceText(name),
                closeSoftKeyboard()
            )

        return this
    }

    fun verifyChatInList(name: String): ChatSteps {
        onView(chatsPage.chatTitleTextView)
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .check(matches(withText(name)))
            .check(matches(isDisplayed()))

        return this
    }

    fun openChat(name: String): ChatSteps {
        verifyChatInList(name)

        onView(chatsPage.chatSendMessageButton)
            .waitUntilVisible(Constants.WAIT_TIMEOUT)
            .perform(click())

        return this
    }
}





