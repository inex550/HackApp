package ru.faaen.hackapp.features.flow

import android.os.Bundle
import android.view.View
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
                R.id.item_search -> selectTab(TabTag.TAG_SEARCH.tag)
                R.id.item_home -> selectTab(TabTag.TAG_HOME.tag)
                R.id.item_profile -> selectTab(TabTag.TAG_PROFILE.tag)
            }
            true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            binding.navBnv.selectedItemId = R.id.item_home
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

    private fun visibleFragment(): BaseFragment? {
        return childFragmentManager.fragments.lastOrNull { it.isVisible } as? BaseFragment
    }

    override fun onBackPressed(): Boolean {
        return visibleFragment()?.onBackPressed() ?: super.onBackPressed()
    }
}