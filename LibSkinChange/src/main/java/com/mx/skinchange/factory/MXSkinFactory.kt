package com.mx.skinchange.factory

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.mx.skinchange.base.ISkinChange
import com.mx.skinchange.utils.MXSkinObserver

class MXSkinFactory : LifecycleEventObserver, LayoutInflater.Factory2, ISkinChange {
    private val skinViewList = ArrayList<ISkinChange>()
    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        val clazz = MXSkinViewRegister.getRegisterClass(name) ?: return null
        val iSkinView = MXSkinViewRegister.createSkinView(clazz, context, attrs)
        if (iSkinView != null) {
            skinViewList.add(iSkinView)
        }
        return iSkinView?.getSelfView()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val clazz = MXSkinViewRegister.getRegisterClass(name) ?: return null
        val iSkinView = MXSkinViewRegister.createSkinView(clazz, context, attrs)
        if (iSkinView != null) {
            skinViewList.add(iSkinView)
        }
        return iSkinView?.getSelfView()
    }

    override fun onSkinChange() {
        skinViewList.toList().forEach {
            it.onSkinChange()
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                MXSkinObserver.addObserver(this)
            }
            Lifecycle.Event.ON_DESTROY -> {
                skinViewList.clear()
                MXSkinObserver.deleteObserver(this)
            }
            else -> {}
        }
    }
}