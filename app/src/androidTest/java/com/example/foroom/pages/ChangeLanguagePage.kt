package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R

class ChangeLanguagePage {

    private val languageButtonGeo = withId(R.id.languageButtonGeo)
    private val languageButtonEng = withId(R.id.languageButtonEng)

    fun selectGeorgian() {
        onView(languageButtonGeo).perform(click())
    }

    fun selectEnglish() {
        onView(languageButtonEng).perform(click())
    }
}
