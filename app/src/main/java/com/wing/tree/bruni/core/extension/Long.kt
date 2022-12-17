package com.wing.tree.bruni.core.extension

import com.wing.tree.bruni.core.constant.FOUR
import com.wing.tree.bruni.core.constant.TWO
import com.wing.tree.bruni.core.constant.ZERO

val Long.half get() = div(TWO.long)
val Long.int get() = toInt()
val Long.isNegative: Boolean get() = this < ZERO.long
val Long.isPositive: Boolean get() = this > ZERO.long
val Long.negative get() = -this
val Long.quarter get() = div(FOUR.long)

val Long?.isZero: Boolean get() = this == ZERO.long
val Long?.notZero: Boolean get() = isZero.not()
