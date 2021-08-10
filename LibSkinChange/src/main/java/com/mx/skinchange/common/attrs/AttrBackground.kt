package com.mx.skinchange.common.attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.core.view.ViewCompat
import com.mx.skinchange.R
import com.mx.skinchange.factory.SkinResourceLoader

open class AttrBackground(val view: View) : com.mx.skinchange.base.AttrBase {
    private var backgroundResId = AttrBase.INVALID_ID
    private var backgroundTintResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrBackground,
            defStyleAttr, 0
        )
        try {
            backgroundResId = getResourceId(a, R.styleable.AttrBackground_android_background)
            backgroundTintResId =
                getResourceId(a, R.styleable.AttrBackground_android_backgroundTint)
        } finally {
            a.recycle()
        }

        applyAttrs()
        applyBackgroundTintRes() // 只在初始化的时候运行一次
    }

    override fun applyAttrs() {
        val resId = checkResourceId(backgroundResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val drawable = SkinResourceLoader.loadDrawable(
            view.context,
            resId
        )
        val paddingLeft: Int = view.paddingLeft
        val paddingTop: Int = view.paddingTop
        val paddingRight: Int = view.paddingRight
        val paddingBottom: Int = view.paddingBottom
        ViewCompat.setBackground(view, drawable)
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
    }

    private fun applyBackgroundTintRes() {
        val resId = checkResourceId(backgroundTintResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val colorStateList = SkinResourceLoader.loadColorStateList(
            view.context,
            resId
        )
        ViewCompat.setBackgroundTintList(view, colorStateList)
    }

    fun setBackgroundResource(res: Int) {
        backgroundResId = res
        applyAttrs()
    }
}