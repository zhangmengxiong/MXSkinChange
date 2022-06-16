package com.mx.skinchange.resource

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.mx.skinchange.base.IResourceLoader

object MXSkinResource {
    private var resourceLoader: IResourceLoader = NameDelegateLoader()

    @JvmStatic
    fun getDrawable(context: Context, @DrawableRes resId: Int): Drawable {
        return resourceLoader.getDrawable(context, resId)!!
    }

    @JvmStatic
    fun getColor(context: Context, @ColorRes resId: Int): Int {
        return resourceLoader.getColor(context, resId)!!
    }

    @JvmStatic
    fun getColorStateList(context: Context, @ColorRes resId: Int): ColorStateList {
        return resourceLoader.getColorStateList(context, resId)!!
    }


    @JvmStatic
    fun getDrawableSafe(context: Context, @DrawableRes resId: Int): Drawable? {
        return resourceLoader.getDrawable(context, resId)
    }

    @JvmStatic
    fun getColorSafe(context: Context, @ColorRes resId: Int): Int? {
        return resourceLoader.getColor(context, resId)
    }

    @JvmStatic
    fun getColorStateListSafe(context: Context, @ColorRes resId: Int): ColorStateList? {
        return resourceLoader.getColorStateList(context, resId)
    }
}