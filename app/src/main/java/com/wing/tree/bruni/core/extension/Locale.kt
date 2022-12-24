package com.wing.tree.bruni.core.extension

import java.util.*

val Locale.flagEmoji: String
    get() {
        val first = Character.codePointAt(country, 0).minus(0x41).plus(0x1F1E6)
        val second = Character.codePointAt(country, 1).minus(0x41).plus(0x1F1E6)

        return String(Character.toChars(first)).plus(String(Character.toChars(second)))
    }
