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
    val measuredHeight: Int = this.measuredHeight
    val listener = object : AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            onAnimationStart?.invoke(animation)
        }

        override fun onAnimationEnd(animation: Animator) {
            layoutParams.height = ZERO

            onAnimationEnd?.invoke(animation)
        }

        override fun onAnimationCancel(animation: Animator) {
            alpha = ZERO.float
            layoutParams.height = ZERO

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

    val listener = object : AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            onAnimationStart?.invoke(animation)
        }

        override fun onAnimationEnd(animation: Animator) {
            layoutParams.height = value

            onAnimationEnd?.invoke(animation)
        }

        override fun onAnimationCancel(animation: Animator) {
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

fun View.fadeIn(
    duration: Long,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    alpha = ZERO.float

    return animate()
        .alpha(ONE.float)
        .setDuration(duration)
        .setInterpolator(DecelerateInterpolator())
        .setListener(listener)
        .withLayer()
}

fun View.fadeOut(
    duration: Long,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    alpha = ONE.float

    return animate()
        .alpha(ZERO.float)
        .setDuration(duration)
        .setInterpolator(AccelerateInterpolator())
        .setListener(listener)
        .withLayer()
}

fun View.translateDown(
    duration: Long,
    value: Float,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    translationY = ZERO.float

    return animate()
        .setDuration(duration)
        .setListener(listener)
        .translationY(value)
        .withLayer()
}

fun View.translateLeft(
    duration: Long,
    value: Float,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    translationX = ZERO.float

    return animate()
        .setDuration(duration)
        .setListener(listener)
        .translationX(value.negative)
        .withLayer()
}

fun View.translateLeft(
    duration: Long,
    value: Float,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .translationX(value.negative)
        .withLayer()
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
}

fun View.translateRight(
    duration: Long,
    value: Float,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    translationX = ZERO.float

    return animate()
        .setDuration(duration)
        .setListener(listener)
        .translationX(value)
        .withLayer()
}

fun View.translateRight(
    duration: Long,
    value: Float,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .translationX(value)
        .withLayer()
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
}

fun View.translateUp(
    duration: Long,
    value: Float,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    translationY = ZERO.float

    return animate()
        .setDuration(duration)
        .setListener(listener)
        .translationY(value.negative)
        .withLayer()
}
