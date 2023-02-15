package com.wing.tree.bruni.core.extension

inline fun <T> Iterable<T>.contains(predicate: (T) -> Boolean): Boolean {
    return firstOrNull(predicate).isNotNull()
}

inline fun <T> Iterable<T>.notContains(predicate: (T) -> Boolean): Boolean {
    return firstOrNull(predicate).isNull()
}
