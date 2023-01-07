package com.wing.tree.bruni.core.regular

import java.util.*

fun findDisplayLanguageByLanguage(language: String): String? {
    return findLocaleByLanguage(language)?.displayLanguage
}

fun findLanguageTagByLanguage(language: String): String? {
    return findLocaleByLanguage(language)?.toLanguageTag()
}

fun findLocaleByLanguage(language: String): Locale? {
    return Locale.getAvailableLocales().find {
        it.language == language
    }
}

inline fun <T> T?.then(block: T?.() -> Unit) = block(this)
