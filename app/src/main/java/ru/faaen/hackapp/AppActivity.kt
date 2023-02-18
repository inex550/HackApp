package ru.faaen.hackapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.hilt.android.AndroidEntryPoint
import ru.faaen.hackapp.core.common.utils.uiLazy
import ru.faaen.hackapp.core.navigation.RouterProvider
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.navigation.navigator.HCNavigator
import ru.faaen.hackapp.core.ui.base.BaseFragment
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : AppCompatActivity(), RouterProvider {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: HCNavigator by uiLazy {
        object : HCNavigator(this, R.id.app_container) {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        if (savedInstanceState == null) {
            router.newRootScreen(Screens.whereGoScreen())
        }
    }

    override fun provideRouter(): Router {
        return router
    }

    private fun visibleFragment(): BaseFragment? {
        return supportFragmentManager.fragments.lastOrNull { it.isVisible } as? BaseFragment
    }

    override fun onBackPressed() {
         visibleFragment()?.onBackPressed() ?: super.onBackPressed()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}