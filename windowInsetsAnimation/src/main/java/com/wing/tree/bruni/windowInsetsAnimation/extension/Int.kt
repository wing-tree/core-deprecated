package com.wing.tree.bruni.windowInsetsAnimation.extension

import androidx.core.view.WindowInsetsCompat.Type.InsetsType
import com.wing.tree.bruni.core.extension.isNotZero

internal fun Int.isMasked(@InsetsType type: Int) = and(type).isNotZero
