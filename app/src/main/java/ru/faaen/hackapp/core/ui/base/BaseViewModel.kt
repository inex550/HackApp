package ru.faaen.hackapp.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.faaen.hackapp.core.network.error.ErrorModel
import ru.faaen.hackapp.core.network.error.ErrorParser
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import ru.faaen.hackapp.core.common.ext.SpecError
import timber.log.Timber

abstract class BaseViewModel<VS: BaseViewState, VA: BaseViewAction>(
    initialState: VS,
): ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<VS> = _uiState

    private val _action = MutableSharedFlow<VA>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val action: SharedFlow<VA> = _action

    fun updateState(newState: VS.() -> VS) {
        _uiState.value = _uiState.value.newState()
    }

    fun performAction(action: VA) {
        _action.tryEmit(action)
    }

    open fun provideDefaultMessageError(message: String): VA? = null

    open fun provideDefaultSpecError(spec: SpecError): VA? = null

    protected fun launchCoroutine(
        errorListener: ErrorListener = ErrorListener { true },
        finallyListener: FinallyListener? = null,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                block()
            } catch (e: CancellationException) {
                Timber.d("Coroutine $this cancelled")
            } catch (t: Throwable) {
                Timber.e(t)

                val error = ErrorParser.parseThrowable(t)
                val listenerResult = errorListener.proceedError(error)

                if (listenerResult) {
                    val errorAction = null

                    errorAction?.let {
                        performAction(it)
                    } ?: throw NotImplementedError("\"provideDefaultError\" for ${this@BaseViewModel::class} class is not implemented")
                }
            } finally {
                finallyListener?.invoke()
            }
        }
    }

    protected fun launchIOCoroutine(
        errorListener: ErrorListener = ErrorListener { true },
        finallyListener: FinallyListener? = null,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return launchCoroutine(
            errorListener = errorListener,
            finallyListener = finallyListener
        ) {
            withContext(Dispatchers.IO, block)
        }
    }

    protected fun launchDefaultCoroutine(
        errorListener: ErrorListener = ErrorListener { true },
        finallyListener: FinallyListener? = null,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return launchCoroutine(
            errorListener = errorListener,
            finallyListener = finallyListener
        ) {
            withContext(Dispatchers.Default, block)
        }
    }

    fun interface ErrorListener {
        fun proceedError(error: ErrorModel): Boolean
    }

    fun interface FinallyListener {
        fun invoke()
    }
}