package com.wing.tree.bruni.core.extension

import com.wing.tree.bruni.core.constant.*

val Long.half get() = div(TWO.long)
val Long.hundreds: Long get() = times(ONE_HUNDRED.long)
val Long.int get() = toInt()
val Long.isNegative: Boolean get() = this < ZERO.long
val Long.isNotNegative: Boolean get() = this >= ZERO.long
val Long.isNotPositive: Boolean get() = this <= ZERO.long
val Long.isPositive: Boolean get() = this > ZERO.long
val Long.negative get() = -this
val Long.quarter get() = div(FOUR.long)
val Long.thousands: Long get() = times(ONE_THOUSAND.long)

val Long?.isNotZero: Boolean get() = isZero.not()
val Long?.isZero: Boolean get() = this == ZERO.long

fun Long.safeDiv(other: Float): Float {
    return if (other.isZero) {
        this.float
    } else {
        div(other)
    }
}

fun Long.safeDiv(other: Int): Long {
    return if (other.isZero) {
        this
    } else {
        div(other)
    }
}

fun Long.safeDiv(other: Long): Long {
    return if (other.isZero) {
        this
    } else {
        div(other)
    }
}
