package com.mx.skinchange.common_views

import android.view.View

/**
 * 皮肤View生成的基类
 */
interface ISkinView {
    fun getName(): String // 获取View的名字
    fun getSelfView(): View
    fun onUpdate() // 皮肤更新了
}