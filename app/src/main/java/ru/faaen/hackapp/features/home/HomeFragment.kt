package ru.faaen.hackapp.features.home

import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.base.BaseFragment

class HomeFragment: BaseFragment(
    layoutResId = R.layout.fragment_home
) {

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}