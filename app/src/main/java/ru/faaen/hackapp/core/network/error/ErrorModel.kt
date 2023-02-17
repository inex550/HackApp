package ru.faaen.hackapp.core.network.error

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorModel(
    val message: String?
): Parcelable
