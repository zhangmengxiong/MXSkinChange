package com.mx.skinchange.base

import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.StyleableRes

interface BaseAttr {
    companion object {
        const val SYSTEM_ID_PREFIX = "1"
        const val INVALID_ID = 0

        fun checkResourceId(resId: Int): Int {
            val hexResId = Integer.toHexString(resId)
            return if (hexResId.startsWith(SYSTEM_ID_PREFIX)) INVALID_ID else resId
        }

        fun getResourceId(
            typedArray: TypedArray,
            @StyleableRes index: Int,
            defaultId: Int = INVALID_ID
        ): Int {
            try {
                if (typedArray.hasValue(index)) {
                    return typedArray.getResourceId(index, defaultId)
                }
            } catch (e: Exception) {
            }
            return defaultId
        }
    }

    fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int)
    fun applyAttrs()
}
