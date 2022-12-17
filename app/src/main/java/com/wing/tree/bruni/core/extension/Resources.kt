package com.wing.tree.bruni.core.extension

import android.content.res.Resources

val Resources.configShortAnimTime: Int get() = getInteger(android.R.integer.config_shortAnimTime)
val Resources.configMediumAnimTime: Int get() = getInteger(android.R.integer.config_mediumAnimTime)
val Resources.configLongAnimTime: Int get() = getInteger(android.R.integer.config_longAnimTime)
