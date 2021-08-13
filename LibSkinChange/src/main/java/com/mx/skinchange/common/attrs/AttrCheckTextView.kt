package com.mx.skinchange.common.attrs

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.os.Build
import android.util.AttributeSet
import android.widget.CheckedTextView
import com.mx.skinchange.R
import com.mx.skinchange.base.BaseAttr
import com.mx.skinchange.models.AttrItem

open class AttrCheckTextView(val view: CheckedTextView) : BaseAttr {
    private val checkAttr = AttrItem()
    private val checkTintAttr = AttrItem()


    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrCheckTextView,
            defStyleAttr, 0
        )
        try {
            checkAttr.init(a, R.styleable.AttrCheckTextView_android_checkMark)
            checkTintAttr.init(a, R.styleable.AttrCheckTextView_android_checkMarkTint)
        } finally {
            a.recycle()
        }
        checkAttr.onApplyDrawable { drawable ->
            view.checkMarkDrawable = drawable
        }
        checkTintAttr.onApplyColorStateList { colorStateList ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.checkMarkTintList = colorStateList
            }
        }
        applyAttrs()
    }

    override fun applyAttrs() {
        checkAttr.apply(view.context)
        checkTintAttr.apply(view.context)
    }

    fun setCheckMarkDrawable(resId: Int) {
        checkAttr.setResourceId(resId)
        applyAttrs()
    }

    fun setCheckMarkTintList(tint: ColorStateList?) {
        checkTintAttr.disable()
    }
}