package ru.faaen.hackapp.features.home

import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.view.postDelayed
import androidx.lifecycle.Lifecycle
import com.github.terrakok.cicerone.Screen
import dagger.hilt.android.AndroidEntryPoint
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.navigation.Screens
import ru.faaen.hackapp.core.prefs.PreferenceStorage
import ru.faaen.hackapp.core.ui.anim.MyBounceInterpolator
import ru.faaen.hackapp.core.ui.base.BaseFragment
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.databinding.FragmentHomeBinding
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(
    layoutResId = R.layout.fragment_home
) {
    @Inject
    lateinit var prefs: PreferenceStorage

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun setupUi() {
        with(binding) {
            animateRussia()

            russiaIv.setOnClickListener {
                animateRussiaClick()
            }

            newsLl.setOnClickListener {
                bounceBtnClick(it).post {
                    requireLocalRouter().navigateTo(Screens.newsScreen())
                }
            }

            placesLl.setOnClickListener {
                bounceBtnClick(it).post {
                    requireLocalRouter().navigateTo(Screens.whereGoScreen())
                }
            }

            shopLl.setOnClickListener {
                val screen: Screen = if (prefs.isAuthorized())
                    Screens.shopScreen()
                else
                    Screens.loginScreen(Screens.shopScreen())

                bounceBtnClick(it).post {
                    requireLocalRouter().navigateTo(screen)
                }
            }

            aboutLl.setOnClickListener {
                bounceBtnClick(it)
            }

            eventsLl.setOnClickListener {
                bounceBtnClick(it).post {
                    requireLocalRouter().navigateTo(Screens.eventsScreen())
                }
            }

            friendsLl.setOnClickListener {
                bounceBtnClick(it)
            }
        }
    }

    private fun animateRussia() {
        postRotated(5f) {
            postRotated(-5f) {
                postRotated(0f) {
                    if ((lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED))) {
                        binding.russiaIv.postDelayed(3000) {
                            animateRussia()
                        }
                    }
                }
            }
        }
    }

    private fun postRotated(angle: Float, after: () -> Unit) {
        if ((lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED))) {
            binding.russiaIv.animate()
                .rotation(angle)
                .setDuration(100)
                .start()

        }

        view?.postDelayed(100L) {
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