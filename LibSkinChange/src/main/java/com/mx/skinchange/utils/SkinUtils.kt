package com.mx.skinchange.utils

import android.content.Context

object SkinUtils {
    fun getResourceNameById(context: Context, resId: Int): String? {
        try {
            return context.resources.getResourceName(resId)
        } catch (e: Exception) {
        }
        return null
    }

    fun getResourceId(context: Context, resName: String, type: String): Int? {
        try {
            return context.resources.getIdentifier(resName, type, context.packageName)
        } catch (e: Exception) {
        }
        return null
    }
}