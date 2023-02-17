package ru.faaen.hackapp.core.common.ext

import android.util.Patterns
import android.webkit.URLUtil

val String.isHttpUrl: Boolean
    get() = URLUtil.isHttpsUrl(this) || URLUtil.isHttpUrl(this)

val String.isValidEmail: Boolean
    get() = Patterns.EMAIL_ADDRESS.matcher(this).matches()