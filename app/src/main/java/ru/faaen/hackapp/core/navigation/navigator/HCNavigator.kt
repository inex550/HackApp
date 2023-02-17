package ru.faaen.hackapp.core.navigation.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.faaen.hackapp.core.navigation.navigator.FragmentTransactionHandler

class HCNavigator(
    activity: FragmentActivity,
    containerId: Int,
    fragmentManager: FragmentManager = activity.supportFragmentManager
): AppNavigator(activity, containerId, fragmentManager) {

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment
    ) {
        if (currentFragment is FragmentTransactionHandler) {
            currentFragment.setupFragmentTransaction(
                currentFragment,
                nextFragment,
                fragmentTransaction
            )
        }
    }
}