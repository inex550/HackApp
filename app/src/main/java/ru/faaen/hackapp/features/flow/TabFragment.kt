package ru.faaen.hackapp.features.flow

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.hilt.android.AndroidEntryPoint
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.utils.uiLazy
import ru.faaen.hackapp.core.navigation.RouterProvider
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.navigation.navigator.HCNavigator
import ru.faaen.hackapp.core.navigation.sub.CiceroneHolder
import ru.faaen.hackapp.core.prefs.PreferenceStorage
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.withArgs

import ru.faaen.hackapp.features.profile.ProfileFragment
import javax.inject.Inject

@AndroidEntryPoint
class TabFragment: BaseFragment(
    layoutResId = R.layout.fragment_tab
), RouterProvider {
    private val screenTag: String by uiLazy {
        requireArguments().getString(ARG_TAG).orEmpty()
    }

    private val cicerone: Cicerone<Router> by uiLazy {
        CiceroneHolder.getCicerone(screenTag)
    }

    private val router: Router by uiLazy {
        cicerone.router
    }

    private val navigatorHolder: NavigatorHolder by uiLazy {
        cicerone.getNavigatorHolder()
    }

    @Inject
    lateinit var prefs: PreferenceStorage

    private val navigator: HCNavigator by uiLazy {
        object : HCNavigator(requireActivity(), R.id.tab_container, childFragmentManager) {
            override fun setupFragmentTransaction(
                screen: FragmentScreen,
                fragmentTransaction: FragmentTransaction,
                currentFragment: Fragment?,
                nextFragment: Fragment
            ) {
                fragmentTransaction.setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out,
                )
            }
        }
    }

    override fun setupUi() {
        if (childFragmentManager.findFragmentById(R.id.tab_container) == null) {
            val screen = screenByTag(screenTag)
            router.newRootScreen(screen)
        }
    }

    private fun screenByTag(tag: String): Screen {
        return when(tag) {
            TabTag.TAG_SEARCH.tag -> Screens.searchScreen()
            TabTag.TAG_HOME.tag -> Screens.homeScreen()
            TabTag.TAG_PROFILE.tag -> {
                if (prefs.isAuthorized()) {
                    Screens.profileScreen()
                } else {
                    Screens.loginScreen(nextScreen = Screens.profileScreen())
                }
            }
            else -> throw IllegalArgumentException("No such fragment with tag \"$tag\"")
        }
    }

    override fun provideRouter(): Router {
        return router
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun visibleFragment(): BaseFragment? {
        return childFragmentManager.fragments.lastOrNull { it.isVisible } as? BaseFragment
    }

    override fun onBackPressed(): Boolean {
        return visibleFragment()?.onBackPressed() == true
    }

    companion object {

        private const val ARG_TAG = "ARG_TAG"
        fun newInstance(tag: String): TabFragment = TabFragment().withArgs {
            putString(ARG_TAG, tag)
        }
    }
}