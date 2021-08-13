package com.mx.skinchange.utils

import androidx.lifecycle.Lifecycle
import com.mx.skinchange.base.ISkinChange
import com.mx.skinchange.models.MXSkinLifecycleObserver

object MXSkinObserver {
    private val observerList = ArrayList<ISkinChange>()
    fun addObserver(iSkinChange: ISkinChange) {
        if (!observerList.contains(iSkinChange)) {
            observerList.add(iSkinChange)
            MXSkinUtils.log("observer size = ${observerList.size}")
        }
    }

    fun deleteObserver(iSkinChange: ISkinChange) {
        observerList.remove(iSkinChange)
        MXSkinUtils.log("observer size = ${observerList.size}")
    }

    fun notifyChange() {
        observerList.toList().forEach { it.onSkinChange() }
        MXSkinUtils.log("observer size = ${observerList.size}")
    }

    fun observerByLifecycle(lifecycle: Lifecycle, onChange: (() -> Unit)) {
        lifecycle.addObserver(MXSkinLifecycleObserver(onChange))
    }
}