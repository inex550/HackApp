package ru.faaen.hackapp.features.profile.presentation.ui

import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.databinding.FragmentInfoAboutMeBinding
import ru.faaen.hackapp.features.profile.data.InfoAboutMes


class InfoAboutMeFragment : BaseFragment(
    layoutResId = R.layout.fragment_info_about_me

) {
    private val binding by viewBinding(FragmentInfoAboutMeBinding::bind)

    override fun setupUi() {
        with(binding) {

            initInfoAboutMe()

            toolbarInfoAboutMe.setNavigationOnClickListener {
                onBackPressed()
            }

            buttonSaveAboutMe.setOnClickListener{
                if (tieNameAboutMe.text!!.isNotEmpty() && tieLastNameAboutMe.text!!.isNotEmpty()) {
                   saveInfoAboutMe()
                    showSnackbarSuccess("Успешно сохронено")
                    requireLocalRouter().navigateTo(Screens.profileScreen())
                } else {
                    showSnackbarError("Имя или фамилия пусто")
                }
            }


        }
    }

    private fun initInfoAboutMe() {
        binding.tieNameAboutMe.setText(InfoAboutMes.name)
        binding.tieLastNameAboutMe.setText(InfoAboutMes.lastName)
        binding.tieFatherNameAboutMe.setText(InfoAboutMes.fatherName)
        binding.tieMailAboutMe.setText(InfoAboutMes.mail)
        binding.tiePasswordAboutMe.setText(InfoAboutMes.password)
        binding.tieRoleAboutMe.setText(InfoAboutMes.role)
        binding.tiePolAboutMe.setText(InfoAboutMes.pol)
        binding.tieDataAboutMe.setText(InfoAboutMes.data)
        binding.tieGitAboutMe.setText(InfoAboutMes.git)
    }

    private fun saveInfoAboutMe() {
        InfoAboutMes.name = binding.tieNameAboutMe.text.toString()
        InfoAboutMes.lastName = binding.tieLastNameAboutMe.text.toString()
        InfoAboutMes.fatherName = binding.tieFatherNameAboutMe.text.toString()
        InfoAboutMes.mail = binding.tieMailAboutMe.text.toString()
        InfoAboutMes.password = binding.tiePasswordAboutMe.text.toString()
        InfoAboutMes.role = binding.tieRoleAboutMe.text.toString()
        InfoAboutMes.pol = binding.tiePolAboutMe.text.toString()
        InfoAboutMes.data = binding.tieDataAboutMe.text.toString()
        InfoAboutMes.git = binding.tieGitAboutMe.text.toString()
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}
