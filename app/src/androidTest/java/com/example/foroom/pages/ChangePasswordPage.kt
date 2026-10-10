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

class ChangePasswordPage {

    private val newPasswordInput = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.passwordInput))
    )

    private val repeatPasswordInput = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.repeatPasswordInput))
    )

    val actionButton = withId(DesignSystemR.id.actionButton)

    fun typeNewPassword(password: String) {
        onView(newPasswordInput)
            .perform(click(), replaceText(password), closeSoftKeyboard())
    }

    fun typeRepeatPassword(password: String) {
        onView(repeatPasswordInput)
            .perform(click(), replaceText(password), closeSoftKeyboard())
    }

    fun tapConfirm() {
        onView(actionButton).perform(click())
    }
}

