package com.mx.skinchange_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mx.skinchange.SkinManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        SkinManager.attach(lifecycle, layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}