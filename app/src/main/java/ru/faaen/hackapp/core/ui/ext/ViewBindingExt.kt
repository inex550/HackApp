package ru.faaen.hackapp.core.ui.ext

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

typealias Binder<VB> = (View) -> VB

val ViewBinding.context: Context get() = root.context

val ViewBinding.resources: Resources get() = root.resources

fun <VB: ViewBinding> Fragment.viewBinding(
    binder: Binder<VB>
): ViewBindingDelegate<VB> = ViewBindingDelegate(binder)

class ViewBindingDelegate <VB: ViewBinding> (
    private val binder: Binder<VB>
): ReadOnlyProperty<Fragment, VB> {

    private var viewBinding: VB? = null

    private val lifecycleObserver = FragmentLifecycleObserver()

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        return viewBinding ?: let {
            binder(thisRef.requireView()).also {
                thisRef.viewLifecycleOwner.lifecycle.addObserver(lifecycleObserver)
                viewBinding = it
            }
        }
    }

    inner class FragmentLifecycleObserver: DefaultLifecycleObserver {

        private val handler = Handler(Looper.getMainLooper())

        override fun onDestroy(owner: LifecycleOwner) {
            handler.post { viewBinding = null }
            owner.lifecycle.removeObserver(this)
        }
    }
}