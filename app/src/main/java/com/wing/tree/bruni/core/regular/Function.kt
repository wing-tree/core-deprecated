package com.wing.tree.bruni.core.regular

inline fun <T> T?.then(block: T?.() -> Unit) = block(this)
