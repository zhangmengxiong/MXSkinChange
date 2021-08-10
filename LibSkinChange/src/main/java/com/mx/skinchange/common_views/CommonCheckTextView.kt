package com.mx.skinchange.common_views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.R
import com.mx.skinchange.common.ISkinView
import com.mx.skinchange.common_attrs.AttrBackground
import com.mx.skinchange.common_attrs.AttrCheckedTextView
import com.mx.skinchange.common_attrs.AttrTextView

class CommonCheckTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatCheckedTextView(
    context,
    attrs,
    if (defStyleAttr == 0) R.attr.checkboxStyle else defStyleAttr
), ISkinView {

    private val attrBackground by lazy { AttrBackground(this) }
    private val attrCheckedTextView by lazy { AttrCheckedTextView(this) }
    private val attrTextView by lazy { AttrTextView(this) }

    init {
        val defStyleAttr = if (defStyleAttr == 0) R.attr.checkboxStyle else defStyleAttr
        attrBackground.initAttrs(attrs, defStyleAttr)
        attrCheckedTextView.initAttrs(attrs, defStyleAttr)
        attrTextView.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return "CheckedTextView"
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onChange() {
        attrBackground.applyAttrs()
        attrCheckedTextView.applyAttrs()
        attrTextView.applyAttrs()
    }

    override fun setCheckMarkDrawable(resId: Int) {
        super.setCheckMarkDrawable(resId)
        attrCheckedTextView.setCheckMarkDrawable(resId)
    }

    override fun setTextAppearance(resId: Int) {
        this.setTextAppearance(context, resId)
    }

    override fun setTextAppearance(context: Context?, resId: Int) {
        super.setTextAppearance(context, resId)
        attrTextView.setTextAppearance(resId)
    }

    override fun setCompoundDrawablesRelativeWithIntrinsicBounds(
        start: Int,
        top: Int,
        end: Int,
        bottom: Int
    ) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
        attrTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
    }

    override fun setCompoundDrawablesWithIntrinsicBounds(
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
        attrTextView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
    }

    override fun setBackgroundResource(resid: Int) {
        super.setBackgroundResource(resid)
        attrBackground.setBackgroundResource(resid)
    }
}