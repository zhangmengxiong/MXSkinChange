package com.mx.skinchange.common_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ScrollView
import com.mx.skinchange.common.ISkinView
import com.mx.skinchange.common_attrs.AttrBackground

class CommonScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr), ISkinView {

    private val attrBackground by lazy { AttrBackground(this) }

    init {
        attrBackground.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return "ScrollView"
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