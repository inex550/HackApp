package ru.faaen.hackapp.features.profile.presentation.ui

import androidx.recyclerview.widget.LinearLayoutManager
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.core.ui.recycler.ConcatAdapter
import ru.faaen.hackapp.databinding.FragmentEventProfileBinding
import ru.faaen.hackapp.databinding.FragmentEventsBinding
import ru.faaen.hackapp.databinding.FragmentLifeProfileBinding
import ru.faaen.hackapp.features.profile.adapter.EventProfileAdapter
import ru.faaen.hackapp.features.profile.adapter.LifeProfileAdapter
import ru.faaen.hackapp.features.profile.data.EventProfiles
import ru.faaen.hackapp.features.profile.data.LifeProfiles


class LifeProfileFragment : BaseFragment(
    layoutResId = R.layout.fragment_life_profile

) {
    private val binding by viewBinding(FragmentLifeProfileBinding::bind)

    override fun setupUi() {
        with(binding) {

            toolbarLifeProfile.setNavigationOnClickListener {
                onBackPressed()
            }

            lifeProfileRv.layoutManager = LinearLayoutManager(requireContext())
           lifeProfileRv.adapter = ConcatAdapter(
                LifeProfileAdapter()
            ).apply {
                setItems(LifeProfiles)
            }


        }
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}
