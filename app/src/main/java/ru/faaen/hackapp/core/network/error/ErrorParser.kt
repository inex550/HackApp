package ru.faaen.hackapp.core.network.error

import com.google.gson.GsonBuilder
import retrofit2.HttpException
import ru.faaen.hackapp.App
import ru.faaen.hackapp.R
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

object ErrorParser {

    private val gson = GsonBuilder().create()

    fun parseThrowable(t: Throwable): ErrorModel {
        return when(t) {
            is HttpException -> {
                val response = t.response()?.errorBody()?.string()
                val errorModel = parseCustomError(response)

                errorModel ?: unknownError()
            }
            is TimeoutException -> ErrorModel(App.context.getString(R.string.timeout_error))
            is SSLHandshakeException -> ErrorModel(App.context.getString(R.string.ssl_handshake_error))
            is NotImplementedError -> ErrorModel(t.message ?: App.context.getString(R.string.unknown_error))
            else -> unknownError()
        }
    }

    private fun unknownError() = ErrorModel(App.context.getString(R.string.unknown_error))

    private fun parseCustomError(error: String?): ErrorModel? {
        return try {
            gson.fromJson(error, ErrorModel::class.java)
        } catch(ex: Exception) {
            null
        }
    }
}