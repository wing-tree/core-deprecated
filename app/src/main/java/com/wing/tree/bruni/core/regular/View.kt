package com.wing.tree.bruni.core.regular

import android.view.View
import com.wing.tree.bruni.core.extension.*

fun enable(vararg views: View) {
    views.forEach {
        it.enable()
    }
}

fun disable(vararg views: View) {
    views.forEach {
        it.disable()
    }
}

fun visible(vararg views: View) {
    views.forEach {
        it.visible()
    }
}

fun invisible(vararg views: View) {
    views.forEach {
        it.invisible()
    }
}

fun gone(vararg views: View) {
    views.forEach {
        it.gone()
    }
}
