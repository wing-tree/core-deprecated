package com.wing.tree.bruni.core.extension

val String.flagEmoji: String
    get() {
        val first = Character.codePointAt(this, 0).minus(0x41).plus(0x1F1E6)
        val second = Character.codePointAt(this, 1).minus(0x41).plus(0x1F1E6)

        return String(Character.toChars(first)).plus(String(Character.toChars(second)))
    }