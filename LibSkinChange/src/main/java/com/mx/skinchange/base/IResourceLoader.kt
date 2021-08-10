package com.mx.skinchange.base

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable

interface IResourceLoader {
    fun loadDrawable(context: Context, resId: Int): Drawable?
    fun loadColor(context: Context, resId: Int): Int?
    fun loadColorStateList(context: Context, resId: Int): ColorStateList?
}