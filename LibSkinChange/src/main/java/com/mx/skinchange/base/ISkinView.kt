package com.mx.skinchange.base

import android.view.View

/**
 * 皮肤View生成的基类
 */
interface ISkinView : ISkinChange {
    fun getName(): String // 获取View的名字
    fun getSelfView(): View
    fun needObserved(): Boolean = false // 当前View是否需要被加入监听列表
}