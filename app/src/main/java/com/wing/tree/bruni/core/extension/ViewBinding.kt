package com.wing.tree.bruni.core.extension

import android.content.Context
import android.content.res.Resources
import androidx.viewbinding.ViewBinding

val ViewBinding.context: Context get() = root.context
val ViewBinding.resources: Resources get() = root.resources

inline fun <reified T> ViewBinding.withIsInstance(block: T.() -> Unit) {
    if (this is T) {
        block()
    }
}
