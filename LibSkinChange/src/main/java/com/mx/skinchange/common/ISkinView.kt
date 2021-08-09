package com.mx.skinchange.common

import android.view.View
import com.mx.skinchange.common.ISkinChange

/**
 * 皮肤View生成的基类
 */
interface ISkinView : ISkinChange {
    fun getName(): String // 获取View的名字
    fun getSelfView(): View
}