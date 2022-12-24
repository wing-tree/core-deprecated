package com.wing.tree.bruni.core.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

suspend fun <T> Flow<T?>.firstOrDefault(defaultValue: T): T = firstOrNull() ?: defaultValue
