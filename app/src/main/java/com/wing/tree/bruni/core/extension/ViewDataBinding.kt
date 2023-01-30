package com.wing.tree.bruni.core.extension

import androidx.databinding.ViewDataBinding

fun ViewDataBinding.post(action: Runnable) {
    root.post(action)
}

fun ViewDataBinding.postDelayed(action: Runnable, delayMillis: Long) {
    root.postDelayed(action, delayMillis)
}
