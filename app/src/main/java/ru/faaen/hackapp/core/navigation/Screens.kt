package ru.faaen.hackapp.core.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.faaen.hackapp.features.flow.FlowFragment
import ru.faaen.hackapp.features.flow.TabFragment
import ru.faaen.hackapp.features.profile.ProfileFragment

object Screens {

    fun profileScreen(): FragmentScreen = FragmentScreen {
        ProfileFragment()
    }

    fun flowScreen(): FragmentScreen = FragmentScreen {
        FlowFragment()
    }

    fun tabScreen(tag: String): FragmentScreen = FragmentScreen {
        TabFragment.newInstance(tag)
    }
}