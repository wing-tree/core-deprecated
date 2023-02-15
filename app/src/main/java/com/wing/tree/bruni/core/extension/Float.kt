package com.wing.tree.bruni.core.extension

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import com.wing.tree.bruni.core.constant.*

val Float.complement: Float get() = run {
    require(this in ZERO.float..ONE.float)

    ONE.minus(this)
}

val Float.half: Float get() = div(TWO.float)
val Float.hundreds: Float get() = times(ONE_HUNDRED.float)
val Float.isNegative: Boolean get() = this < ZERO.float
val Float.isNotNegative: Boolean get() = this >= ZERO.float
val Float.isNotPositive: Boolean get() = this <= ZERO.float
val Float.isPositive: Boolean get() = this > ZERO.float
val Float.negative: Float get() = -this
val Float.quarter get() = div(FOUR.float)
val Float.thousands: Float get() = times(ONE_THOUSAND.float)

val Float.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Float.sp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

val Float?.isZero: Boolean get() = this == ZERO.float
val Float?.notZero: Boolean get() = isZero.not()

fun Float.safeDiv(other: Float): Float {
    return if (other.isZero) {
        this
    } else {
        div(other)
    }
}

fun Float.safeDiv(other: Int): Float {
    return if (other.isZero) {
        this
    } else {
        div(other)
    }
}

fun Float.safeDiv(other: Long): Float {
    return if (other.isZero) {
        this
    } else {
        div(other)
    }
}

fun Float.toDp(context: Context): Float {
    return times(context.resources.displayMetrics.density).plus(ONE_HALF)
}

fun Float.toDp(resources: Resources): Float {
    return times(resources.displayMetrics.density).plus(ONE_HALF)
}

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
