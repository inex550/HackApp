package ru.faaen.hackapp.features.home

import android.widget.Toast
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(
    layoutResId = R.layout.fragment_home
) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun setupUi() {
        with(binding) {
            imEarthHome.animate().apply {
                duration = 500
                scaleX(0.5f)
                scaleY(0.5f)
                scaleXBy(0.5f)
                scaleYBy(0.5f)
            }.withEndAction {
                imEarthHome.animate().apply {
                    duration = 500
                    scaleX(-0.5f)
                    scaleY(-0.5f)
                    scaleXBy(-0.5f)
                    scaleYBy(-0.5f)
                }.start()
            }

            imEarthHome.setOnClickListener {
                it.animate().apply {
                    duration = 300
                    scaleX(-0.2f)
                    scaleY(-0.2f)
                    scaleXBy(-0.2f)
                    scaleYBy(-0.2f)
                }.withEndAction {
                    requireLocalRouter().navigateTo(Screens.mapScreen())
                }.start()
            }
            imNewsHome.setOnClickListener {
                showSnackbarSuccess("news")
            }
            imWhereGoHome.setOnClickListener {
                showSnackbarSuccess("where go")
            }
            imShopHome.setOnClickListener {
                showSnackbarSuccess("shop")
            }
            imInfoHome.setOnClickListener {
                showSnackbarSuccess("info")
            }
            imWhyGoHome.setOnClickListener {
                showSnackbarSuccess("why go")
            }
            imFriendHome.setOnClickListener {
//                showSnackbarSuccess("friend")
                showSnackbarError("friend")
            }

        }
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}