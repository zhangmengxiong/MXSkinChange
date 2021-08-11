package com.mx.skinchange.common.attrs

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import com.mx.skinchange.R
import com.mx.skinchange.base.BaseAttr
import com.mx.skinchange.models.AttrItem
import com.mx.skinchange.models.AttrType

open class AttrImageView(val view: ImageView) : BaseAttr {
    private val srcAttr = AttrItem()
    private val srcTintAttr = AttrItem()


    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrImageView,
            defStyleAttr, 0
        )
        try {
            srcAttr.init(a, R.styleable.AttrImageView_android_src)
            srcTintAttr.init(
                a,
                R.styleable.AttrImageView_android_tint,
                R.styleable.AttrImageView_tint
            )
        } finally {
            a.recycle()
        }
        srcAttr.onApplyDrawable { drawable ->
            view.setImageDrawable(drawable)
        }
        srcTintAttr.onApplyColorStateList { colorStateList ->
            ImageViewCompat.setImageTintList(view, colorStateList)
        }
        applyAttrs()
    }

    override fun applyAttrs() {
        srcAttr.apply(view.context)
        srcTintAttr.apply(view.context)
    }


    fun setImageResource(resId: Int) {
        srcAttr.setResourceId(resId)
        applyAttrs()
    }

    fun setImageTintList(tint: ColorStateList?) {
        srcTintAttr.disable()
    }
}