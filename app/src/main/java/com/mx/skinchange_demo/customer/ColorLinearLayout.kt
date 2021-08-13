package com.mx.skinchange_demo.customer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.mx.skinchange.common.views.MXSkinLinearLayout
import com.mx.skinchange_demo.R

class ColorLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : MXSkinLinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_color, this, true)
    }

    override fun needObserved(): Boolean  = true
}