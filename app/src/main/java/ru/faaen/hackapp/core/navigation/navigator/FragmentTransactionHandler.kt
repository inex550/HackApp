package ru.faaen.hackapp.core.navigation.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

interface FragmentTransactionHandler {

    fun setupFragmentTransaction(
        currentFragment: Fragment,
        nextFragment: Fragment,
        transaction: FragmentTransaction
    )
}
