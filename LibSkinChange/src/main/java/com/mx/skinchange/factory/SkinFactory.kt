package com.mx.skinchange.factory

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.LifecycleObserver

class SkinFactory : LifecycleObserver, LayoutInflater.Factory2 {
    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        val clazz = SkinViewRegister.getRegisterClass(name) ?: return null
        return SkinViewRegister.createSkinView(clazz, context, attrs)?.getSelfView()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val clazz = SkinViewRegister.getRegisterClass(name) ?: return null
        return SkinViewRegister.createSkinView(clazz, context, attrs)?.getSelfView()
    }
}