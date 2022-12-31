package com.wing.tree.bruni.core.extension

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import com.wing.tree.bruni.core.constant.*

val Int.half: Int get() = div(TWO)
val Int.isEven: Boolean get() = rem(TWO) == ZERO
val Int.isOdd: Boolean get() = isEven.not()
val Int.isNegative: Boolean get() = this < ZERO.float
val Int.isPositive: Boolean get() = this > ZERO.float
val Int.negative get() = -this
val Int.quarter get() = div(FOUR)

val Int?.isOne: Boolean get() = this == ONE
val Int?.isZero: Boolean get() = this == ZERO
val Int?.notZero: Boolean get() = isZero.not()

val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        float,
        Resources.getSystem().displayMetrics
    )

val Int.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        float,
        Resources.getSystem().displayMetrics
    )

fun Int.toDp(context: Context): Int {
    return times(context.resources.displayMetrics.density).plus(ONE_HALF).int
}

fun Int.toDp(resources: Resources): Int {
    return times(resources.displayMetrics.density).plus(ONE_HALF).int
}

fun Int.toPx(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    float,
    context.resources.displayMetrics
)

fun Int.toPx(resources: Resources) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    float,
    resources.displayMetrics
)
