package com.mx.skinchange.observer

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