package com.wing.tree.bruni.core.extension

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

fun <T> Bundle.getParcelableCompat(name: String, clazz: Class<out T>): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(name, clazz)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(name)
    }

fun <T : Parcelable> Bundle.getParcelableArrayListCompat(name: String, clazz: Class<out T>): ArrayList<T>? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayList(name, clazz)
    } else {
        @Suppress("DEPRECATION")
        getParcelableArrayList(name)
    }

inline fun <reified T : Serializable> Bundle.getSerializableCompat(name: String, clazz: Class<out T>): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(name, clazz)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(name) as? T
    }
