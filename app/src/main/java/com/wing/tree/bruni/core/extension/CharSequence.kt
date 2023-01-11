package com.wing.tree.bruni.core.extension

inline fun CharSequence.ifNotBlank(crossinline block: (CharSequence) -> Unit) {
    if (isNotBlank()) {
        block(this)
    }
}

inline fun CharSequence.ifNotEmpty(crossinline block: (CharSequence) -> Unit) {
    if (isNotEmpty()) {
        block(this)
    }
}
