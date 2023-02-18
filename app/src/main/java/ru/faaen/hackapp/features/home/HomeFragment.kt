package ru.faaen.hackapp.features.home

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
            zoomInOut()

            imEarthHome.setOnClickListener {
                animateEarthClick()
            }

            imNewsHome.setOnClickListener {
                showSnackbarSuccess("news")
            }

            imWhereGoHome.setOnClickListener {
                requireLocalRouter().navigateTo(Screens.whereGoScreen())
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
                showSnackbarError("friend")
            }
        }
    }

    private fun FragmentHomeBinding.zoomInOut() {
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
    }

    private fun FragmentHomeBinding.animateEarthClick() {
        imEarthHome.animate().apply {
            duration = 300
            scaleX(-0.2f)
            scaleY(-0.2f)
            scaleXBy(-0.2f)
            scaleYBy(-0.2f)
        }.withEndAction {
            requireLocalRouter().navigateTo(Screens.mapScreen())
        }.start()
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}