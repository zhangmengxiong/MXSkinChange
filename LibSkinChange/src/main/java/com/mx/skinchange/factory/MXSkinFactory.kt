package com.mx.skinchange.factory

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.mx.skinchange.base.ISkinChange
import com.mx.skinchange.utils.MXSkinObserver

class MXSkinFactory : LifecycleObserver, LayoutInflater.Factory2, ISkinChange {
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

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    fun onCreateActivity() {
        MXSkinObserver.addObserver(this)
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    fun onDestroyActivity() {
        skinViewList.clear()
        MXSkinObserver.deleteObserver(this)
    }

    override fun onSkinChange() {
        skinViewList.toList().forEach {
            it.onSkinChange()
        }
    }
}