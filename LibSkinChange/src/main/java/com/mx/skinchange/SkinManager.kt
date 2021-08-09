package com.mx.skinchange

import android.app.Application
import android.view.LayoutInflater
import androidx.core.view.LayoutInflaterCompat
import androidx.lifecycle.Lifecycle
import com.mx.skinchange.factory.SkinFactory

object SkinManager {
    private var application: Application? = null
    val appContext: Application
        get() = application!!

    fun init(application: Application) {
        this.application = application
    }

    fun attach(lifecycle: Lifecycle, layoutInflater: LayoutInflater) {
        val factory = SkinFactory()
        lifecycle.addObserver(factory)
        LayoutInflaterCompat.setFactory2(layoutInflater, factory)
    }
}