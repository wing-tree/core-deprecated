package com.wing.tree.bruni.core.fluidContentResizer

data class InputMethodVisibilityChangedEvent(
    val isVisible: Boolean,
    val currentContentHeight: Int,
    val previousContentHeight: Int
)
