package com.mx.skinchange.common.attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.core.widget.ImageViewCompat
import com.mx.skinchange.R
import com.mx.skinchange.common.views.CommonImageView
import com.mx.skinchange.factory.SkinResourceLoader

open class AttrImageView(val view: CommonImageView) : com.mx.skinchange.base.AttrBase {
    private var srcResId = AttrBase.INVALID_ID
    private var srcTintResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrImageView,
            defStyleAttr, 0
        )
        try {
            srcResId = getResourceId(a, R.styleable.AttrImageView_android_src)
            srcTintResId = getResourceId(a, R.styleable.AttrImageView_android_tint)
            if (srcTintResId == AttrBase.INVALID_ID) {
                srcTintResId = getResourceId(a, R.styleable.AttrImageView_tint)
            }
        } finally {
            a.recycle()
        }

        applyAttrs()
        applyTintRes() // 初始化运行一次
    }

    override fun applyAttrs() {
        val resId = checkResourceId(srcResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val drawable = SkinResourceLoader.loadDrawable(view.context, resId)
        view.setImageDrawable(drawable)
    }

    private fun applyTintRes() {
        val resId = checkResourceId(srcTintResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val colorStateList = SkinResourceLoader.loadColorStateList(view.context, resId)
        ImageViewCompat.setImageTintList(view, colorStateList)
    }

    fun setImageResource(res: Int) {
        srcResId = res
        applyAttrs()
    }
}