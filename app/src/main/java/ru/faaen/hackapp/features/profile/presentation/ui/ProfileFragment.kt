package ru.faaen.hackapp.features.profile.presentation.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityCompat
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.ui.anim.MyBounceInterpolator
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.databinding.FragmentHomeBinding
import ru.faaen.hackapp.databinding.FragmentProfileBinding
import ru.faaen.hackapp.features.profile.data.InfoAboutMes
import java.io.IOException

class ProfileFragment : BaseFragment(
    layoutResId = R.layout.fragment_profile
) {
    private val binding by viewBinding(FragmentProfileBinding::bind)

    private var image: Bitmap? = null

    override fun setupUi() {
        with(binding) {
            if (image != null) {
                imProfile.setImageBitmap(image)
            }

            imBackProfile.setOnClickListener {
                bounceBtnClick(it)
            }

            imProfileClick.setOnClickListener {
                bounceBtnClick(it)
                chooseProfilePicture()
            }

            buttonAboutMeProfile.setOnClickListener {
                bounceBtnClick(it)
                requireLocalRouter().navigateTo(Screens.infoAboutMeScreen())
            }

            buttonMyOrderProfile.setOnClickListener {
                bounceBtnClick(it)
                requireLocalRouter().navigateTo(Screens.eventProfileScreen())
            }

            buttonGoalsProfile.setOnClickListener {
                bounceBtnClick(it)
            }

            buttonMyLifeProfile.setOnClickListener {
                bounceBtnClick(it)
                requireLocalRouter().navigateTo(Screens.lifeProfileScreen())
            }

            tvNameProfile.text = InfoAboutMes.name + " " +  InfoAboutMes.lastName
            tvMailProfile.text = InfoAboutMes.mail

        }
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }

    private fun bounceBtnClick(view: View): View {
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.bounce)
        anim.interpolator = MyBounceInterpolator(0.05, 20.0)

        view.startAnimation(anim)
        return view
    }

    private fun chooseProfilePicture() {

        val pictureDialog = AlertDialog.Builder(context)
        pictureDialog.setTitle("Выберите действие")
        val pictureDialogItems = arrayOf("Выбрать фото из галереи", "Снимать фото с камеры")
        pictureDialog.setItems(
            pictureDialogItems
        ) { _, which ->
            when (which) {
                0 -> {
                    checkPhotoFromGalleryPermission()
                }
                1 -> {
                    takePhotoFromCamera()
                }
            }
        }
        pictureDialog.show()
    }

    private fun checkPhotoFromGalleryPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ), 0
            )
            return
        } else {
            choosePhotoFromGallery()
        }
    }


    private fun choosePhotoFromGallery() {

        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, 1)
    }

    private fun takePhotoFromCamera() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 2)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {

            when (requestCode) {
                2 -> {
                    image = data?.extras?.get("data") as Bitmap
                    binding.imProfile.setImageBitmap(image)
                }

                1 -> {
                    if (data != null) {
                        val contentURI = data.data
                        try {
                            image =
                                MediaStore.Images.Media.getBitmap(
                                    context?.contentResolver,
                                    contentURI
                                )
                            binding.imProfile.setImageBitmap(image)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }

                }

            }

        }
    }

}