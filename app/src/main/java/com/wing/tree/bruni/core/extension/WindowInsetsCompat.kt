package com.wing.tree.bruni.core.extension

import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type.InsetsType

fun WindowInsetsCompat.getLeft(@InsetsType typeMask: Int) = getInsets(typeMask).left
fun WindowInsetsCompat.getTop(@InsetsType typeMask: Int) = getInsets(typeMask).top
fun WindowInsetsCompat.getRight(@InsetsType typeMask: Int) = getInsets(typeMask).right
fun WindowInsetsCompat.getBottom(@InsetsType typeMask: Int) = getInsets(typeMask).bottom
