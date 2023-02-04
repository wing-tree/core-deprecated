package com.wing.tree.bruni.windowInsetsAnimation

import android.view.View
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_CONTINUE_ON_SUBTREE
import androidx.core.view.WindowInsetsCompat
import com.wing.tree.bruni.windowInsetsAnimation.extension.isTypeMasked

object DeferringWindowInsetsAnimation {
    private const val dispatchMode = DISPATCH_MODE_CONTINUE_ON_SUBTREE

    private val ime = WindowInsetsCompat.Type.ime()
    private val systemBars = WindowInsetsCompat.Type.systemBars()

    class Callback :
        WindowInsetsAnimationCompat.Callback(dispatchMode),
        OnApplyWindowInsetsListener
    {
        private var v: View? = null
        private var windowInsets: WindowInsetsCompat? = null

        private var isDeferring = false

        override fun onApplyWindowInsets(
            v: View,
            windowInsets: WindowInsetsCompat
        ): WindowInsetsCompat {
            this.v = v
            this.windowInsets = windowInsets

            val typeMask = when {
                isDeferring -> systemBars
                else -> ime or systemBars
            }

            with(windowInsets.getInsets(typeMask)) {
                v.setPadding(left, top, right, bottom)
            }

            return WindowInsetsCompat.CONSUMED
        }

        override fun onPrepare(animation: WindowInsetsAnimationCompat) {
            if (animation.isTypeMasked(ime)) {
                isDeferring = true
            }
        }

        override fun onProgress(
            insets: WindowInsetsCompat,
            runningAnims: List<WindowInsetsAnimationCompat>
        ): WindowInsetsCompat {
            return insets
        }

        override fun onEnd(animation: WindowInsetsAnimationCompat) {
            if (isDeferring.and(animation.isTypeMasked(ime))) {
                isDeferring = false

                val v = v ?: return
                val windowInsets = windowInsets ?: return

                ViewCompat.dispatchApplyWindowInsets(v, windowInsets)
            }
        }
    }
}
