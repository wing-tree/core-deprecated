package com.wing.tree.bruni.core.extension

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import java.io.Serializable

fun <T : Parcelable> Intent.getParcelableExtraCompat(name: String, clazz: Class<out T>): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(name, clazz)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(name)
    }

fun <T : Parcelable> Intent.getParcelableArrayListExtraCompat(name: String, clazz: Class<out T>): ArrayList<T>? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayListExtra(name, clazz)
    } else {
        @Suppress("DEPRECATION")
        getParcelableArrayListExtra(name)
    }

inline fun <reified T : Serializable> Intent.getSerializableExtraCompat(name: String, clazz: Class<out T>): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(name, clazz)
    } else {
        @Suppress("DEPRECATION")
        getSerializableExtra(name) as? T
    }
