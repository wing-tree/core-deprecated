package com.wing.tree.bruni.core.extension

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.wing.tree.bruni.core.constant.ALPHA_MAXIMUM
import com.wing.tree.bruni.core.constant.ALPHA_MINIMUM
import com.wing.tree.bruni.core.constant.EMPTY
import com.wing.tree.bruni.core.constant.ZERO

private const val TAG = "TextView"

@SuppressLint("DiscouragedPrivateApi", "SoonBlockedPrivateApi")
fun TextView.setCursorDrawableColor(@ColorInt color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        textCursorDrawable?.tinted(color)
        return
    }

    try {
        val mEditor = TextView::class
            .getDeclaredFieldOrNull("mEditor")?.get(this) ?: this
        val mCursorDrawableRes = TextView::class
            .getDeclaredFieldOrNull("mCursorDrawableRes")?.get(this)

        val mCursorDrawable = if (mCursorDrawableRes is Int) {
            ContextCompat.getDrawable(context, mCursorDrawableRes)
        } else {
            return
        }

        val mDrawableForCursor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mEditor::class.getDeclaredFieldOrNull("mDrawableForCursor")
        } else {
            null
        }

        mDrawableForCursor?.set(mEditor, mCursorDrawable?.tinted(color))
            ?: with(mCursorDrawable?.tinted(color)) {
                mEditor::class.getDeclaredFieldOrNull(
                    "mCursorDrawable",
                    "mDrawableForCursor"
                )?.set(
                    mEditor,
                    arrayOf(this, this)
                )
        }
    } catch (throwable: Throwable) {
        Log.e(TAG, throwable.message ?: EMPTY)
    }
}

@SuppressLint("DiscouragedPrivateApi", "SoonBlockedPrivateApi")
fun TextView.setCursorDrawableAlpha(alpha: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        textCursorDrawable?.alpha = alpha
        return
    }

    try {
        val mEditor = TextView::class
            .getDeclaredFieldOrNull("mEditor")?.get(this) ?: this
        val mCursorDrawableRes = TextView::class
            .getDeclaredFieldOrNull("mCursorDrawableRes")?.get(this)

        val mCursorDrawable = if (mCursorDrawableRes is Int) {
            ContextCompat.getDrawable(context, mCursorDrawableRes)?.let {
                it.alpha = alpha
            }
        } else {
            return
        }

        val mDrawableForCursor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mEditor::class.getDeclaredFieldOrNull("mDrawableForCursor")
        } else {
            null
        }

        mDrawableForCursor?.set(mEditor, mCursorDrawable)
            ?: with(mCursorDrawable) {
                mEditor::class.getDeclaredFieldOrNull(
                    "mCursorDrawable",
                    "mDrawableForCursor"
                )?.set(
                    mEditor,
                    arrayOf(this, this)
                )
            }
    } catch (throwable: Throwable) {
        Log.e(TAG, throwable.message ?: EMPTY)
    }
}

fun TextView.setTextAlpha(alpha: Int) {
    with(textColors.withAlpha(alpha)) {
        setTextColor(this)
        setHintTextColor(this)
        setLinkTextColor(this)
        setCursorDrawableAlpha(alpha)
    }
}

fun TextView.textFadeIn(
    duration: Long,
    startDelay: Long = ZERO.long,
    onAnimationStart: ((animation: Animator) -> Unit)? = null,
    onAnimationEnd: ((animation: Animator) -> Unit)? = null,
    onAnimationCancel: ((animation: Animator) -> Unit)? = null,
    onAnimationRepeat: ((animation: Animator) -> Unit)? = null,
) {
    val listener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            onAnimationStart?.invoke(animation)
        }

        override fun onAnimationEnd(animation: Animator) {
            setTextAlpha(ALPHA_MAXIMUM)
            onAnimationEnd?.invoke(animation)
        }

        override fun onAnimationCancel(animation: Animator) {
            setTextAlpha(ALPHA_MAXIMUM)
            onAnimationCancel?.invoke(animation)
        }

        override fun onAnimationRepeat(animation: Animator) {
            onAnimationRepeat?.invoke(animation)
        }
    }

    ValueAnimator.ofInt(ALPHA_MINIMUM, ALPHA_MAXIMUM).apply {
        addListener(listener)
        addUpdateListener {
            val animatedValue = it.animatedValue

            if (animatedValue is Int) {
                setTextAlpha(animatedValue)
            }
        }

        this.duration = duration
        this.startDelay = startDelay
        interpolator = DecelerateInterpolator()
    }.start()
}

fun TextView.textFadeOut(
    duration: Long,
    startDelay: Long = ZERO.long,
    onAnimationStart: ((animation: Animator) -> Unit)? = null,
    onAnimationEnd: ((animation: Animator) -> Unit)? = null,
    onAnimationCancel: ((animation: Animator) -> Unit)? = null,
    onAnimationRepeat: ((animation: Animator) -> Unit)? = null,
) {
    val listener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            onAnimationStart?.invoke(animation)
        }

        override fun onAnimationEnd(animation: Animator) {
            setTextAlpha(ALPHA_MAXIMUM)
            onAnimationEnd?.invoke(animation)
        }

        override fun onAnimationCancel(animation: Animator) {
            setTextAlpha(ALPHA_MAXIMUM)
            onAnimationCancel?.invoke(animation)
        }

        override fun onAnimationRepeat(animation: Animator) {
            onAnimationRepeat?.invoke(animation)
        }
    }

    ValueAnimator.ofInt(ALPHA_MAXIMUM, ALPHA_MINIMUM).apply {
        addListener(listener)
        addUpdateListener {
            val animatedValue = it.animatedValue

            if (animatedValue is Int) {
                setTextAlpha(animatedValue)
            }
        }

        this.duration = duration
        this.startDelay = startDelay
        interpolator = AccelerateInterpolator()
    }.start()
}
