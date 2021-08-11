package com.mx.skinchange.common.attrs

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.core.widget.CompoundButtonCompat
import com.mx.skinchange.R
import com.mx.skinchange.base.BaseAttr
import com.mx.skinchange.models.AttrItem
import com.mx.skinchange.models.AttrType

open class AttrButton(val view: CompoundButton) : BaseAttr {
    private val buttonAttr = AttrItem()
    private val buttonTintAttr = AttrItem()


    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrButton,
            defStyleAttr, 0
        )
        try {
            buttonAttr.init(a, R.styleable.AttrButton_android_button)
            buttonTintAttr.init(a, R.styleable.AttrButton_android_buttonTint)
        } finally {
            a.recycle()
        }
        buttonAttr.onApplyDrawable { drawable ->
            view.buttonDrawable = drawable
        }
        buttonTintAttr.onApplyColorStateList { colorStateList ->
            CompoundButtonCompat.setButtonTintList(view, colorStateList)
        }
        applyAttrs()
    }

    override fun applyAttrs() {
        buttonAttr.apply(view.context)
        buttonTintAttr.apply(view.context)
    }

    fun setButtonDrawable(resId: Int) {
        buttonAttr.setResourceId(resId)
        applyAttrs()
    }

    fun setButtonTintList(tint: ColorStateList?) {
        buttonTintAttr.disable()
    }
}