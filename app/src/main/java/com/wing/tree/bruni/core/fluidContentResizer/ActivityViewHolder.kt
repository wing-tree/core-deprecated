package com.wing.tree.bruni.core.fluidContentResizer

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.wing.tree.bruni.core.constant.ZERO
import com.wing.tree.bruni.core.extension.isPositive

internal data class ActivityViewHolder(
    val decorView: ViewGroup,
    val content: ViewGroup,
    val contentParent: ViewGroup,
    val navigationBarHeight: Int
) {
    fun interface OnDetachedListener {
        fun onDetached(v: View?)
    }

    fun setOnDetachedListener(onDetachedListener: OnDetachedListener) {
        decorView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewDetachedFromWindow(v: View) {
                onDetachedListener.onDetached(v)
            }

            override fun onViewAttachedToWindow(v: View) = Unit
        })
    }

    companion object {
        @SuppressLint("InternalInsetResource", "DiscouragedApi")
        fun from(activity: Activity): ActivityViewHolder {
            val decorView = activity.window.decorView as ViewGroup
            val content = decorView.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT)
            val actionBarRoot = content.parent as ViewGroup
            val contentParent = actionBarRoot.parent as ViewGroup

            val identifier = activity.resources.getIdentifier(
                "navigation_bar_height",
                "dimen",
                "android"
            )

            val navigationBarHeight = if (identifier.isPositive) {
                activity.resources.getDimensionPixelSize(identifier)
            } else {
                ZERO
            }

            return ActivityViewHolder(
                decorView = decorView,
                content = content,
                contentParent = contentParent,
                navigationBarHeight = navigationBarHeight
            )
        }
    }
}
