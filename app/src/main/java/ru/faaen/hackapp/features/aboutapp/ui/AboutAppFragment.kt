package ru.faaen.hackapp.features.aboutapp.ui

import android.os.Handler
import android.os.Looper
import android.view.View
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.databinding.FragmentAboutAppBinding
import ru.faaen.hackapp.features.aboutapp.data.InfoAboutApps


class AboutAppFragment : BaseFragment(
    layoutResId = R.layout.fragment_about_app

) {
    private val binding by viewBinding(FragmentAboutAppBinding::bind)

    override fun setupUi() {
        with(binding) {

            Handler(Looper.getMainLooper()).postDelayed({
                progressBarAboutApp.visibility= View.INVISIBLE
            }, 150)

            toolbarAboutApp.setNavigationOnClickListener {
                onBackPressed()
            }

            wvAboutApp.loadDataWithBaseURL(
                null,
                InfoAboutApps.desc,
                "text/html",
                "utf-8",
                null
            )

        }
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}
