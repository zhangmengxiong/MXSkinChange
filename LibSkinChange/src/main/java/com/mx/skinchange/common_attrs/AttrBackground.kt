package com.mx.skinchange.common_attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.core.view.ViewCompat
import com.mx.skinchange.R
import com.mx.skinchange.factory.SkinResourceLoader

open class AttrBackground(val view: View) : AttrBase {
    private var backgroundResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrBackground,
            defStyleAttr, 0
        )
        try {
            backgroundResId = getResourceId(a, R.styleable.AttrBackground_android_background)
        } finally {
            a.recycle()
        }
        applyAttrs()
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

    fun setBackgroundResource(res: Int) {
        backgroundResId = res
        applyAttrs()
    }
}