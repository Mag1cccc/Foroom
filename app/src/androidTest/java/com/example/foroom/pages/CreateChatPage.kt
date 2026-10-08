package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignSystemR
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.Helper.withIndex
import com.example.foroom.data.Constants
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith

class CreateChatPage {

    private val chatNameInput = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.chatNameInput))
    )

    private val chatImageItem = allOf(
        withClassName(endsWith("ImageChooserItemView")),
        isDescendantOfA(withId(R.id.chatImageChooser))
    )

    val createChatNavigation = withId(R.id.homeNavigationCreateChat)
    val closeButton = withId(R.id.closeButton)
    val createChatButton = withId(R.id.createChatButton)

    fun openCreateChat() {
        onView(createChatNavigation).perform(click())
    }

    fun typeChatName(name: String) {
        onView(chatNameInput)
            .perform(
                click(),
                replaceText(name),
                closeSoftKeyboard()
            )
    }

    fun selectImage() {
        onView(withId(R.id.chatImageChooser))
            .waitUntilVisible(Constants.WAIT_TIMEOUT)

        onView(withIndex(chatImageItem, 1))
            .perform(click())
    }

    fun tapCreateChat() {
        onView(createChatButton).perform(click())
    }

    fun tapClose() {
        onView(closeButton).perform(click())
    }
}






