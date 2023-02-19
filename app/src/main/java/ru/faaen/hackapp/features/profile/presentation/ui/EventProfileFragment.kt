package ru.faaen.hackapp.features.profile.presentation.ui

import androidx.recyclerview.widget.LinearLayoutManager
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.utils.uiLazy
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.core.ui.recycler.ConcatAdapter
import ru.faaen.hackapp.databinding.FragmentEventProfileBinding
import ru.faaen.hackapp.features.profile.adapter.EventProfileAdapter
import ru.faaen.hackapp.features.profile.data.EventProfileItem
import ru.faaen.hackapp.features.profile.data.EventProfiles


class EventProfileFragment : BaseFragment(
    layoutResId = R.layout.fragment_event_profile

) {
    private val binding by viewBinding(FragmentEventProfileBinding::bind)

    override fun setupUi() {
        with(binding) {
            toolbarEventProfile.setNavigationOnClickListener {
                onBackPressed()
            }

            eventProfileRv.layoutManager = LinearLayoutManager(requireContext())
            eventProfileRv.adapter = ConcatAdapter(
                EventProfileAdapter()
            ).apply {
                setItems(EventProfiles)
            }
        }
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}
