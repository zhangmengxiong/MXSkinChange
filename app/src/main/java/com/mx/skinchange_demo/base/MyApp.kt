package com.mx.skinchange_demo.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mx.skinchange.SkinManager

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SkinManager.init(this)
    }
}