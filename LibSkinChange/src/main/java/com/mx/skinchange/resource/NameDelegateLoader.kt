package com.mx.skinchange.resource

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.mx.skinchange.MXSkinManager
import com.mx.skinchange.base.IResourceLoader
import com.mx.skinchange.utils.MXSkinUtils

/**
 * 对资源名称进行替换的资源加载器
 * 示例：
 *      图片：R.drawable.app_bg
 *      皮肤名称：dark
 *      如果存在 R.drawable.app_bg_dark 资源时，返回 R.drawable.app_bg_dark ,否则返回 R.drawable.app_bg
 */
class NameDelegateLoader : IResourceLoader {
    private val TYPE_COLOR = "color"
    private val TYPE_DRAWABLE = "drawable"

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
        val skinName = MXSkinManager.getSkinName()
        val resourceName = MXSkinUtils.getResourceNameById(context, resId) ?: return null
        if (skinName.isEmpty()) {
            return load.invoke(resId)
        }
        val newResId = MXSkinUtils.getResourceId(context, "${resourceName}_$skinName", resType)
        if (newResId != null && newResId != 0) {
            MXSkinUtils.log("资源替换：$resType  $resourceName  -->   ${"${resourceName}_$skinName"}")
            return load.invoke(newResId)
        }
        return load.invoke(resId)
    }
}