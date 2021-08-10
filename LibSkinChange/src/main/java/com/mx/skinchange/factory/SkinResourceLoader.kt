package com.mx.skinchange.factory

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.mx.skinchange.SkinManager
import com.mx.skinchange.base.IResourceLoader
import com.mx.skinchange.utils.Log
import com.mx.skinchange.utils.SkinUtils

object SkinResourceLoader : IResourceLoader {
    private const val TYPE_COLOR = "color"
    private const val TYPE_DRAWABLE = "drawable"

    override fun loadDrawable(context: Context, resId: Int): Drawable? {
        return loadResource(context, resId, TYPE_DRAWABLE) {
            AppCompatResources.getDrawable(context, it)
        }
    }

    override fun loadColor(context: Context, resId: Int): Int? {
        return loadResource(context, resId, TYPE_COLOR) {
            context.resources.getColor(it)
        }
    }

    override fun loadColorStateList(context: Context, resId: Int): ColorStateList? {
        return loadResource(context, resId, TYPE_COLOR) {
            AppCompatResources.getColorStateList(context, it)
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
        val newResId = SkinUtils.getResourceId(context, "${resourceName}_$skinName", resType)
        if (newResId != null && newResId != 0) {
            Log("资源替换：$resType  $resourceName  -->   ${"${resourceName}_$skinName"}")
            return load.invoke(newResId)
        }
        return load.invoke(resId)
    }
}