package ru.faaen.hackapp.features.flow

import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.databinding.FragmentFlowBinding

class FlowFragment: BaseFragment(R.layout.fragment_flow) {

    private val binding by viewBinding(FragmentFlowBinding::bind)

    override fun setupUi() {
        binding.navBnv.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.item_profile -> selectTab(TabTag.TAG_PROFILE.tag)
            }
            true
        }
    }

    private fun selectTab(tag: String) {
        val fm = childFragmentManager

        val visibleFragment = fm.fragments.firstOrNull { it.isVisible }
        val selectedFragment = fm.findFragmentByTag(tag)

        if (visibleFragment != null && selectedFragment != null && visibleFragment == selectedFragment) {
            return
        }

        val transaction = fm.beginTransaction()

        if (selectedFragment == null) {
            val newFragment = Screens.tabScreen(tag).createFragment(fm.fragmentFactory)
            transaction.add(R.id.flow_container, newFragment, tag)
        }

        if (visibleFragment != null) {
            transaction.hide(visibleFragment)
        }

        if (selectedFragment != null) {
            transaction.show(selectedFragment)
        }

        transaction.commitNow()
    }
}