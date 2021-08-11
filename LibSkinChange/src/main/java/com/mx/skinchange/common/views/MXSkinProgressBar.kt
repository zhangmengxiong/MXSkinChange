package com.mx.skinchange.common.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import com.mx.skinchange.base.ISkinView
import com.mx.skinchange.common.attrs.AttrBackground
import com.mx.skinchange.common.attrs.AttrProgressBar
import com.mx.skinchange.utils.MXSkinObserver

open class MXSkinProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ProgressBar(
    context,
    attrs,
    if (defStyleAttr == 0) android.R.attr.progressBarStyle else defStyleAttr
), ISkinView {

    private val attrBackground by lazy { AttrBackground(this) }
    private val attrProgressBar by lazy { AttrProgressBar(this) }

    init {
        val defStyleAttr = if (defStyleAttr == 0) android.R.attr.progressBarStyle else defStyleAttr
        attrBackground.initAttrs(attrs, defStyleAttr)
        attrProgressBar.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return "ProgressBar"
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onSkinChange() {
        attrBackground.applyAttrs()
    }

    override fun setBackgroundResource(resid: Int) {
        super.setBackgroundResource(resid)
        attrBackground.setBackgroundResource(resid)
    }

    override fun setBackgroundTintList(tint: ColorStateList?) {
        super.setBackgroundTintList(tint)
        attrBackground.setBackgroundTintList(tint)
    }

    override fun setForeground(foreground: Drawable?) {
        super.setForeground(foreground)
        attrBackground.setForeground(foreground)
    }

    override fun setForegroundTintList(tint: ColorStateList?) {
        super.setForegroundTintList(tint)
        attrBackground.setForegroundTintList(tint)
    }

    override fun setIndeterminateDrawable(d: Drawable?) {
        super.setIndeterminateDrawable(d)
        attrProgressBar.setIndeterminateDrawable(d)
    }

    override fun setIndeterminateTintList(tint: ColorStateList?) {
        super.setIndeterminateTintList(tint)
        attrProgressBar.setIndeterminateTintList(tint)
    }

    override fun setProgressDrawable(d: Drawable?) {
        super.setProgressDrawable(d)
        attrProgressBar.setProgressDrawable(d)
    }

    override fun setProgressTintList(tint: ColorStateList?) {
        super.setProgressTintList(tint)
        attrProgressBar.setProgressTintList(tint)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (needObserved()) {
            MXSkinObserver.addObserver(this)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (needObserved()) {
            MXSkinObserver.deleteObserver(this)
        }
    }
}