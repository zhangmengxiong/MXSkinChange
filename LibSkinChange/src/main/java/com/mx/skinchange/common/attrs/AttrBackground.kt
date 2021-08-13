package com.mx.skinchange.common.attrs

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.core.view.ViewCompat
import com.mx.skinchange.R
import com.mx.skinchange.base.BaseAttr
import com.mx.skinchange.models.AttrItem

open class AttrBackground(val view: View) : BaseAttr {
    private val backgroundAttr = AttrItem()
    private val backgroundTintAttr = AttrItem()

    private val foregroundAttr = AttrItem()
    private val foregroundTintAttr = AttrItem()

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrBackground,
            defStyleAttr, 0
        )
        try {
            backgroundAttr.init(a, R.styleable.AttrBackground_android_background)
            backgroundTintAttr.init(a, R.styleable.AttrBackground_android_backgroundTint)

            foregroundAttr.init(a, R.styleable.AttrBackground_android_foreground)
            foregroundTintAttr.init(a, R.styleable.AttrBackground_android_foregroundTint)
        } finally {
            a.recycle()
        }

        backgroundAttr.onApplyDrawable { drawable ->
            val paddingLeft: Int = view.paddingLeft
            val paddingTop: Int = view.paddingTop
            val paddingRight: Int = view.paddingRight
            val paddingBottom: Int = view.paddingBottom
            ViewCompat.setBackground(view, drawable)
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
        }
        backgroundTintAttr.onApplyColorStateList { colorStateList ->
            ViewCompat.setBackgroundTintList(view, colorStateList)
        }

        foregroundAttr.onApplyDrawable { drawable ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.foreground = drawable
            }
        }
        foregroundTintAttr.onApplyColorStateList { colorStateList ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.foregroundTintList = colorStateList
            }
        }

        applyAttrs()
    }

    override fun applyAttrs() {
        backgroundAttr.apply(view.context)
        backgroundTintAttr.apply(view.context)

        foregroundAttr.apply(view.context)
        foregroundTintAttr.apply(view.context)
    }


    fun setBackgroundResource(res: Int) {
        backgroundAttr.setResourceId(res)
        applyAttrs()
    }

    fun setBackgroundTintList(tint: ColorStateList?) {
        backgroundTintAttr.disable()
    }

    fun setForeground(foreground: Drawable?) {
        foregroundAttr.disable()
    }

    fun setForegroundTintList(tint: ColorStateList?) {
        foregroundTintAttr.disable()
    }
}