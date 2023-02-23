package com.wing.tree.bruni.core.extension

import android.app.Activity
import android.app.Service
import android.content.ClipData
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.TypedArray
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.util.Size
import android.util.TypedValue
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.annotation.*
import androidx.core.content.res.ResourcesCompat
import com.wing.tree.bruni.core.constant.EMPTY
import com.wing.tree.bruni.core.constant.ZERO

private const val TAG = "Context"

val Context.configShortAnimTime: Int get() = resources.configShortAnimTime
val Context.configMediumAnimTime: Int get() = resources.configMediumAnimTime
val Context.configLongAnimTime: Int get() = resources.configLongAnimTime
val Context.configShowNavigationBar: Boolean get() = resources.configShowNavigationBar
val Context.navigationBarHeight: Int get() = resources.navigationBarHeight

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

val Context.actionBarSize: Int get() = run {
    val styledAttributes: TypedArray = theme.obtainStyledAttributes(
        intArrayOf(android.R.attr.actionBarSize)
    )

    return styledAttributes.getDimension(ZERO, ZERO.float).also {
        styledAttributes.recycle()
    }.int
}

val Context.colorSurface: Int
    @ColorInt get() = resolveAttribute(com.google.android.material.R.attr.colorSurface).data

val Context.colorOnSurface: Int
    @ColorInt get() = resolveAttribute(com.google.android.material.R.attr.colorOnSurface).data

val Context.colorSurfaceVariant: Int
    @ColorInt get() = resolveAttribute(com.google.android.material.R.attr.colorSurfaceVariant).data

val Context.colorOnSurfaceVariant: Int
    @ColorInt get() = resolveAttribute(com.google.android.material.R.attr.colorOnSurfaceVariant).data

val Context.colorOnBackground: Int
    @ColorInt get() = resolveAttribute(com.google.android.material.R.attr.colorOnBackground).data

val Context.accelerateCubicInterpolator: Interpolator
    get() = AnimationUtils.loadInterpolator(this, android.R.interpolator.accelerate_cubic)

val Context.accelerateQuadInterpolator: Interpolator
    get() = AnimationUtils.loadInterpolator(this, android.R.interpolator.accelerate_quad)

val Context.accelerateQuintInterpolator: Interpolator
    get() = AnimationUtils.loadInterpolator(this, android.R.interpolator.accelerate_quint)

val Context.decelerateCubicInterpolator: Interpolator
    get() = AnimationUtils.loadInterpolator(this, android.R.interpolator.decelerate_cubic)

val Context.decelerateQuadInterpolator: Interpolator
    get() = AnimationUtils.loadInterpolator(this, android.R.interpolator.decelerate_quad)

val Context.decelerateQuintInterpolator: Interpolator
    get() = AnimationUtils.loadInterpolator(this, android.R.interpolator.decelerate_quint)

val Context.displaySize: Size
    get() = run {
        val windowManager = getSystemService(WindowManager::class.java)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val currentWindowMetrics = windowManager.currentWindowMetrics
            val typeMask = WindowInsets.Type.displayCutout()
                .or(WindowInsets.Type.systemBars())
            val windowInsets = currentWindowMetrics.windowInsets
            val insets = windowInsets.getInsetsIgnoringVisibility(typeMask)

            with(currentWindowMetrics.bounds) {
                val width = width()
                    .minus(insets.left)
                    .minus(insets.right)

                val height = height()
                    .minus(insets.top)
                    .minus(insets.bottom)

                Size(width, height)
            }
        } else {
            val displayMetrics = DisplayMetrics()

            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getMetrics(displayMetrics)

            with(displayMetrics) {
                Size(widthPixels, heightPixels)
            }
        }
    }

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

fun Context.anim(@AnimRes id: Int) = AnimationUtils.loadAnimation(this, id)
fun Context.boolean(@BoolRes id: Int) = resources.getBoolean(id)
fun Context.dimen(@DimenRes id: Int) = resources.getDimension(id)
fun Context.drawable(@DrawableRes id: Int) = resources.drawable(id)
fun Context.float(@DimenRes id: Int) = resources.float(id)
fun Context.font(@FontRes id: Int) = ResourcesCompat.getFont(this, id)
fun Context.integer(@IntegerRes id: Int) = resources.getInteger(id)
fun Context.string(@StringRes id: Int) = getString(id)

fun Context.copyPlainTextToClipboard(text: CharSequence) {
    val clipboardManager = getSystemService(ClipboardManager::class.java)
    val clipData = ClipData.newPlainText(MIMETYPE_TEXT_PLAIN, text)

    clipboardManager.setPrimaryClip(clipData)
}

fun Context.pastePlanTextFromClipboard(): CharSequence? {
    val clipboardManager = getSystemService(ClipboardManager::class.java)
    val hasPrimaryClip = clipboardManager.hasPrimaryClip()
    val hasMimeType = clipboardManager.primaryClipDescription?.hasMimeType(MIMETYPE_TEXT_PLAIN) ?: false
    val primaryClip = clipboardManager.primaryClip

    return if (hasPrimaryClip.and(hasMimeType)) {
        primaryClip?.getItemAt(ZERO)?.text
    } else {
        null
    }
}

fun Context.pasteTextFromClipboard(): CharSequence? {
    val clipboardManager = getSystemService(ClipboardManager::class.java)
    val hasPrimaryClip = clipboardManager.hasPrimaryClip()
    val primaryClip = clipboardManager.primaryClip
    val item  = primaryClip?.getItemAt(ZERO)

    return if (hasPrimaryClip) {
        item?.coerceToText(this)
    } else {
        null
    }
}

fun Context.resolveAttribute(resid: Int): TypedValue {
    val typedValue = TypedValue()

    theme.resolveAttribute(
        resid,
        typedValue,
        true
    )

    return typedValue
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
