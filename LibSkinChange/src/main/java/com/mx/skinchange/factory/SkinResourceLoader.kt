package com.mx.skinchange.factory

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import com.mx.skinchange.SkinManager
import com.mx.skinchange.utils.Log
import com.mx.skinchange.utils.SkinUtils

object SkinResourceLoader {
    const val TYPE_COLOR = "color"
    const val TYPE_DRAWABLE = "drawable"


    fun loadSkinResourceId(context: Context, resId: Int, resType: String): Int {
        val skinName = SkinManager.getSkinName()
        if (skinName.isEmpty()) {
            return resId
        }
        val resourceName = SkinUtils.getResourceNameById(context, resId) ?: return resId
        val newResId = SkinUtils.getResourceId(context, resourceName + "_" + skinName, resType)
        if (newResId != null && newResId != 0) {
            Log("资源替换：$resType  $resourceName  -->   ${resourceName + skinName}")
            return newResId
        }
        return resId
    }

    fun loadDrawable(context: Context, resId: Int): Drawable? {
        return loadResource(context, resId, TYPE_DRAWABLE) {
            context.resources.getDrawable(resId)
        }
    }

    fun loadColor(context: Context, resId: Int): Int? {
        return loadResource(context, resId, TYPE_COLOR) {
            context.resources.getColor(resId)
        }
    }

    fun loadColorStateList(context: Context, resId: Int): ColorStateList? {
        return loadResource(context, resId, TYPE_COLOR) {
            context.resources.getColorStateList(resId)
        }
    }

    private fun <T> loadResource(
        context: Context,
        resId: Int,
        resType: String,
        load: ((resId: Int) -> T?)
    ): T? {
        val skinName = SkinManager.getSkinName()
        val resourceName = SkinUtils.getResourceNameById(context, resId) ?: return null
        if (skinName.isEmpty()) {
            return load.invoke(resId)
        }
        val newResId = SkinUtils.getResourceId(context, resourceName + skinName, resType)
        return load.invoke(newResId ?: resId)
    }
}