package ru.faaen.hackapp.features.auth.presentation.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Screen
import dagger.hilt.android.AndroidEntryPoint
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.ext.SpecError
import ru.faaen.hackapp.core.navigation.navigator.FragmentTransactionHandler
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.base.BaseMvvmFragment
import ru.faaen.hackapp.core.ui.ext.enableError
import ru.faaen.hackapp.core.ui.ext.renderIn
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.core.ui.ext.viewModels
import ru.faaen.hackapp.databinding.FragmentLoginBinding
import ru.faaen.hackapp.features.auth.presentation.validators.AuthFields
import ru.faaen.hackapp.features.auth.presentation.vm.AuthViewAction
import ru.faaen.hackapp.features.auth.presentation.vm.AuthViewModel
import ru.faaen.hackapp.features.auth.presentation.vm.AuthViewState
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment: BaseMvvmFragment<AuthViewState, AuthViewAction, AuthViewModel>(
    layoutResId = R.layout.fragment_login
) {
    @Inject
    lateinit var viewModelFactory: AuthViewModel.Factory

    private val binding by viewBinding(FragmentLoginBinding::bind)

    var nextScreen: Screen? = null

    override val viewModel: AuthViewModel by viewModels {
        viewModelFactory.create(requireLocalRouter(), nextScreen)
    }

    override fun setupUi() {
        with(binding) {
            setupLoginBtn()

            registerTv.setOnClickListener {
                viewModel.navToRegister()
            }
        }
    }

    private fun FragmentLoginBinding.setupLoginBtn() {
        loginBtn.setOnClickListener {
            val email = emailEt.text.toString()
            val password = passwordEt.text.toString()
            viewModel.signIn(email, password)
        }
    }

    override fun setupRender() {
        with(viewModel.uiState) {
            renderIn(viewLifecycle, { it.isLoading }, ::renderLoading)
        }
    }

    private fun renderLoading(isLoading: Boolean) {
        binding.loginBtn.isEnabled = !isLoading
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
            emailIl.enableError(spec[AuthFields.EMAIL.field])
            passwordIl.enableError(spec[AuthFields.PASSWORD.field])
        }
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}
