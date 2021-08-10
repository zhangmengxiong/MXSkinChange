package com.mx.skinchange.common_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.mx.skinchange.common.ISkinView
import com.mx.skinchange.common_attrs.AttrBackground
import com.mx.skinchange.common_attrs.AttrImageView

open class CommonImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr), ISkinView {

    private val attrBackground by lazy { AttrBackground(this) }
    private val attrImageView by lazy { AttrImageView(this) }

    init {
        attrBackground.initAttrs(attrs, defStyleAttr)
        attrImageView.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return "ImageView"
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onChange() {
        attrBackground.applyAttrs()
        attrImageView.applyAttrs()
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        attrImageView.setImageResource(resId)
    }

    override fun setBackgroundResource(resId: Int) {
        super.setBackgroundResource(resId)
        attrBackground.setBackgroundResource(resId)
    }
}