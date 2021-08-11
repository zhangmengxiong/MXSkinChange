package com.mx.skinchange.androidx.attrs

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.mx.skinchange.R
import com.mx.skinchange.base.BaseAttr
import com.mx.skinchange.models.AttrItem

open class AttrCardView(val view: CardView) : BaseAttr {
    private val backgroundAttr = AttrItem()

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrCardView,
            defStyleAttr, 0
        )
        try {
            backgroundAttr.init(a, R.styleable.AttrCardView_cardBackgroundColor)
        } finally {
            a.recycle()
        }
        backgroundAttr.onApplyColorStateList { colorStateList ->
            view.setCardBackgroundColor(colorStateList)
        }
        applyAttrs()
    }

    override fun applyAttrs() {
        backgroundAttr.apply(view.context)
    }

    fun setCardBackgroundColor(color: ColorStateList?) {
        backgroundAttr.disable()
    }

    fun setCardBackgroundColor(color: Int?) {
        backgroundAttr.disable()
    }
}