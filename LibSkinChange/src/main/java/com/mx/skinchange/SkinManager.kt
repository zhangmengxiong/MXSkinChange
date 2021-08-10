package com.mx.skinchange

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import androidx.core.view.LayoutInflaterCompat
import androidx.lifecycle.Lifecycle
import com.mx.skinchange.factory.SkinFactory
import com.mx.skinchange.utils.SkinObserver

object SkinManager {
    private const val SKIN_NAME_SAVED = "MX_SKIN_CHANGE_NAME"
    private var application: Application? = null
    private val sp: SharedPreferences by lazy {
        appContext.getSharedPreferences("com.mx.skin_change_sp", Context.MODE_PRIVATE)
    }
    val appContext: Application
        get() = application!!

    private var skinName: String? = null

    fun init(application: Application) {
        this.application = application
        skinName = sp.getString(SKIN_NAME_SAVED, "")
    }

    fun attach(lifecycle: Lifecycle, layoutInflater: LayoutInflater) {
        val factory = SkinFactory()
        lifecycle.addObserver(factory)
        LayoutInflaterCompat.setFactory2(layoutInflater, factory)
    }

    fun loadSkin(skinName: String) {
        this.skinName = skinName
        sp.edit().putString(SKIN_NAME_SAVED, skinName).commit()
        SkinObserver.notifyChange()
    }

    fun getSkinName(): String = skinName ?: ""
}