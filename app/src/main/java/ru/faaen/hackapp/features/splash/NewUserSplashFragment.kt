package ru.faaen.hackapp.features.splash

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import androidx.core.animation.doOnEnd
import androidx.core.view.postDelayed
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.utils.uiLazy
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.getScreenWidth
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.databinding.FragmentSplashNewUserBinding

class NewUserSplashFragment: BaseFragment(
    layoutResId = R.layout.fragment_splash_new_user
) {
    private val binding by viewBinding(FragmentSplashNewUserBinding::bind)

    private var isCancelled = false

    private val animator: Animator by uiLazy {
        AnimatorSet().apply {
            play(reduceLogoAnim)
            play(moveContentAnimated).after(reduceLogoAnim)
        }
    }

    private val reduceLogoAnim by uiLazy {
        ValueAnimator.ofFloat(1.0f, 0.4f).apply {
            duration = 1000L
            addUpdateListener {
                val value = it.animatedValue as Float
                binding.logoIv.apply {
                    scaleX = value
                    scaleY = value
                }
            }
        }
    }

    private val moveContentAnimated by uiLazy {
        val logoPos = -(getScreenWidth() / 4)
        val splashTextPos = getScreenWidth() / 1.65f

        ValueAnimator.ofFloat(0.0f, 1.0f).apply {
            duration = 1000L
            addUpdateListener {
                val progress = it.animatedValue as Float

                with(binding) {
                    logoIv.translationX = logoPos * progress
                    wallView.translationX = logoIv.translationX
                    splashTv.translationX = splashTextPos * progress
                }
            }
        }
    }

    override fun setupUi() {
        animator.apply {
            doOnEnd {
                binding.root.postDelayed(500L) {
                    showNextScreen()
                }
            }
        }.start()
    }

    private fun showNextScreen() {
        localRouter?.replaceScreen(Screens.flowScreen())
    }

    private fun cancelAnimation() {
        isCancelled = true
        animator.cancel()
    }

    override fun onStop() {
        cancelAnimation()
        super.onStop()
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}