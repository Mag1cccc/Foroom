package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ProfilePage

class ProfileSteps {

    private val profilePage = ProfilePage()
    private val changePasswordPage = ChangePasswordPage()
    private val changeLanguagePage = ChangeLanguagePage()

    fun openProfile(): ProfileSteps {
        profilePage.tapProfile()
        return this
    }

    fun openChangePassword(): ProfileSteps {
        profilePage.tapChangePassword()
        return this
    }

    fun changePassword(password: String): ProfileSteps {
        changePasswordPage.typeNewPassword(password)
        changePasswordPage.typeRepeatPassword(password)
        changePasswordPage.tapConfirm()
        return this
    }

    fun openChangeLanguage(): ProfileSteps {
        profilePage.tapChangeLanguage()
        return this
    }

    fun selectGeorgian(): ProfileSteps {
        changeLanguagePage.selectGeorgian()
        return this
    }

    fun selectEnglish(): ProfileSteps {
        changeLanguagePage.selectEnglish()
        return this
    }

    fun verifyGeorgianProfile(): ProfileSteps {
        onView(withText("ენის შეცვლა"))
            .check(matches(isDisplayed()))
        return this
    }

    fun verifyEnglishProfile(): ProfileSteps {
        onView(withText("Change Language"))
            .check(matches(isDisplayed()))
        return this
    }

    fun signOut(): ProfileSteps {
        profilePage.tapSignOut()
        return this
    }
}

