package com.wing.tree.bruni.core.extension

import java.io.File

val File.isNotDirectory: Boolean get() = isDirectory.not()

fun File.notExists() = exists().not()
