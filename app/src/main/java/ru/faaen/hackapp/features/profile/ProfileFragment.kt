package ru.faaen.hackapp.features.profile

import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.databinding.FragmentHomeBinding
import ru.faaen.hackapp.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment(
    layoutResId = R.layout.fragment_profile
) {
    private val binding by viewBinding(FragmentProfileBinding::bind)

    override fun setupUi() {
        with(binding) {
            imBackProfile.setOnClickListener{
                showSnackbarSuccess("image background")
            }

            imProfile.setOnClickListener{
                showSnackbarSuccess("image")
            }

            buttonAboutMeProfile.setOnClickListener{
                showSnackbarSuccess("info about me")
            }

            buttonMyOrderProfile.setOnClickListener{
                showSnackbarSuccess("my order")
            }

            buttonGoalsProfile.setOnClickListener{
                showSnackbarSuccess("my goals")
            }
        }
    }
    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}