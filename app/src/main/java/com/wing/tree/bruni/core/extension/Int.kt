package com.wing.tree.bruni.core.extension

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import com.wing.tree.bruni.core.constant.*

val Int.half: Int get() = div(TWO)
val Int.hundreds: Int get() = times(ONE_HUNDRED)
val Int.isEven: Boolean get() = rem(TWO) == ZERO
val Int.isOdd: Boolean get() = isEven.not()
val Int.isNegative: Boolean get() = this < ZERO
val Int.isNotNegative: Boolean get() = this >= ZERO
val Int.isNotPositive: Boolean get() = this <= ZERO
val Int.isPositive: Boolean get() = this > ZERO
val Int.negative get() = -this
val Int.quarter get() = div(FOUR)
val Int.thousands: Int get() = times(ONE_THOUSAND)

val Int?.isNotZero: Boolean get() = isZero.not()
val Int?.isOne: Boolean get() = this == ONE
val Int?.isZero: Boolean get() = this == ZERO

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

fun Int.safeDiv(other: Float): Float {
    return if (other.isZero) {
        this.float
    } else {
        div(other)
    }
}

fun Int.safeDiv(other: Int): Int {
    return if (other.isZero) {
        this
    } else {
        div(other)
    }
}

fun Int.safeDiv(other: Long): Long {
    return if (other.isZero) {
        this.long
    } else {
        div(other)
    }
}

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
