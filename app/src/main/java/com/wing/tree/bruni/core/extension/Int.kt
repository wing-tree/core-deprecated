package com.wing.tree.bruni.core.extension

import android.content.res.Resources
import com.wing.tree.bruni.core.constant.*

val Int.dp: Int
    get() = times(Resources.getSystem().displayMetrics.density).plus(ONE_HALF).int

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
