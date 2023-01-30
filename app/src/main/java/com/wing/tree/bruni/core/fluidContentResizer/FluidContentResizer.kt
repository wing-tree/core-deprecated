package com.wing.tree.bruni.core.fluidContentResizer

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.app.Activity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.wing.tree.bruni.core.extension.updateHeight

class FluidContentResizer(
    private val duration: Long = DURATION,
    private val interpolator: TimeInterpolator = FastOutSlowInInterpolator()
) {
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

        content.updateHeight(previousContentHeight)

        valueAnimator?.cancel()

        valueAnimator = ObjectAnimator.ofInt(
            previousContentHeight,
            currentContentHeight
        ).apply {
            duration = this@FluidContentResizer.duration
            interpolator = this@FluidContentResizer.interpolator
        }

        valueAnimator?.let { valueAnimator ->
            valueAnimator.addUpdateListener {
                with(it.animatedValue) {
                    if (this is Int) {
                        content.updateHeight(this)
                    }
                }
            }

            valueAnimator.start()
        }
    }

    companion object {
        const val DURATION = 300L
    }
}
