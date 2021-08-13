package com.mx.skinchange_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mx.skinchange.MXSkinManager
import com.mx.skinchange.factory.MXSkinViewRegister
import com.mx.skinchange_demo.customer.CView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 注册自定义View，这一步必须要有，否则皮肤信息不会生效
        MXSkinViewRegister.register(CView::class.java)

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