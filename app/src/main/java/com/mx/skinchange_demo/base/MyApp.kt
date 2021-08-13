package com.mx.skinchange_demo.base

import android.app.Application
import com.mx.skinchange.MXSkinManager
import com.mx.skinchange.factory.MXSkinViewRegister
import com.mx.skinchange_demo.customer.CView

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MXSkinManager.init(this)

        // 注册自定义View，这一步必须要有，否则皮肤信息不会生效  需要在 MXSkinManager.attach(lifecycle, layoutInflater) 前注册
        MXSkinViewRegister.register(CView::class.java)
    }
}