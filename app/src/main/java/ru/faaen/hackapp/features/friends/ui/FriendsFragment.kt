package ru.faaen.hackapp.features.friends.ui

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.context
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.core.ui.recycler.ConcatAdapter
import ru.faaen.hackapp.databinding.FragmentFriendsBinding
import ru.faaen.hackapp.features.friends.adapter.FriendsAdapter
import ru.faaen.hackapp.features.friends.data.FriendsItems
import ru.faaen.hackapp.features.profile.adapter.LifeProfileAdapter
import ru.faaen.hackapp.features.profile.data.InfoAboutMes
import ru.faaen.hackapp.features.profile.data.LifeProfiles


class FriendsFragment : BaseFragment(
    layoutResId = R.layout.fragment_friends

) {
    private val binding by viewBinding(FragmentFriendsBinding::bind)

    override fun setupUi() {
        with(binding) {

            tvMyId.text = InfoAboutMes.mail

            toolbarFriends.setNavigationOnClickListener {
                onBackPressed()
            }

            tvMyId.setOnClickListener {
                clip(tvMyId.text.toString())
                showSnackbarSuccess("Id скопирован в буфер обмена")
            }

            tvMyRef.setOnClickListener {
                clip(tvMyRef.text.toString())
                showSnackbarSuccess("Реферальная ссылка скопирован в буфер обмена")
            }

            friendsRv.layoutManager = LinearLayoutManager(requireContext())
            friendsRv.adapter = ConcatAdapter(
                FriendsAdapter()
            ).apply {
                setItems(FriendsItems)
            }


        }
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }

    private fun clip(text: String) {
        val clipboardManager =
            requireContext().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        var clipData: ClipData = ClipData.newPlainText("text", text)
        clipboardManager.setPrimaryClip(clipData)
    }
}
