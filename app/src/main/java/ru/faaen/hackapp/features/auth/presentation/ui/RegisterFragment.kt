package ru.faaen.hackapp.features.auth.presentation.ui

import com.github.terrakok.cicerone.Screen
import dagger.hilt.android.AndroidEntryPoint
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.ext.SpecError
import ru.faaen.hackapp.core.ui.base.BaseMvvmFragment
import ru.faaen.hackapp.core.ui.ext.enableError
import ru.faaen.hackapp.core.ui.ext.renderIn
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.core.ui.ext.viewModels
import ru.faaen.hackapp.databinding.FragmentRegisterBinding
import ru.faaen.hackapp.features.auth.presentation.validators.AuthFields
import ru.faaen.hackapp.features.auth.presentation.vm.AuthViewAction
import ru.faaen.hackapp.features.auth.presentation.vm.AuthViewModel
import ru.faaen.hackapp.features.auth.presentation.vm.AuthViewState
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment: BaseMvvmFragment<AuthViewState, AuthViewAction, AuthViewModel>(
    layoutResId = R.layout.fragment_register
) {
    private val binding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)

    @Inject
    lateinit var viewModelFactory: AuthViewModel.Factory

    override val viewModel: AuthViewModel by viewModels {
        viewModelFactory.create(router = requireLocalRouter(), nextScreen = nextScreen)
    }

    var nextScreen: Screen? = null

    override fun setupUi() {
        with(binding) {
            setupRegisterBtn()

            loginTv.setOnClickListener {
                viewModel.navToLogin()
            }
        }
    }

    private fun FragmentRegisterBinding.setupRegisterBtn() {
        registerBtn.setOnClickListener {
            val fio: String = fioEt.text.toString()
            val email: String = emailEt.text.toString()
            val password: String = passwordEt.text.toString()

            viewModel.register(fio, email, password)
        }
    }

    override fun setupRender() {
        with(viewModel.uiState) {
            renderIn(viewLifecycle, { it.isLoading }, ::renderLoading)
        }
    }

    private fun renderLoading(isLoading: Boolean) {
        binding.registerBtn.isEnabled = !isLoading
    }

    override fun processAction(action: AuthViewAction) {
        when (action) {
            is AuthViewAction.ShowSnackbarError -> {
                showSnackbarError(action.message)
            }
            is AuthViewAction.ShowSpec -> {
                showSpecError(action.spec)
            }
        }
    }

    private fun showSpecError(spec: SpecError) {
        with(binding) {
            fioIl.enableError(spec[AuthFields.FIO.field])
            emailIl.enableError(spec[AuthFields.EMAIL.field])
            passwordIl.enableError(spec[AuthFields.PASSWORD.field])
        }
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}