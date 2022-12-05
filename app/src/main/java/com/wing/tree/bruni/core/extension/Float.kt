package com.wing.tree.bruni.core.extension

import com.wing.tree.bruni.core.constant.FOUR
import com.wing.tree.bruni.core.constant.TWO

val Float.half: Float get() = div(TWO.float)
val Float.quarter get() = div(FOUR.float)