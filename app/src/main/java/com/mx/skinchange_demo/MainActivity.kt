package com.mx.skinchange_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mx.skinchange.MXSkinManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        MXSkinManager.attach(lifecycle, layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        skinTypeGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.defaultSkin -> {
                    MXSkinManager.loadSkin("")
                }
                else -> {
                    MXSkinManager.loadSkin("dark")
                }
            }
        }
    }
}