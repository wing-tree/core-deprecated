package com.wing.tree.bruni.core.extension

fun <T> MutableList<T>.replaceAt(index: Int, element: T) {
    removeAt(index)
    add(index, element)
}
