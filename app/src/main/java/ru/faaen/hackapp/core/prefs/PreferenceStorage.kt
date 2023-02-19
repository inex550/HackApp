package ru.faaen.hackapp.core.prefs

import android.content.SharedPreferences
import ru.faaen.hackapp.core.prefs.utils.StringPreference
import javax.inject.Inject

class PreferenceStorage @Inject constructor(
    private val prefs: SharedPreferences
) {
    var username: String? by StringPreference(prefs, KEY_USERNAME, null)

    fun isAuthorized(): Boolean {
        return username != null
    }

    companion object {
        private const val KEY_TOKEN = "KEY_TOKEN"
        private const val KEY_USERNAME = "KEY_USERNAME"
    }
}
