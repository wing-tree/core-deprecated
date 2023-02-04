package com.wing.tree.bruni.windowInsetsAnimation.extension

import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat.Type.InsetsType

internal fun WindowInsetsAnimationCompat.isTypeMasked(@InsetsType type: Int): Boolean {
    return typeMask.isMasked(type)
}
