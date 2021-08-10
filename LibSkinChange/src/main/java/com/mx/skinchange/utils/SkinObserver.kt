package com.mx.skinchange.utils

import com.mx.skinchange.base.ISkinChange

object SkinObserver {
    private val observerList = ArrayList<ISkinChange>()
    fun addObserver(iSkinChange: ISkinChange) {
        observerList.add(iSkinChange)
    }

    fun deleteObserver(iSkinChange: ISkinChange) {
        observerList.remove(iSkinChange)
    }

    fun notifyChange() {
        observerList.toList().forEach { it.onChange() }
    }
}