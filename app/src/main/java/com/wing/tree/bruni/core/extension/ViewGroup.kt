package com.wing.tree.bruni.core.extension

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

fun ViewGroup.findViewsWithTag(tag: CharSequence): ArrayList<View> {
    val views = ArrayList<View>()

    children.forEach { child ->
        if (child is ViewGroup) {
            views.addAll(child.findViewsWithTag(tag))
        }

        child.tag?.let {
            if (it == tag) {
                views.add(child)
            }
        }
    }

    return views
}
