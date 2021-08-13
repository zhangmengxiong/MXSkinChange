package com.mx.skinchange.models

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.mx.skinchange.base.ISkinChange
import com.mx.skinchange.utils.MXSkinObserver

class MXSkinLifecycleObserver(val onChange: (() -> Unit)) : ISkinChange, LifecycleObserver {
    override fun onSkinChange() {
        onChange.invoke()
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    fun onCreateActivity() {
        MXSkinObserver.addObserver(this)
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    fun onDestroyActivity() {
        MXSkinObserver.deleteObserver(this)
    }
}