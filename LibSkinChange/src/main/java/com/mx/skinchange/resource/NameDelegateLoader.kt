package com.mx.skinchange.resource

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.mx.skinchange.MXSkinManager
import com.mx.skinchange.base.IResourceLoader
import com.mx.skinchange.models.MXResType
import com.mx.skinchange.utils.MXSkinUtils

/**
 * 对资源名称进行替换的资源加载器
 * 示例：
 *      图片：R.drawable.app_bg
 *      皮肤名称：dark
 *      如果存在 R.drawable.app_bg_dark 资源时，返回 R.drawable.app_bg_dark ,否则返回 R.drawable.app_bg
 */
class NameDelegateLoader : IResourceLoader {
    override fun getString(context: Context, resId: Int): String? {
        return loadResource(context, resId, MXResType.ResString) {
            context.resources.getString(it)
        }
    }

    override fun getText(context: Context, resId: Int): CharSequence? {
        return loadResource(context, resId, MXResType.ResString) {
            context.resources.getText(it)
        }
    }

    override fun getDrawable(context: Context, resId: Int): Drawable? {
        return loadResource(context, resId, MXResType.ResDrawable) {
            AppCompatResources.getDrawable(context, it)
        }
    }

    override fun getColor(context: Context, resId: Int): Int? {
        return loadResource(context, resId, MXResType.ResColor) {
            context.resources.getColor(it)
        }
    }

    override fun getColorStateList(context: Context, resId: Int): ColorStateList? {
        return loadResource(context, resId, MXResType.ResColor) {
            AppCompatResources.getColorStateList(context, it)
        }
    }

    override fun getDimension(context: Context, resId: Int): Float? {
        return loadResource(context, resId, MXResType.ResDimension) {
            context.resources.getDimension(it)
        }
    }

    override fun getDimensionPixelOffset(context: Context, resId: Int): Int? {
        return loadResource(context, resId, MXResType.ResDimension) {
            context.resources.getDimensionPixelOffset(it)
        }
    }

    override fun getDimensionPixelSize(context: Context, resId: Int): Int? {
        return loadResource(context, resId, MXResType.ResDimension) {
            context.resources.getDimensionPixelSize(it)
        }
    }

    private fun <T> loadResource(
        context: Context,
        resId: Int,
        resType: MXResType,
        load: ((resId: Int) -> T?)
    ): T? {
        val skinName = MXSkinManager.getSkinName()
        val resourceName = MXSkinUtils.getResourceNameById(context, resId) ?: return null
        if (skinName.isEmpty()) {
            return load.invoke(resId)
        }
        val newResId =
            MXSkinUtils.getResourceId(context, "${resourceName}_$skinName", resType.typeName)
        if (newResId != null && newResId != 0) {
            MXSkinUtils.log("资源替换：${resType.typeName}  $resourceName  -->   ${"${resourceName}_$skinName"}")
            return load.invoke(newResId)
        }
        return load.invoke(resId)
    }
}