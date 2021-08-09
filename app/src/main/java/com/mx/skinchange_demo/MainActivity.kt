package com.mx.skinchange_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mx.skinchange.SkinManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        SkinManager.attach(lifecycle, layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        skinTypeGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.defaultSkin -> {
                    SkinManager.loadSkin("")
                }
                else -> {
                    SkinManager.loadSkin("dark")
                }
            }
        }
    }
}