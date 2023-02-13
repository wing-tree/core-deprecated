package com.wing.tree.bruni.core.extension

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.annotation.*
import androidx.core.content.res.ResourcesCompat
import com.wing.tree.bruni.core.constant.ZERO

val Resources.configShortAnimTime: Int get() = getInteger(android.R.integer.config_shortAnimTime)
val Resources.configMediumAnimTime: Int get() = getInteger(android.R.integer.config_mediumAnimTime)
val Resources.configLongAnimTime: Int get() = getInteger(android.R.integer.config_longAnimTime)
val Resources.configShowNavigationBar: Boolean get() = run {
    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    val identifier = getIdentifier(
        "config_showNavigationBar",
        "bool",
        "android"
    )

    getBoolean(identifier)
}

val Resources.navigationBarHeight: Int get() = run {
    if (configShowNavigationBar.not()) {
        return ZERO
    }

    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    val identifier = getIdentifier(
        "navigation_bar_height",
        "dimen",
        "android"
    )

    return if (identifier.isPositive) {
        getDimensionPixelSize(identifier)
    } else {
        ZERO
    }
}


fun Resources.dimen(@DimenRes id: Int) = getDimension(id)
fun Resources.drawable(@DrawableRes id: Int) = ResourcesCompat.getDrawable(this, id, null)
fun Resources.integer(@IntegerRes id: Int) = getInteger(id)
