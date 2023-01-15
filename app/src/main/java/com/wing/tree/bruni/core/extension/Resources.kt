package com.wing.tree.bruni.core.extension

import android.content.res.Resources
import androidx.annotation.*
import androidx.core.content.res.ResourcesCompat

val Resources.configShortAnimTime: Int get() = getInteger(android.R.integer.config_shortAnimTime)
val Resources.configMediumAnimTime: Int get() = getInteger(android.R.integer.config_mediumAnimTime)
val Resources.configLongAnimTime: Int get() = getInteger(android.R.integer.config_longAnimTime)

fun Resources.dimen(@DimenRes id: Int) = getDimension(id)
fun Resources.drawable(@DrawableRes id: Int) = ResourcesCompat.getDrawable(this, id, null)
fun Resources.integer(@IntegerRes id: Int) = getInteger(id)
