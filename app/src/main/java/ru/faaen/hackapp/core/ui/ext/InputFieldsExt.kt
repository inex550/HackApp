package ru.faaen.hackapp.core.ui.ext

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.enableError(error: String?) {
    isErrorEnabled = error != null
    setError(error)
}