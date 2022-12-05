package com.wing.tree.bruni.core.extension

import com.wing.tree.bruni.core.constant.FOUR
import com.wing.tree.bruni.core.constant.ONE
import com.wing.tree.bruni.core.constant.TWO
import com.wing.tree.bruni.core.constant.ZERO

val Int.half: Int get() = div(TWO)
val Int.isEven: Boolean get() = rem(TWO) == ZERO
val Int.isOdd: Boolean get() = isEven.not()
val Int.quarter get() = div(FOUR)
val Int?.isOne: Boolean get() = this == ONE
val Int?.isZero: Boolean get() = this == ZERO
val Int?.notZero: Boolean get() = isZero.not()