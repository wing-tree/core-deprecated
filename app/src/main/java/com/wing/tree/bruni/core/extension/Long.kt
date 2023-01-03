package com.wing.tree.bruni.core.extension

import com.wing.tree.bruni.core.constant.*

val Long.half get() = div(TWO.long)
val Long.hundreds: Long get() = times(ONE_HUNDRED.long)
val Long.int get() = toInt()
val Long.isNegative: Boolean get() = this < ZERO.long
val Long.isPositive: Boolean get() = this > ZERO.long
val Long.negative get() = -this
val Long.quarter get() = div(FOUR.long)
val Long.thousands: Long get() = times(ONE_THOUSAND.long)

val Long?.isZero: Boolean get() = this == ZERO.long
val Long?.notZero: Boolean get() = isZero.not()
