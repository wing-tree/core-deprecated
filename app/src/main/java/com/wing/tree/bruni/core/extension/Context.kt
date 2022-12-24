package com.wing.tree.bruni.core.extension

import android.app.Activity
import android.app.Service
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.util.TypedValue
import androidx.annotation.ColorInt
import com.wing.tree.bruni.core.constant.EMPTY
import com.wing.tree.bruni.core.constant.ZERO

private const val TAG = "Context"

val Context.colorPrimary: Int @ColorInt get() = with(TypedValue()) {
    theme.resolveAttribute(
        com.google.android.material.R.attr.colorPrimary,
        this,
        true
    )

    data
}

val Context.colorOnPrimary: Int @ColorInt get() = with(TypedValue()) {
    theme.resolveAttribute(
        com.google.android.material.R.attr.colorOnPrimary,
        this,
        true
    )

    data
}

val Context.colorTertiary: Int @ColorInt get() = with(TypedValue()) {
    theme.resolveAttribute(
        com.google.android.material.R.attr.colorTertiary,
        this,
        true
    )

    data
}

val Context.colorOnTertiary: Int @ColorInt get() = with(TypedValue()) {
    theme.resolveAttribute(
        com.google.android.material.R.attr.colorOnTertiary,
        this,
        true
    )

    data
}

val Context.colorSurface: Int
    @ColorInt get() = resolveAttribute(com.google.android.material.R.attr.colorSurface)

val Context.colorSurfaceVariant: Int
    @ColorInt get() = resolveAttribute(com.google.android.material.R.attr.colorSurfaceVariant)

val Context.packageInfo: PackageInfo get() = with(packageManager) {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val flags = PackageManager.PackageInfoFlags.of(ZERO.long)

        getPackageInfo(packageName, flags)
    } else {
        @Suppress("DEPRECATION")
        getPackageInfo(packageName, ZERO)
    }
}

val Context.versionName: String get() = try {
    packageInfo.versionName
} catch (nameNotFoundException: PackageManager.NameNotFoundException) {
    Log.e(TAG, nameNotFoundException.message ?: EMPTY)
    EMPTY
}

fun Context.copyToClipboard(label: CharSequence, text: CharSequence) {
    val clipboardManager = getSystemService(ClipboardManager::class.java)
    val clipData = ClipData.newPlainText(label, text)

    clipboardManager.setPrimaryClip(clipData)
}

fun Context.resolveAttribute(resid: Int): Int {
    val typedValue = TypedValue()

    theme.resolveAttribute(
        resid,
        typedValue,
        true
    )

    return typedValue.data
}

fun Context.toPx(value: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    value,
    resources.displayMetrics
)

inline fun <reified T: Activity> Context.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T: Activity> Context.startActivity(block: Intent.() -> Unit) {
    startActivity(Intent(this, T::class.java).apply(block))
}

inline fun <reified T: Service> Context.startForegroundService() {
    startForegroundService(Intent(this, T::class.java))
}
