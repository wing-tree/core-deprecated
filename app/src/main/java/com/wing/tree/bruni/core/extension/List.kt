package com.wing.tree.bruni.core.extension

import com.wing.tree.bruni.core.constant.ONE
import com.wing.tree.bruni.core.constant.TWO

fun <T> List<T>.findWithIndex(predicate: (T) -> Boolean): Pair<Int, T>? {
    val element = find(predicate) ?: return null
    val index = indexOf(element)

    return index to element
}

fun <T> List<T>.second() = get(ONE)
fun <T> List<T>.secondOrNull(): T? {
    return if (size < TWO) {
        null
    } else {
        get(ONE)
    }
}

fun List<*>.isPair() = size == TWO
fun List<*>.isSingle() = size == ONE
