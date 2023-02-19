package ru.faaen.hackapp.features.splash

import android.annotation.SuppressLint
import androidx.core.view.postDelayed
import dagger.hilt.android.AndroidEntryPoint
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.prefs.PreferenceStorage
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.databinding.FragmentSplashBinding
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen: BaseFragment(
    layoutResId = R.layout.fragment_splash
) {
    private val binding by viewBinding(FragmentSplashBinding::bind)

    @Inject
    lateinit var prefs: PreferenceStorage

    override fun setupUi() {
        with(binding) {
            val name = prefs.username?.split(" ")?.firstOrNull().orEmpty()
            goodMorningTv.text = requireContext().getString(R.string.good_morning, name)

            root.postDelayed(SPLASH_DELAY) {
                localRouter?.replaceScreen(Screens.flowScreen())
            }
        }
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }

    companion object {
        private const val SPLASH_DELAY = 2000L
    }
}