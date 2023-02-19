package ru.faaen.hackapp.features.home

import android.animation.Animator
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import androidx.core.view.postDelayed
import androidx.lifecycle.Lifecycle
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.utils.uiLazy
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.ui.anim.MyBounceInterpolator
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(
    layoutResId = R.layout.fragment_home
) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun setupUi() {
        with(binding) {
            animateRussia()

            russiaIv.setOnClickListener {
                animateRussiaClick()
            }

            newsLl.setOnClickListener {
                bounceBtnClick(it)
            }

            placesLl.setOnClickListener {
                bounceBtnClick(it).post {
                    requireLocalRouter().navigateTo(Screens.whereGoScreen())
                }
            }

            shopLl.setOnClickListener {
                bounceBtnClick(it)
            }

            aboutLl.setOnClickListener {
                bounceBtnClick(it).post {
                    requireLocalRouter().navigateTo(Screens.aboutAppScreen())
                }
            }

            eventsLl.setOnClickListener {
                bounceBtnClick(it)
            }

            friendsLl.setOnClickListener {
                bounceBtnClick(it).post {
                    requireLocalRouter().navigateTo(Screens.friendsScreen())
                }
            }
        }
    }

    private fun animateRussia() {
        postRotated(5f) {
            postRotated(-5f) {
                postRotated(0f) {
                    if (!(lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)))
                        return@postRotated

                    binding.russiaIv.postDelayed(3000) {
                        animateRussia()
                    }
                }
            }
        }
    }

    private fun postRotated(angle: Float, after: () -> Unit) {
        if (!(lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED))) return

        binding.russiaIv.animate()
            .rotation(angle)
            .setDuration(100)
            .start()

        binding.russiaIv.postDelayed(100L) {
            after()
        }
    }

    private fun bounceBtnClick(view: View): View {
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.bounce)
        anim.interpolator = MyBounceInterpolator(0.05, 20.0)

        view.startAnimation(anim)
        return view
    }

    private fun FragmentHomeBinding.animateRussiaClick() {
        russiaIv.animate().apply {
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