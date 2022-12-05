package com.wing.tree.bruni.core.extension

import com.wing.tree.bruni.core.constant.FOUR
import com.wing.tree.bruni.core.constant.TWO

internal val Long.half get() = div(TWO.long)
internal val Long.int get() = toInt()
internal val Long.quarter get() = div(FOUR.long)