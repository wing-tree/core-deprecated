package com.wing.tree.bruni.core.extension

import android.util.Log
import com.wing.tree.bruni.core.constant.EMPTY
import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.reflect.KClass

private const val TAG = "KClass"

fun <T : Any> KClass<T>.getDeclaredField(name: String): Field =
    java.getDeclaredField(name).apply {
        isAccessible = true
    }

fun KClass<*>.getDeclaredFieldOrNull(vararg name: String): Field? {
    name.forEach {
        return try{
            getDeclaredField(it).apply {
                isAccessible = true
            }
        } catch (noSuchFieldException: NoSuchFieldException) {
            Log.e(TAG, noSuchFieldException.message ?: EMPTY)
            null
        }
    }

    return null
}

fun <T : Any> KClass<T>.getDeclaredMethod(name: String, vararg parameterTypes: Class<*>): Method =
    java.getDeclaredMethod(name, *parameterTypes).apply {
        isAccessible = true
    }
