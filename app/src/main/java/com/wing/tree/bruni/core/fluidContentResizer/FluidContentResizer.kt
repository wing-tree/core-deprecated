package com.wing.tree.bruni.core.fluidContentResizer

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

object FluidContentResizer {
    private var valueAnimator: ValueAnimator? = null

    fun registerActivity(
        activity: Activity,
        onInputMethodVisibilityChanged: ((InputMethodVisibilityChangedEvent) -> Unit)? = null
    ) {
        val activityViewHolder = ActivityViewHolder.from(activity)

        InputMethodVisibilityDetector.setOnInputMethodVisibilityChangedListener(activityViewHolder) {
            onInputMethodVisibilityChanged?.invoke(it)
            animate(activityViewHolder, it)
        }

        activityViewHolder.setOnDetachedListener {
            valueAnimator?.let {
                it.cancel()
                it.removeAllUpdateListeners()
            }
        }
    }

    private fun animate(
        activityViewHolder: ActivityViewHolder,
        inputMethodVisibilityChangedEvent: InputMethodVisibilityChangedEvent
    ) {
        val content = activityViewHolder.content
        val currentContentHeight = inputMethodVisibilityChangedEvent.currentContentHeight
        val previousContentHeight = inputMethodVisibilityChangedEvent.previousContentHeight

        content.setHeight(previousContentHeight)

        valueAnimator?.cancel()

        valueAnimator = ObjectAnimator.ofInt(
            previousContentHeight,
            currentContentHeight
        ).apply {
            interpolator = FastOutSlowInInterpolator()
        }

        valueAnimator?.let { valueAnimator ->
            valueAnimator.addUpdateListener {
                with(it.animatedValue) {
                    if (this is Int) {
                        content.setHeight(this)
                    }
                }
            }

            valueAnimator.start()
        }
    }

    private fun View.setHeight(height: Int) {
        val layoutParameter = this.layoutParams

        layoutParameter.height = height
        layoutParams = layoutParameter
    }
}
