package com.mx.skinchange.common.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.mx.skinchange.base.ISkinView
import com.mx.skinchange.common.attrs.AttrBackground
import com.mx.skinchange.common.attrs.AttrTextView
import com.mx.skinchange.utils.MXSkinObserver

open class MXSkinTextView @JvmOverloads constructor(
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

    override fun onSkinChange() {
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

    override fun setCompoundDrawables(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawables(left, top, right, bottom)
        attrTextView.disableCompoundDrawables()
    }

    override fun setCompoundDrawablesWithIntrinsicBounds(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
        attrTextView.disableCompoundDrawables()
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