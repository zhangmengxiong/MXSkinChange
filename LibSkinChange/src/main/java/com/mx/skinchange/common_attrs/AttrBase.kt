package com.mx.skinchange.common_attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.StyleableRes
import com.mx.skinchange.R
import java.lang.Exception

interface AttrBase {
    companion object {
        const val SYSTEM_ID_PREFIX = "1"
        const val INVALID_ID = 0
    }

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
            if (typedArray.hasValue(R.styleable.AttrTextView_android_textColor)) {
                return typedArray.getResourceId(
                    R.styleable.AttrTextView_android_textColor,
                    defaultId
                )
            }
        } catch (e: Exception) {
        }
        return defaultId
    }

    fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int)
    fun applyAttrs()
}
