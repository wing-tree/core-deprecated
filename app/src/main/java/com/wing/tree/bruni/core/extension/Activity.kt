package com.wing.tree.bruni.core.extension

import android.app.Activity
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.annotation.StringRes

fun Activity.checkPermission(vararg permission: String): Boolean {
    return permission.all { checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }
}

fun Activity.sharePlainText(text: CharSequence) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = MIMETYPE_TEXT_PLAIN
    }

    startActivity(Intent.createChooser(intent, null))
}

fun Activity.shouldShowRequestPermissionRationale(vararg permission: String): Boolean {
    return permission.any { shouldShowRequestPermissionRationale(it) }
}

fun Activity.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, duration).show()
}

fun Activity.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}
