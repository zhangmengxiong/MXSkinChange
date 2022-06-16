package com.mx.skinchange.base

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources.NotFoundException
import android.graphics.drawable.Drawable

interface IResourceLoader {
    @Throws(NotFoundException::class)
    fun getDrawable(context: Context, resId: Int): Drawable?

    @Throws(NotFoundException::class)
    fun getColor(context: Context, resId: Int): Int?

    @Throws(NotFoundException::class)
    fun getString(context: Context, resId: Int): String?

    @Throws(NotFoundException::class)
    fun getText(context: Context, resId: Int): CharSequence?

    @Throws(NotFoundException::class)
    fun getColorStateList(context: Context, resId: Int): ColorStateList?

    @Throws(NotFoundException::class)
    fun getDimension(context: Context, resId: Int): Float?

    @Throws(NotFoundException::class)
    fun getDimensionPixelOffset(context: Context, resId: Int): Int?

    @Throws(NotFoundException::class)
    fun getDimensionPixelSize(context: Context, resId: Int): Int?
}