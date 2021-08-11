package com.mx.skinchange.models

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import androidx.annotation.StyleableRes
import com.mx.skinchange.base.BaseAttr
import com.mx.skinchange.resource.MXSkinResource

class AttrItem {
    companion object {
        private fun getResourceId(
            typedArray: TypedArray,
            @StyleableRes index: Int,
            defaultId: Int = BaseAttr.INVALID_ID
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

    private var isAttrEnable = true
    private var resourceId: Int = BaseAttr.INVALID_ID

    fun init(
        typedArray: TypedArray,
        @StyleableRes index: Int,
        @StyleableRes index2: Int? = null,
        @StyleableRes index3: Int? = null
    ) {
        resourceId = getResourceId(typedArray, index)
        if (resourceId == BaseAttr.INVALID_ID && index2 != null) {
            resourceId = getResourceId(typedArray, index2)
        }
        if (resourceId == BaseAttr.INVALID_ID && index3 != null) {
            resourceId = getResourceId(typedArray, index3)
        }
        isAttrEnable = true
    }

    fun setResourceId(resId: Int) {
        resourceId = resId
        isAttrEnable = true
    }

    fun apply(context: Context) {
        if (!isAttrEnable) return
        val resId = resourceId
        if (resId == BaseAttr.INVALID_ID) return

        when {
            colorApply != null -> {
                colorApply?.let { call ->
                    val color = MXSkinResource.getColorSafe(context, resId)
                    if (color != null) {
                        call.invoke(color)
                    }
                }
            }
            colorStateListApply != null -> {
                colorStateListApply?.let { call ->
                    val colorStateList = MXSkinResource.getColorStateListSafe(context, resId)
                    if (colorStateList != null) {
                        call.invoke(colorStateList)
                    }
                }
            }
            drawableApply != null -> {
                drawableApply?.let { call ->
                    val drawable = MXSkinResource.getDrawableSafe(context, resId)
                    if (drawable != null) {
                        call.invoke(drawable)
                    }
                }
            }
        }

        isAttrEnable = true
    }

    private var colorApply: ((Int) -> Unit)? = null
    fun onApplyColor(call: ((color: Int) -> Unit)) {
        colorApply = call
    }

    private var drawableApply: ((Drawable) -> Unit)? = null
    fun onApplyDrawable(call: ((drawable: Drawable) -> Unit)) {
        drawableApply = call
    }

    private var colorStateListApply: ((ColorStateList) -> Unit)? = null
    fun onApplyColorStateList(call: ((colorStateList: ColorStateList) -> Unit)) {
        colorStateListApply = call
    }

    fun getDrawable(context: Context): Drawable? {
        if (!isAttrEnable) return null
        val resId = resourceId
        if (resId == BaseAttr.INVALID_ID) return null
        return MXSkinResource.getDrawableSafe(context, resId)
    }

    fun disable() {
        isAttrEnable = false
    }

    fun enable() {
        isAttrEnable = true
    }
}