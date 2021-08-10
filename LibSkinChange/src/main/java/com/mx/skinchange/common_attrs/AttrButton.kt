package com.mx.skinchange.common_attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.core.widget.CompoundButtonCompat
import com.mx.skinchange.R
import com.mx.skinchange.factory.SkinResourceLoader

class AttrButton(val view: CompoundButton) : AttrBase {
    private var buttonResId = AttrBase.INVALID_ID
    private var buttonTintResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrButton,
            defStyleAttr, 0
        )
        try {
            buttonResId = getResourceId(a, R.styleable.AttrButton_android_button)
            buttonTintResId = getResourceId(a, R.styleable.AttrButton_android_buttonTint)
        } finally {
            a.recycle()
        }
        applyAttrs()
    }

    override fun applyAttrs() {
        applyButtonRes()
        applyButtonTintRes()
    }

    private fun applyButtonRes() {
        val resId = checkResourceId(buttonResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val drawable = SkinResourceLoader.loadDrawable(
            view.context,
            resId
        )
        view.buttonDrawable = drawable
    }

    private fun applyButtonTintRes() {
        val resId = checkResourceId(buttonTintResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val colorStateList = SkinResourceLoader.loadColorStateList(
            view.context,
            resId
        )
        CompoundButtonCompat.setButtonTintList(view, colorStateList)
    }

    fun setButtonDrawable(res: Int) {
        buttonResId = res
        applyAttrs()
    }
}