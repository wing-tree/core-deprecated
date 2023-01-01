package com.wing.tree.bruni.core.fluidContentResizer

import android.view.ViewTreeObserver
import com.wing.tree.bruni.core.constant.MINUS_ONE

internal object InputMethodVisibilityDetector {
    fun interface OnInputMethodVisibilityChangedListener {
        fun onInputMethodVisibilityChangedListener(
            inputMethodVisibilityChangedEvent: InputMethodVisibilityChangedEvent
        )
    }

    fun setOnInputMethodVisibilityChangedListener(
        activityViewHolder: ActivityViewHolder,
        onInputMethodVisibilityChangedListener: OnInputMethodVisibilityChangedListener
    ) {
        with(activityViewHolder) {
            val inputMethodVisibilityDetector = InputMethodVisibilityDetector(
                this,
                onInputMethodVisibilityChangedListener
            )

            decorView.viewTreeObserver.addOnPreDrawListener(inputMethodVisibilityDetector)

            setOnDetachedListener {
                decorView.viewTreeObserver.removeOnPreDrawListener(inputMethodVisibilityDetector)
            }
        }
    }

    private class InputMethodVisibilityDetector(
        val activityViewHolder: ActivityViewHolder,
        val onInputMethodVisibilityChangedListener: OnInputMethodVisibilityChangedListener
    ) : ViewTreeObserver.OnPreDrawListener {

        private var previousContentHeight: Int = MINUS_ONE

        override fun onPreDraw(): Boolean {
            return detect().not()
        }

        private fun detect(): Boolean {
            val currentContentHeight = activityViewHolder.contentParent.height
            val decorView = activityViewHolder.decorView
            val navigationBarHeight = activityViewHolder.navigationBarHeight

            if (currentContentHeight == previousContentHeight) {
                return false
            }

            if (previousContentHeight > MINUS_ONE) {
                val top = activityViewHolder.contentParent.top
                val isVisible = currentContentHeight < decorView.height.minus(navigationBarHeight.plus(top))

                onInputMethodVisibilityChangedListener.onInputMethodVisibilityChangedListener(
                    InputMethodVisibilityChangedEvent(
                        isVisible = isVisible,
                        currentContentHeight = currentContentHeight,
                        previousContentHeight = previousContentHeight
                    )
                )
            }

            previousContentHeight = currentContentHeight

            return true
        }
    }
}
