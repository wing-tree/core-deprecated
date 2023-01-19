package com.wing.tree.bruni.core.regular

import android.view.View
import com.wing.tree.bruni.core.extension.*

fun enable(vararg view: View) {
    view.forEach {
        it.enable()
    }
}

fun disable(vararg view: View) {
    view.forEach {
        it.disable()
    }
}

fun visible(vararg view: View) {
    view.forEach {
        it.visible()
    }
}

fun invisible(vararg view: View) {
    view.forEach {
        it.invisible()
    }
}

fun gone(vararg view: View) {
    view.forEach {
        it.gone()
    }
}
