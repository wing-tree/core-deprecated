package com.wing.tree.bruni.core.extension

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.widget.ImageView

fun ImageView.tintFade(
    duration: Long,
    interpolator: TimeInterpolator = context.decelerateQuadInterpolator,
    vararg values: Int
): ValueAnimator {
    return ValueAnimator.ofArgb(*values).apply {
        this.duration = duration
        this.interpolator = interpolator

        addUpdateListener {
            val animatedValue = it.animatedValue

            if (animatedValue is Int) {
                imageTintList = ColorStateList.valueOf(animatedValue)
            }
        }

        start()
    }
}
