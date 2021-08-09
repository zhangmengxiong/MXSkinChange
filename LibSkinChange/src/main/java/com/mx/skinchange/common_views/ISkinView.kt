package com.mx.skinchange.common_views

import android.view.View
import com.mx.skinchange.observer.ISkinChange

/**
 * 皮肤View生成的基类
 */
interface ISkinView : ISkinChange {
    fun getName(): String // 获取View的名字
    fun getSelfView(): View
}