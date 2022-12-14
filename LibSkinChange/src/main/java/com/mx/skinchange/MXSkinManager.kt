package com.mx.skinchange

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import androidx.core.view.LayoutInflaterCompat
import androidx.lifecycle.Lifecycle
import com.mx.skinchange.factory.MXSkinFactory
import com.mx.skinchange.utils.MXSkinObserver
import com.mx.skinchange.utils.MXSkinUtils

object MXSkinManager {
    private const val SKIN_NAME_SAVED = "MX_SKIN_CHANGE_NAME"
    private var application: Application? = null
    private val sp: SharedPreferences by lazy {
        appContext.getSharedPreferences("com.mx.skin_change_sp", Context.MODE_PRIVATE)
    }
    val appContext: Application
        get() = application!!

    private var skinName: String? = null

    /**
     * 初始化
     */
    fun init(application: Application) {
        this.application = application
        skinName = sp.getString(SKIN_NAME_SAVED, "")
    }

    fun attach(lifecycle: Lifecycle, layoutInflater: LayoutInflater) {
        val factory = MXSkinFactory()
        lifecycle.addObserver(factory)
        LayoutInflaterCompat.setFactory2(layoutInflater, factory)
    }

    /**
     * 加载皮肤
     */
    fun loadSkin(skinName: String?): Boolean {
        MXSkinUtils.log("加载皮肤：$skinName")
        if (skinName == this.skinName) return false
        this.skinName = skinName
        sp.edit().putString(SKIN_NAME_SAVED, skinName ?: "").commit()
        MXSkinObserver.notifyChange()
        return true
    }

    /**
     * 重置为默认皮肤
     */
    fun resetSkin() {
        loadSkin(null)
    }

    /**
     * 获取皮肤名称
     */
    fun getSkinName(): String = skinName ?: ""
}