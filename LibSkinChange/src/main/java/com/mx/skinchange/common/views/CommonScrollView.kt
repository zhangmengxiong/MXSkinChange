package com.mx.skinchange.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ScrollView
import com.mx.skinchange.base.ISkinView
import com.mx.skinchange.common.attrs.AttrBackground

open class CommonScrollView @JvmOverloads constructor(
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