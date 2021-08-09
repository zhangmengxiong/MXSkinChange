package com.mx.skinchange.common_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.mx.skinchange.common_attrs.AttrBackground
import com.mx.skinchange.common_attrs.AttrTextView

class CommonTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr), ISkinView {

    private val attrBackground by lazy { AttrBackground(this) }
    private val attrTextView by lazy { AttrTextView(this) }

    init {
        attrBackground.initAttrs(attrs, defStyleAttr)
        attrTextView.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return "TextView"
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onChange() {
        attrBackground.applyAttrs()
        attrTextView.applyAttrs()
    }

    override fun setTextAppearance(resId: Int) {
        this.setTextAppearance(context, resId)
    }

    override fun setTextAppearance(context: Context?, resId: Int) {
        super.setTextAppearance(context, resId)
        attrTextView.setTextAppearance(resId)
    }

    override fun setBackgroundResource(resid: Int) {
        super.setBackgroundResource(resid)
        attrBackground.setBackgroundRes(resid)
    }
}