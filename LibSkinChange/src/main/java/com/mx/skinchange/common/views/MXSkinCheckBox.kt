package com.mx.skinchange.common.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.CheckBox
import com.mx.skinchange.base.ISkinView
import com.mx.skinchange.common.attrs.AttrBackground
import com.mx.skinchange.common.attrs.AttrButton
import com.mx.skinchange.common.attrs.AttrTextView
import com.mx.skinchange.utils.MXSkinObserver

open class MXSkinCheckBox @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.checkboxStyle
) : CheckBox(context, attrs, defStyleAttr), ISkinView {

    private var attrBackground: AttrBackground? = null
    private var attrButton: AttrButton? = null
    private var attrTextView: AttrTextView? = null

    init {
        attrBackground = AttrBackground(this)
        attrBackground?.initAttrs(attrs, defStyleAttr)

        attrButton = AttrButton(this)
        attrButton?.initAttrs(attrs, defStyleAttr)

        attrTextView = AttrTextView(this)
        attrTextView?.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return CheckBox::class.java.simpleName
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onSkinChange() {
        attrBackground?.applyAttrs()
        attrButton?.applyAttrs()
        attrTextView?.applyAttrs()
    }

    override fun setButtonDrawable(resId: Int) {
        super.setButtonDrawable(resId)
        attrButton?.setButtonDrawable(resId)
    }

    override fun setButtonTintList(tint: ColorStateList?) {
        super.setButtonTintList(tint)
        attrButton?.setButtonTintList(tint)
    }

    override fun setTextAppearance(resId: Int) {
        super.setTextAppearance(resId)
        attrTextView?.setTextAppearance(resId)
    }

    override fun setTextAppearance(context: Context, resId: Int) {
        super.setTextAppearance(context, resId)
        attrTextView?.setTextAppearance(resId)
    }

    override fun setCompoundDrawablesRelativeWithIntrinsicBounds(
        start: Int,
        top: Int,
        end: Int,
        bottom: Int
    ) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
        attrTextView?.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
    }

    override fun setCompoundDrawablesWithIntrinsicBounds(
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
        attrTextView?.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
    }

    override fun setCompoundDrawables(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawables(left, top, right, bottom)
        attrTextView?.disableCompoundDrawables()
    }

    override fun setCompoundDrawablesWithIntrinsicBounds(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
        attrTextView?.disableCompoundDrawables()
    }

    override fun setBackgroundResource(resid: Int) {
        super.setBackgroundResource(resid)
        attrBackground?.setBackgroundResource(resid)
    }

    override fun setBackgroundTintList(tint: ColorStateList?) {
        super.setBackgroundTintList(tint)
        attrBackground?.setBackgroundTintList(tint)
    }

    override fun setForeground(foreground: Drawable?) {
        super.setForeground(foreground)
        attrBackground?.setForeground(foreground)
    }

    override fun setForegroundTintList(tint: ColorStateList?) {
        super.setForegroundTintList(tint)
        attrBackground?.setForegroundTintList(tint)
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