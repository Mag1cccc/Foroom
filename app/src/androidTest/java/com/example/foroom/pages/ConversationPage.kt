package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.R as DesignSystemR
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ConversationPage {

    val chatHeader = withId(R.id.chatHeaderView)
    val closeButton = withId(R.id.closeButton)
    val messagesList = withId(R.id.messagesRecyclerView)
    val sendButton = withId(R.id.sendMessageButton)

    val messageInputField = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.messageInput))
    )

    fun titleInHeader(title: String): Matcher<View> =
        allOf(
            withId(DesignSystemR.id.chatNameTextView),
            withText(title)
        )

    fun messageText(text: String): Matcher<View> =
        allOf(
            withId(DesignSystemR.id.messageTextView),
            withText(text),
            isDescendantOfA(messagesList)
        )

    fun message(text: String, sender: String): Matcher<View> =
        allOf(
            withId(R.id.messageView),
            isDescendantOfA(messagesList),
            hasDescendant(
                allOf(
                    withId(DesignSystemR.id.messageTextView),
                    withText(text)
                )
            ),
            hasDescendant(
                allOf(
                    withId(DesignSystemR.id.userNameTextView),
                    withText(sender)
                )
            )
        )
}