package com.mx.skinchange.androidx.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.mx.skinchange.base.ISkinView
import com.mx.skinchange.common.attrs.AttrBackground
import com.mx.skinchange.utils.MXSkinObserver

open class MXSkinConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ISkinView {

    private val attrBackground by lazy { AttrBackground(this) }

    init {
        attrBackground.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return ConstraintLayout::class.java.name
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