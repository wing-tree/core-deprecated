package com.wing.tree.bruni.core.extension

import com.wing.tree.bruni.core.constant.*

val Double.complement: Double get() = run {
    require(this in ZERO.double..ONE.double)

    ONE.minus(this)
}

val Double.half: Double get() = div(TWO.double)
val Double.hundreds: Double get() = times(ONE_HUNDRED.double)
val Double.isNegative: Boolean get() = this < ZERO.double
val Double.isNotNegative: Boolean get() = this >= ZERO.double
val Double.isNotPositive: Boolean get() = this <= ZERO.double
val Double.isPositive: Boolean get() = this > ZERO.double
val Double.negative: Double get() = -this
val Double.reciprocal get() = ONE.double.div(this)
val Double.quarter get() = div(FOUR.double)
val Double.thousands: Double get() = times(ONE_THOUSAND.double)

val Double?.isZero: Boolean get() = this == ZERO.double
val Double?.notZero: Boolean get() = isZero.not()

fun Double.safeDiv(other: Double): Double {
    return if (other.isZero) {
        this
    } else {
        div(other)
    }
}

fun Double.safeDiv(other: Int): Double {
    return if (other.isZero) {
        this
    } else {
        div(other)
    }
}

fun Double.safeDiv(other: Long): Double {
    return if (other.isZero) {
        this
    } else {
        div(other)
    }
}
