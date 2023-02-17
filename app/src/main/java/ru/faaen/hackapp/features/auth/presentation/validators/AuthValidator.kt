package ru.faaen.hackapp.features.auth.presentation.validators

import android.content.Context
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.ext.isValidEmail
import javax.inject.Inject

class AuthValidator @Inject constructor(
    private val context: Context
) {
    fun validFio(fio: String): String? {
        val words = fio.split(" ");
        if (words.size != 2 && words.size != 3)
            return context.getString(R.string.incorrect_email_error)
        return null
    }

    fun validEmail(email: String): String? {
        if (!email.isValidEmail)
            return context.getString(R.string.incorrect_email_error)
        return null
    }

    fun validPassword(password: String): String? {
        if (password.length < PASSWORD_LENGTH)
            return context.getString(R.string.short_password_error)
        return null
    }

    companion object {
        private const val PASSWORD_LENGTH = 6
    }
}