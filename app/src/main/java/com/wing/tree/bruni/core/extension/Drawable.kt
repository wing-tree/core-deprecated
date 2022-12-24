package com.wing.tree.bruni.core.extension

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

fun Drawable.tinted(@ColorInt tint: Int): Drawable = when (this) {
    is VectorDrawableCompat -> apply { setTintList(ColorStateList.valueOf(tint)) }
    is VectorDrawable -> apply { setTintList(ColorStateList.valueOf(tint)) }
    else -> DrawableCompat.wrap(this)
        .also { DrawableCompat.setTint(it, tint) }
        .let { DrawableCompat.unwrap(it) }
}
