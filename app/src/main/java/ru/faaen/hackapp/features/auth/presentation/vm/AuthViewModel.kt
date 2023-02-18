package ru.faaen.hackapp.features.auth.presentation.vm

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.faaen.hackapp.core.common.ext.*
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.ui.base.BaseViewModel
import ru.faaen.hackapp.features.auth.data.repo.AuthRepository
import ru.faaen.hackapp.features.auth.presentation.validators.AuthFields
import ru.faaen.hackapp.features.auth.presentation.validators.AuthValidator

class AuthViewModel @AssistedInject constructor(
    @Assisted private val router: Router,
    @Assisted private val nextScreen: Screen?,
    private val authValidator: AuthValidator,
    private val repository: AuthRepository,
) : BaseViewModel<AuthViewState, AuthViewAction>(
    initialState = AuthViewState()
) {
    override fun provideDefaultMessageError(message: String): AuthViewAction {
        return AuthViewAction.ShowSnackbarError(message)
    }

    override fun provideDefaultSpecError(spec: SpecError): AuthViewAction {
        return AuthViewAction.ShowSpec(spec)
    }

    fun navToLogin() {
        router.replaceScreen(Screens.loginScreen(nextScreen))
    }

    fun navToRegister() {
        router.replaceScreen(Screens.registerScreen(nextScreen))
    }

    fun signIn(email: String, password: String) {
        val validResult = EditableSpecError().apply {
            setError(AuthFields.EMAIL.field, authValidator.validEmail(email))
            setError(AuthFields.PASSWORD.field, authValidator.validPassword(password))
        }

        if (validResult.hasErrors()) {
            performAction(provideDefaultSpecError(validResult))
            return
        }

        performAction(provideDefaultSpecError(emptySpec()))

        launchIOCoroutine(
            finallyListener = { updateState { copy(isLoading = false) } }
        ) {
            updateState { copy(isLoading = true) }

            repository.login(email, password)

            val newScreen = nextScreen ?: Screens.homeScreen()
            router.replaceScreen(newScreen)
        }
    }

    fun register(fio: String, email: String, password: String) {
        val spec = EditableSpecError().apply {
            setError(AuthFields.FIO.field, authValidator.validFio(fio))
            setError(AuthFields.EMAIL.field, authValidator.validEmail(email))
            setError(AuthFields.PASSWORD.field, authValidator.validPassword(password))
        }

        if (spec.hasErrors()) {
            performAction(provideDefaultSpecError(spec))
            return
        }

        performAction(provideDefaultSpecError(emptySpec()))

        launchIOCoroutine(
            finallyListener = { updateState { copy(isLoading = false) } }
        ) {
            updateState { copy(isLoading = true) }

            repository.register(fio, email, password)

            val newScreen = nextScreen ?: Screens.homeScreen()
            router.replaceScreen(newScreen)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted router: Router,
            @Assisted nextScreen: Screen?,
        ): AuthViewModel
    }
}