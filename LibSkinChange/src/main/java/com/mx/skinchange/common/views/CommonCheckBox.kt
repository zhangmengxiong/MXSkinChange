package com.mx.skinchange.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.mx.skinchange.base.ISkinView
import com.mx.skinchange.common.attrs.AttrBackground
import com.mx.skinchange.common.attrs.AttrButton
import com.mx.skinchange.common.attrs.AttrTextView

class CommonCheckBox @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatCheckBox(
    context,
    attrs,
    if (defStyleAttr == 0) android.R.attr.checkboxStyle else defStyleAttr
), ISkinView {

    private val attrBackground by lazy { AttrBackground(this) }
    private val attrButton by lazy { AttrButton(this) }
    private val attrTextView by lazy { AttrTextView(this) }

    init {
        val defStyleAttr = if (defStyleAttr == 0) android.R.attr.checkboxStyle else defStyleAttr
        attrBackground.initAttrs(attrs, defStyleAttr)
        attrButton.initAttrs(attrs, defStyleAttr)
        attrTextView.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return "CheckBox"
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onChange() {
        attrBackground.applyAttrs()
        attrButton.applyAttrs()
        attrTextView.applyAttrs()
    }

    override fun setButtonDrawable(resId: Int) {
        super.setButtonDrawable(resId)
        attrButton.setButtonDrawable(resId)
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