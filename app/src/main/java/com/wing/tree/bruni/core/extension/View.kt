package com.wing.tree.bruni.core.extension

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.view.isVisible
import com.wing.tree.bruni.core.constant.ONE
import com.wing.tree.bruni.core.constant.ZERO

val View.isGone: Boolean get() = visibility == View.GONE
val View.isInvisible: Boolean get() = visibility == View.INVISIBLE
val View.isNotVisible: Boolean get() = isVisible.not()

val View.isNotShown: Boolean get() = isShown.not()

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.collapseVertically(
    duration: Long,
    onAnimationStart: ((animation: Animator) -> Unit)? = null,
    onAnimationEnd: ((animation: Animator) -> Unit)? = null,
    onAnimationCancel: ((animation: Animator) -> Unit)? = null,
    onAnimationRepeat: ((animation: Animator) -> Unit)? = null
) {
    visible()

    val measuredHeight: Int = this.measuredHeight
    val listener = object : AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            onAnimationStart?.invoke(animation)
        }

        override fun onAnimationEnd(animation: Animator) {
            alpha = ZERO.float
            layoutParams.height = ZERO

            gone()
            onAnimationEnd?.invoke(animation)
        }

        override fun onAnimationCancel(animation: Animator) {
            alpha = ZERO.float
            layoutParams.height = ZERO

            gone()
            onAnimationCancel?.invoke(animation)
        }

        override fun onAnimationRepeat(animation: Animator) {
            onAnimationRepeat?.invoke(animation)
        }
    }

    ValueAnimator.ofInt(measuredHeight, ZERO).apply {
        addListener(listener)
        addUpdateListener {
            val animatedValue = it.animatedValue

            if (animatedValue is Int) {
                alpha = animatedValue.div(measuredHeight.float)
                layoutParams.height = animatedValue

                requestLayout()
            }
        }

        this.duration = duration
        interpolator = DecelerateInterpolator()
    }.start()
}

fun View.expandVertically(
    duration: Long,
    value: Int,
    onAnimationStart: ((animation: Animator) -> Unit)? = null,
    onAnimationEnd: ((animation: Animator) -> Unit)? = null,
    onAnimationCancel: ((animation: Animator) -> Unit)? = null,
    onAnimationRepeat: ((animation: Animator) -> Unit)? = null
) {
    layoutParams.height = height

    visible()

    val listener = object : AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            onAnimationStart?.invoke(animation)
        }

        override fun onAnimationEnd(animation: Animator) {
            alpha = ONE.float
            layoutParams.height = value

            onAnimationEnd?.invoke(animation)
        }

        override fun onAnimationCancel(animation: Animator) {
            alpha = ONE.float
            layoutParams.height = value

            onAnimationCancel?.invoke(animation)
        }

        override fun onAnimationRepeat(animation: Animator) {
            onAnimationRepeat?.invoke(animation)
        }
    }

    ValueAnimator.ofInt(height, value).apply {
        addListener(listener)
        addUpdateListener {
            val animatedValue = it.animatedValue

            if (animatedValue is Int) {
                alpha = animatedValue.div(value.float)
                layoutParams.height = animatedValue

                requestLayout()
            }
        }

        this.duration = duration
        interpolator = DecelerateInterpolator()
    }.start()
}

internal fun View.fadeIn(
    duration: Long,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    visible()

    alpha = ZERO.float

    return animate()
        .alpha(ONE.float)
        .setDuration(duration)
        .setInterpolator(DecelerateInterpolator())
        .setListener(listener)
        .withLayer()
}

internal fun View.fadeOut(
    duration: Long,
    onAnimationStart: ((animation: Animator) -> Unit)? = null,
    onAnimationEnd: ((animation: Animator) -> Unit)? = null,
    onAnimationCancel: ((animation: Animator) -> Unit)? = null,
    onAnimationRepeat: ((animation: Animator) -> Unit)? = null
): ViewPropertyAnimator {
    this.apply {
        alpha = ONE.float

        val listener = object : AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                onAnimationStart?.invoke(animation)
            }

            override fun onAnimationEnd(animation: Animator) {
                gone()
                onAnimationEnd?.invoke(animation)
            }

            override fun onAnimationCancel(animation: Animator) {
                onAnimationCancel?.invoke(animation)
            }

            override fun onAnimationRepeat(animation: Animator) {
                onAnimationRepeat?.invoke(animation)
            }
        }

        return@fadeOut animate()
            .alpha(ZERO.float)
            .setDuration(duration)
            .setInterpolator(AccelerateInterpolator())
            .setListener(listener)
            .withLayer()
    }
}

fun View.translateDown(
    duration: Long,
    value: Float,
    listener: AnimatorListener? = null
) {
    this.apply {
        translationY = 0.0F

        animate()
            .translationY(value)
            .setDuration(duration)
            .setListener(listener)
            .withLayer()
    }
}

fun View.translateLeft(
    duration: Long,
    value: Float,
    listener: AnimatorListener? = null
) {
    this.apply {
        translationX = 0.0F

        animate()
            .translationX(-value)
            .setDuration(duration)
            .setListener(listener)
            .withLayer()
    }
}

fun View.translateRight(
    duration: Long,
    value: Float,
    listener: AnimatorListener? = null
) {
    this.apply {
        translationX = 0.0F

        animate()
            .translationX(value)
            .setDuration(duration)
            .setListener(listener)
            .withLayer()
    }
}

fun View.translateUp(
    duration: Long,
    value: Float,
    listener: AnimatorListener? = null
) {
    this.apply {
        translationY = 0.0F

        animate()
            .translationY(-value)
            .setDuration(duration)
            .setListener(listener)
            .withLayer()
    }
}
