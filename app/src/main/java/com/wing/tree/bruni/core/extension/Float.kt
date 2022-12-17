package com.wing.tree.bruni.core.extension

import com.wing.tree.bruni.core.constant.FOUR
import com.wing.tree.bruni.core.constant.TWO
import com.wing.tree.bruni.core.constant.ZERO

val Float.half: Float get() = div(TWO.float)
val Float.quarter get() = div(FOUR.float)

val Float.isPositive: Boolean get() = this > ZERO.float
val Float.isNegative: Boolean get() = this < ZERO.float

val Float?.isZero: Boolean get() = this == ZERO.float
val Float?.notZero: Boolean get() = isZero.not()
