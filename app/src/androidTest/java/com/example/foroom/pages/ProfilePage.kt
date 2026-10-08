package com.example.foroom.pages


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R

class ProfilePage {

    val profileNavigation = withId(R.id.homeNavigationProfile)

    val changePasswordItem = withId(R.id.changePasswordItem)
    val changeLanguageItem = withId(R.id.changeLanguageItem)
    val signOutItem = withId(R.id.signOutItem)

    fun tapProfile() {
        onView(profileNavigation).perform(click())
    }

    fun tapChangePassword() {
        onView(changePasswordItem).perform(click())
    }

    fun tapChangeLanguage() {
        onView(changeLanguageItem).perform(click())
    }

    fun tapSignOut() {
        onView(signOutItem).perform(click())
    }
}
