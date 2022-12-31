package com.wing.tree.bruni.core.extension

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import com.wing.tree.bruni.core.constant.FOUR
import com.wing.tree.bruni.core.constant.ONE_HALF
import com.wing.tree.bruni.core.constant.TWO
import com.wing.tree.bruni.core.constant.ZERO

val Float.half: Float get() = div(TWO.float)
val Float.isNegative: Boolean get() = this < ZERO.float
val Float.isPositive: Boolean get() = this > ZERO.float
val Float.negative: Float get() = -this
val Float.quarter get() = div(FOUR.float)

val Float.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Float.sp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

val Float?.isZero: Boolean get() = this == ZERO.float
val Float?.notZero: Boolean get() = isZero.not()

fun Float.toDp(context: Context): Float {
    return times(context.resources.displayMetrics.density).plus(ONE_HALF)
}

fun Float.toDp(resources: Resources): Float {
    return times(resources.displayMetrics.density).plus(ONE_HALF)
}

fun Float.toPx(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    context.resources.displayMetrics
)

fun Float.toPx(resources: Resources) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    resources.displayMetrics
)
