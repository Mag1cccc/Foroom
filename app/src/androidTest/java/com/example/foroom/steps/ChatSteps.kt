
package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
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
        chatsPage.searchChat(name)
        return this
    }

    fun verifyChatInList(name: String): ChatSteps {
        onView(chatsPage.chatTitleTextView)
            .check(matches(withText(name)))
            .check(matches(isDisplayed()))

        return this
    }
}






