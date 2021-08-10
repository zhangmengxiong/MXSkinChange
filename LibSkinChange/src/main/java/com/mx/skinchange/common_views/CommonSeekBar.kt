package com.mx.skinchange.common_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.SeekBar
import com.mx.skinchange.common.ISkinView
import com.mx.skinchange.common_attrs.AttrBackground
import com.mx.skinchange.common_attrs.AttrProgressBar
import com.mx.skinchange.common_attrs.AttrSeekBar

open class CommonSeekBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatSeekBar(
    context,
    attrs,
    if (defStyleAttr == 0) android.R.attr.seekBarStyle else defStyleAttr
), ISkinView {

    private val attrBackground by lazy { AttrBackground(this) }
    private val attrProgressBar by lazy { AttrSeekBar(this) }

    init {
        val defStyleAttr = if (defStyleAttr == 0) android.R.attr.seekBarStyle else defStyleAttr
        attrBackground.initAttrs(attrs, defStyleAttr)
        attrProgressBar.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return "SeekBar"
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onChange() {
        attrBackground.applyAttrs()
    }

    override fun setBackgroundResource(resid: Int) {
        super.setBackgroundResource(resid)
        attrBackground.setBackgroundResource(resid)
    }
}