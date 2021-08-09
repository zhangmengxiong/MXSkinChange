package com.mx.skinchange.common_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.mx.skinchange.common_attrs.AttrBackground

class CommonRelativeLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), ISkinView {

    private val backgroundAttr by lazy { AttrBackground(this) }

    init {
        backgroundAttr.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return "RelativeLayout"
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onChange() {
        backgroundAttr.applyAttrs()
    }

    override fun setBackgroundResource(resid: Int) {
        super.setBackgroundResource(resid)
        backgroundAttr.setBackgroundRes(resid)
    }
}