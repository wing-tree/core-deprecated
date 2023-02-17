package com.wing.tree.bruni.windowInsetsAnimation

import android.view.View
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.core.view.WindowInsetsCompat.Type.systemBars
import com.wing.tree.bruni.windowInsetsAnimation.extension.isTypeMasked

class DeferredWindowInsetsAnimationCallback :
    WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_CONTINUE_ON_SUBTREE),
    OnApplyWindowInsetsListener
{
    private var v: View? = null
    private var windowInsets: WindowInsetsCompat? = null

    private var isDeferred = false

    override fun onApplyWindowInsets(
        v: View,
        windowInsets: WindowInsetsCompat
    ): WindowInsetsCompat {
        this.v = v
        this.windowInsets = windowInsets

        val typeMask = when {
            isDeferred -> systemBars()
            else -> ime() or systemBars()
        }

        with(windowInsets.getInsets(typeMask)) {
            v.setPadding(left, top, right, bottom)
        }

        return WindowInsetsCompat.CONSUMED
    }

    override fun onPrepare(animation: WindowInsetsAnimationCompat) {
        if (animation.isTypeMasked(ime())) {
            isDeferred = true
        }
    }

    override fun onProgress(
        insets: WindowInsetsCompat,
        runningAnims: List<WindowInsetsAnimationCompat>
    ): WindowInsetsCompat {
        return insets
    }

    override fun onEnd(animation: WindowInsetsAnimationCompat) {
        if (isDeferred.and(animation.isTypeMasked(ime()))) {
            isDeferred = false

            val v = v ?: return
            val windowInsets = windowInsets ?: return

            ViewCompat.dispatchApplyWindowInsets(v, windowInsets)
        }
    }
}
