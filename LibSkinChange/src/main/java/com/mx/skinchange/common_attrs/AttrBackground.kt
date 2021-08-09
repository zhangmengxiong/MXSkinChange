package com.mx.skinchange.common_attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.core.view.ViewCompat
import com.mx.skinchange.R
import com.mx.skinchange.factory.SkinResourceLoader

class AttrBackground(val view: View) : AttrBase {
    private var backgroundResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrBackground,
            defStyleAttr, 0
        )
        try {
            if (a.hasValue(R.styleable.AttrBackground_android_background)) {
                backgroundResId = a.getResourceId(
                    R.styleable.AttrBackground_android_background,
                    AttrBase.INVALID_ID
                )
            }
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
        val skinResId = SkinResourceLoader.loadSkinResourceId(
            view.context,
            resId,
            SkinResourceLoader.TYPE_DRAWABLE
        )
        val drawable = SkinResourceLoader.loadDrawable(view.context, skinResId)
        if (drawable != null) {
            val paddingLeft: Int = view.paddingLeft
            val paddingTop: Int = view.paddingTop
            val paddingRight: Int = view.paddingRight
            val paddingBottom: Int = view.paddingBottom
            ViewCompat.setBackground(view, drawable)
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
        }
    }

    fun setBackgroundRes(res: Int) {
        backgroundResId = res
        applyAttrs()
    }
}