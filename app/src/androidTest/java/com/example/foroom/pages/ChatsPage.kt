package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignSystemR
import org.hamcrest.Matchers.allOf

class ChatsPage {

    val searchChatInput = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.searchChatInput))
    )

    val chatsRecyclerView = withId(R.id.chatsRecyclerView)

    val chatTitleTextView = allOf(
        withId(DesignSystemR.id.chatTitleTextView),
        isDescendantOfA(chatsRecyclerView)
    )

    val chatSendMessageButton = allOf(
        withId(R.id.sendMessageButton),
        isDescendantOfA(chatsRecyclerView)
    )
}