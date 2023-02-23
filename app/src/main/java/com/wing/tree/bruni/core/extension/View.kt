package com.wing.tree.bruni.core.extension

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.util.DisplayMetrics
import android.util.Size
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.annotation.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.wing.tree.bruni.core.constant.ONE
import com.wing.tree.bruni.core.constant.ZERO

val View.displayMetrics: DisplayMetrics get() = resources.displayMetrics
val View.displaySize: Size get() = context.displaySize
val View.density: Float get() = displayMetrics.density
val View.densityDpi: Int get() = displayMetrics.densityDpi
val View.navigationBarHeight: Int get() = context.navigationBarHeight
val View.scaledDensity: Float get() = displayMetrics.scaledDensity

val View.isGone: Boolean get() = visibility == View.GONE
val View.isInvisible: Boolean get() = visibility == View.INVISIBLE
val View.isNotVisible: Boolean get() = isVisible.not()

val View.isNotShown: Boolean get() = isShown.not()

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.anim(@AnimRes id: Int) = AnimationUtils.loadAnimation(context, id)
fun View.boolean(@BoolRes id: Int) = resources.boolean(id)
fun View.dimen(@DimenRes id: Int) = resources.dimen(id)
fun View.drawable(@DrawableRes id: Int) = resources.drawable(id)
fun View.float(@DimenRes id: Int) = resources.float(id)
fun View.font(@FontRes id: Int) = ResourcesCompat.getFont(context, id)
fun View.integer(@IntegerRes id: Int) = resources.getInteger(id)
fun View.string(@StringRes id: Int) = resources.getString(id)

fun View.collapseVertically(
    value: Int,
    duration: Long,
    interpolator: TimeInterpolator = LinearInterpolator(),
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

    ValueAnimator.ofInt(measuredHeight, value).apply {
        addListener(listener)
        addUpdateListener {
            val animatedValue = it.animatedValue

            if (animatedValue is Int) {
                layoutParams.height = animatedValue

                requestLayout()
            }
        }

        this.duration = duration
        this.interpolator = interpolator
    }.start()
}

fun View.collapseVertically(
    duration: Long,
    interpolator: TimeInterpolator = LinearInterpolator(),
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
        this.interpolator = interpolator
    }.start()
}

fun View.crossFade(
    target: View,
    duration: Long,
    interpolator: TimeInterpolator? = LinearInterpolator(),
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
) {
    target.alpha = ZERO.float
    target.visibility = View.VISIBLE

    target.animate()
        .alpha(ONE.float)
        .setDuration(duration)
        .setInterpolator(interpolator)
        .withLayer()

    animate()
        .alpha(ZERO.float)
        .setDuration(duration)
        .setInterpolator(interpolator)
        .withStartAction(withStartAction)
        .withEndAction {
            invisible()
            withEndAction?.run()
        }
        .withLayer()
}

fun View.expandVertically(
    value: Int,
    duration: Long,
    interpolator: TimeInterpolator = LinearInterpolator(),
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
                layoutParams.height = animatedValue

                requestLayout()
            }
        }

        this.duration = duration
        this.interpolator = interpolator
    }.start()
}

fun View.fadeIn(
    duration: Long,
    interpolator: TimeInterpolator? = context.decelerateQuadInterpolator,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    alpha = ZERO.float

    return animate()
        .alpha(ONE.float)
        .setDuration(duration)
        .setInterpolator(interpolator)
        .setListener(listener)
        .withLayer()
}

fun View.fadeOut(
    duration: Long,
    interpolator: TimeInterpolator? = context.accelerateQuadInterpolator,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    alpha = ONE.float

    return animate()
        .alpha(ZERO.float)
        .setDuration(duration)
        .setInterpolator(interpolator)
        .setListener(listener)
        .withLayer()
}

fun View.rotate(
    value: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .rotation(value)
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
        .withLayer()
}

fun View.rotateX(
    value: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .rotationX(value)
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
        .withLayer()
}

fun View.rotateY(
    value: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .rotationY(value)
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
        .withLayer()
}

fun View.translateY(
    value: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .setListener(listener)
        .translationY(value)
        .withLayer()
}

fun View.translateY(
    value: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .translationY(value)
        .withLayer()
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
}

fun View.translateX(
    value: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .setListener(listener)
        .translationX(value)
        .withLayer()
}

fun View.translateX(
    value: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .translationX(value)
        .withLayer()
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
}

inline fun View.updateWidth(function: (Int) -> Int) {
    updateLayoutParams<ViewGroup.LayoutParams> {
        width = function(width)
    }
}

inline fun View.updateHeight(function: (Int) -> Int) {
    updateLayoutParams<ViewGroup.LayoutParams> {
        height = function(height)
    }
}
