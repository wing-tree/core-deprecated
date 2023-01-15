package com.wing.tree.bruni.core.extension

import android.content.Context
import android.content.res.Resources
import android.view.animation.AnimationUtils
import androidx.annotation.*
import androidx.core.content.res.ResourcesCompat
import androidx.viewbinding.ViewBinding

val ViewBinding.context: Context get() = root.context
val ViewBinding.resources: Resources get() = root.resources

val ViewBinding.configShortAnimTime: Int get() = resources.getInteger(android.R.integer.config_shortAnimTime)
val ViewBinding.configMediumAnimTime: Int get() = resources.getInteger(android.R.integer.config_mediumAnimTime)
val ViewBinding.configLongAnimTime: Int get() = resources.getInteger(android.R.integer.config_longAnimTime)

fun ViewBinding.anim(@AnimRes id: Int) = AnimationUtils.loadAnimation(context, id)
fun ViewBinding.dimen(@DimenRes id: Int) = resources.getDimension(id)
fun ViewBinding.drawable(@DrawableRes id: Int) = ResourcesCompat.getDrawable(resources, id, null)
fun ViewBinding.font(@FontRes id: Int) = ResourcesCompat.getFont(context, id)
fun ViewBinding.integer(@IntegerRes id: Int) = resources.getInteger(id)

inline fun <reified T> ViewBinding.withIsInstance(block: T.() -> Unit) {
    if (this is T) {
        block()
    }
}
