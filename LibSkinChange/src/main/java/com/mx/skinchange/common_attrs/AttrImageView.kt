package com.mx.skinchange.common_attrs

import android.annotation.SuppressLint
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import com.mx.skinchange.R
import com.mx.skinchange.common_views.CommonImageView
import com.mx.skinchange.factory.SkinResourceLoader

class AttrImageView(val view: CommonImageView) : AttrBase {
    private var srcResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrImageView,
            defStyleAttr, 0
        )
        try {
            if (a.hasValue(R.styleable.AttrImageView_android_src)) {
                srcResId = a.getResourceId(
                    R.styleable.AttrImageView_android_src,
                    AttrBase.INVALID_ID
                )
            }
        } finally {
            a.recycle()
        }
        applyAttrs()
    }

    @SuppressLint("RestrictedApi")
    override fun applyAttrs() {
        val resId = checkResourceId(srcResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val skinResId = SkinResourceLoader.loadSkinResourceId(
            view.context,
            resId,
            SkinResourceLoader.TYPE_DRAWABLE
        )
        view.setImageDrawable(AppCompatResources.getDrawable(view.context, skinResId))
    }

    fun setImageResource(res: Int) {
        srcResId = res
        applyAttrs()
    }
}