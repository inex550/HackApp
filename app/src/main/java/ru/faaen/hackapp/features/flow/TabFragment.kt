package ru.faaen.hackapp.features.flow

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.utils.uiLazy
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.navigation.navigator.HCNavigator
import ru.faaen.hackapp.core.navigation.sub.CiceroneHolder
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.withArgs
import ru.faaen.hackapp.features.profile.ProfileFragment

class TabFragment: BaseFragment(
    layoutResId = R.layout.fragment_tab
) {
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

    private val navigator: HCNavigator by uiLazy {
        HCNavigator(requireActivity(), R.id.tab_container, childFragmentManager)
    }

    override fun setupUi() {
        if (childFragmentManager.findFragmentById(R.id.tab_container) == null) {
            val screen = screenByTag(screenTag)
            router.newRootScreen(screen)
        }
    }

    private fun screenByTag(tag: String): Screen {
        return when(tag) {
            TabTag.TAG_PROFILE.tag -> Screens.profileScreen()
            else -> throw IllegalArgumentException("No such fragment with tag \"$tag\"")
        }
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