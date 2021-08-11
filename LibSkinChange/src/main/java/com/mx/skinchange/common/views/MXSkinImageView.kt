package com.mx.skinchange.common.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.mx.skinchange.base.ISkinView
import com.mx.skinchange.common.attrs.AttrBackground
import com.mx.skinchange.common.attrs.AttrImageView
import com.mx.skinchange.utils.MXSkinObserver

open class MXSkinImageView @JvmOverloads constructor(
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

    override fun onSkinChange() {
        attrBackground.applyAttrs()
        attrImageView.applyAttrs()
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        attrImageView.setImageResource(resId)
    }

    override fun setImageTintList(tint: ColorStateList?) {
        super.setImageTintList(tint)
        attrImageView.setImageTintList(tint)
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