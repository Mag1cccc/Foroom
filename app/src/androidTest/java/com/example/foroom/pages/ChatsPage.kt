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

class ChatsPage {

    private val searchChatInput = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.searchChatInput))
    )

    val chatsRecyclerView = withId(R.id.chatsRecyclerView)

    val chatTitleTextView = allOf(
        withId(DesignSystemR.id.chatTitleTextView),
        isDescendantOfA(withId(R.id.chatsRecyclerView))
    )

    fun searchChat(chatName: String) {
        onView(searchChatInput)
            .perform(
                click(),
                replaceText(chatName),
                closeSoftKeyboard()
            )
    }
}