package com.mx.skinchange.factory

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.mx.skinchange.common.ISkinChange
import com.mx.skinchange.utils.SkinObserver

class SkinFactory : LifecycleObserver, LayoutInflater.Factory2, ISkinChange {
    private val skinViewList = ArrayList<ISkinChange>()
    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        val clazz = SkinViewRegister.getRegisterClass(name) ?: return null
        val iSkinView = SkinViewRegister.createSkinView(clazz, context, attrs)
        if (iSkinView != null) {
            skinViewList.add(iSkinView)
        }
        return iSkinView?.getSelfView()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val clazz = SkinViewRegister.getRegisterClass(name) ?: return null
        val iSkinView = SkinViewRegister.createSkinView(clazz, context, attrs)
        if (iSkinView != null) {
            skinViewList.add(iSkinView)
        }
        return iSkinView?.getSelfView()
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    fun onCreateActivity() {
        SkinObserver.addObserver(this)
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    fun onDestroyActivity() {
        skinViewList.clear()
        SkinObserver.deleteObserver(this)
    }

    override fun onChange() {
        skinViewList.toList().forEach {
            it.onChange()
        }
    }
}