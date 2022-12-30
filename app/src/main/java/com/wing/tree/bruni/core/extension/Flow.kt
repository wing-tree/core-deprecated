package com.wing.tree.bruni.core.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.firstOrNull

fun Flow<String>.filterNotBlank() = filter { it.isNotBlank() }
fun Flow<String>.filterNotEmpty() = filter { it.isNotEmpty() }
fun Flow<String?>.filterNotNullAndBlank() = filterNot { it.isNullOrBlank() }
fun Flow<String?>.filterNotNullAndEmpty() = filterNot { it.isNullOrEmpty() }

suspend fun <T> Flow<T?>.firstOrDefault(defaultValue: T): T = firstOrNull() ?: defaultValue
