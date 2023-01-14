package com.wing.tree.bruni.core.extension

import android.view.ViewPropertyAnimator

fun ViewPropertyAnimator.scale(value: Float) = scaleX(value).scaleY(value)
