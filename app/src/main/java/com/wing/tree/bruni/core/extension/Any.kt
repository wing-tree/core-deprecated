package com.wing.tree.bruni.core.extension

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

val Any?.string: String get() = toString()

@OptIn(ExperimentalContracts::class)
fun Any?.isNull(): Boolean {
    contract { returns(false) implies (this@isNull != null) }

    return this == null
}

@OptIn(ExperimentalContracts::class)
fun Any?.isNotNull(): Boolean {
    contract { returns(true) implies (this@isNotNull != null) }

    return this != null
}

inline fun <reified T> Any?.isOrDefault(defaultValue: T): T {
    return if (this is T) {
        this
    } else {
        defaultValue
    }
}
