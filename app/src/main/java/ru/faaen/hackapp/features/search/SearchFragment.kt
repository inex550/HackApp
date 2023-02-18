package ru.faaen.hackapp.features.search

import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.base.BaseFragment

class SearchFragment: BaseFragment(
    layoutResId = R.layout.fragment_search
) {

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}