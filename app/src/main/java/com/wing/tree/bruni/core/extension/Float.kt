package com.wing.tree.bruni.core.extension

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import com.wing.tree.bruni.core.constant.FOUR
import com.wing.tree.bruni.core.constant.TWO
import com.wing.tree.bruni.core.constant.ZERO

val Float.half: Float get() = div(TWO.float)
val Float.isNegative: Boolean get() = this < ZERO.float
val Float.isPositive: Boolean get() = this > ZERO.float
val Float.negative: Float get() = -this
val Float.quarter get() = div(FOUR.float)

val Float?.isZero: Boolean get() = this == ZERO.float
val Float?.notZero: Boolean get() = isZero.not()

fun Float.toPx(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    context.resources.displayMetrics
)

fun Float.toPx(resources: Resources) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    resources.displayMetrics
)
