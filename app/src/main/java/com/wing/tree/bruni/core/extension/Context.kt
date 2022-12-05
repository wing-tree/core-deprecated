package com.wing.tree.bruni.core.extension

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.TypedValue
import com.wing.tree.bruni.core.constant.EMPTY
import com.wing.tree.bruni.core.constant.ZERO


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
    EMPTY
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

inline fun <reified T: Activity> Context.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T: Activity> Context.startActivity(block: Intent.() -> Unit) {
    startActivity(Intent(this, T::class.java).apply(block))
}

inline fun <reified T: Service> Context.startForegroundService() {
    startForegroundService(Intent(this, T::class.java))
}