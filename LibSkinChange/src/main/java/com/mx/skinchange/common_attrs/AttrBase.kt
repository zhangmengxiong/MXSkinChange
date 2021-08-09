package com.mx.skinchange.common_attrs

import android.util.AttributeSet

interface AttrBase {
    companion object {
        const val SYSTEM_ID_PREFIX = "1"
        const val INVALID_ID = 0
    }

    fun checkResourceId(resId: Int): Int {
        val hexResId = Integer.toHexString(resId)
        return if (hexResId.startsWith(SYSTEM_ID_PREFIX)) INVALID_ID else resId
    }

    fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int)
    fun applyAttrs()
}
