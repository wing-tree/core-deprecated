package com.wing.tree.bruni.core.extension

inline fun <T> T?.then(block: T?.() -> Unit) = block(this)
