package ru.faaen.hackapp.core.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.faaen.hackapp.features.auth.presentation.ui.LoginFragment
import ru.faaen.hackapp.features.auth.presentation.ui.RegisterFragment
import ru.faaen.hackapp.features.flow.FlowFragment
import ru.faaen.hackapp.features.flow.TabFragment
import ru.faaen.hackapp.features.home.HomeFragment
import ru.faaen.hackapp.features.map.MapsFragment
import ru.faaen.hackapp.features.profile.ProfileFragment
import ru.faaen.hackapp.features.wherego.presentation.ui.WhereGoFragment

object Screens {

    fun loginScreen(nextScreen: Screen? = null): FragmentScreen = FragmentScreen {
        LoginFragment().apply {
            this.nextScreen = nextScreen
        }
    }

    fun registerScreen(nextScreen: Screen? = null): FragmentScreen = FragmentScreen {
        RegisterFragment().apply {
            this.nextScreen = nextScreen
        }
    }

    fun whereGoScreen(): FragmentScreen = FragmentScreen {
        WhereGoFragment()
    }

    fun homeScreen(): FragmentScreen = FragmentScreen {
        HomeFragment()
    }

    fun profileScreen(): FragmentScreen = FragmentScreen {
        ProfileFragment()
    }

    fun flowScreen(): FragmentScreen = FragmentScreen {
        FlowFragment()
    }

    fun tabScreen(tag: String): FragmentScreen = FragmentScreen {
        TabFragment.newInstance(tag)
    }

    fun mapScreen(): FragmentScreen = FragmentScreen {
        MapsFragment()
    }
}