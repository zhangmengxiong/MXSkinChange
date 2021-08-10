package com.mx.skinchange.common_attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import com.mx.skinchange.R
import com.mx.skinchange.common_views.CommonImageView
import com.mx.skinchange.factory.SkinResourceLoader

open class AttrImageView(val view: CommonImageView) : AttrBase {
    private var srcResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrImageView,
            defStyleAttr, 0
        )
        try {
            srcResId = getResourceId(a, R.styleable.AttrImageView_android_src)
        } finally {
            a.recycle()
        }
        applyAttrs()
    }

    override fun applyAttrs() {
        val resId = checkResourceId(srcResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val drawable = SkinResourceLoader.loadDrawable(view.context, resId)
        view.setImageDrawable(drawable)
    }

    fun setImageResource(res: Int) {
        srcResId = res
        applyAttrs()
    }
}