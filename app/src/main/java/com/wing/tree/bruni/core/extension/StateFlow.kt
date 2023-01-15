package com.wing.tree.bruni.core.extension

import com.wing.tree.bruni.core.useCase.Result
import com.wing.tree.bruni.core.useCase.firstOrNull
import kotlinx.coroutines.flow.StateFlow

fun <T> StateFlow<Result<List<T>>>.firstOrNull(): T? = value.firstOrNull()
