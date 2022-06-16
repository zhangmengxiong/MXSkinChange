package com.mx.skinchange.models

import androidx.lifecycle.*
import com.mx.skinchange.base.ISkinChange
import com.mx.skinchange.utils.MXSkinObserver

class MXSkinLifecycleObserver(private val onChange: (() -> Unit)) : ISkinChange,
    LifecycleEventObserver {

    override fun onSkinChange() {
        onChange.invoke()
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                MXSkinObserver.addObserver(this)
            }
            Lifecycle.Event.ON_DESTROY -> {
                MXSkinObserver.deleteObserver(this)
            }
            else -> {}
        }
    }
}