package com.wing.tree.bruni.core.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.translateDown(
    duration: Long,
    value: Float,
    onAnimationEnd: ((animation: Animator) -> Unit)? = null
) {
    this.apply {
        translationY = 0.0F

        val listener = object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                onAnimationEnd?.invoke(animation)
                super.onAnimationEnd(animation)
            }
        }

        animate()
            .translationY(value)
            .setDuration(duration)
            .setListener(listener)
            .withLayer()
    }
}

fun View.translateLeft(
    duration: Long,
    value: Float,
    onAnimationEnd: ((animation: Animator) -> Unit)? = null
) {
    this.apply {
        translationX = 0.0F

        val listener = object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                onAnimationEnd?.invoke(animation)
                super.onAnimationEnd(animation)
            }
        }

        animate()
            .translationX(-value)
            .setDuration(duration)
            .setListener(listener)
            .withLayer()
    }
}

fun View.translateRight(
    duration: Long,
    value: Float,
    onAnimationEnd: ((animation: Animator) -> Unit)? = null
) {
    this.apply {
        translationX = 0.0F

        val listener = object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                onAnimationEnd?.invoke(animation)
                super.onAnimationEnd(animation)
            }
        }

        animate()
            .translationX(value)
            .setDuration(duration)
            .setListener(listener)
            .withLayer()
    }
}

fun View.translateUp(
    duration: Long,
    value: Float,
    onAnimationEnd: ((animation: Animator) -> Unit)? = null
) {
    this.apply {
        translationY = 0.0F

        val listener = object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                onAnimationEnd?.invoke(animation)
                super.onAnimationEnd(animation)
            }
        }

        animate()
            .translationY(-value)
            .setDuration(duration)
            .setListener(listener)
            .withLayer()
    }
}