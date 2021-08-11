package com.mx.skinchange_demo.base

import android.app.Application
import com.mx.skinchange.MXSkinManager

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MXSkinManager.init(this)
    }
}