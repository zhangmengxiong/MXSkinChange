package com.mx.skinchange

import android.app.Application
import android.view.LayoutInflater
import androidx.core.view.LayoutInflaterCompat
import androidx.lifecycle.Lifecycle
import com.mx.skinchange.factory.SkinFactory
import com.mx.skinchange.observer.SkinObserver

object SkinManager {
    private var application: Application? = null
    val appContext: Application
        get() = application!!

    private var skinName: String? = "dark"

    fun init(application: Application) {
        this.application = application
    }

    fun attach(lifecycle: Lifecycle, layoutInflater: LayoutInflater) {
        val factory = SkinFactory()
        lifecycle.addObserver(factory)
        LayoutInflaterCompat.setFactory2(layoutInflater, factory)
    }

    fun loadSkin(skinName: String) {
        this.skinName = skinName
        SkinObserver.notifyChange()
    }

    fun getSkinName(): String = skinName ?: ""
}