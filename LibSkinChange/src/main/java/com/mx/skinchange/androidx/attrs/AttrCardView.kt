package com.mx.skinchange.androidx.attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.mx.skinchange.R
import com.mx.skinchange.common.attrs.AttrBase
import com.mx.skinchange.factory.SkinResourceLoader

open class AttrCardView(val view: CardView) : com.mx.skinchange.base.AttrBase {
    private var backgroundResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrCardView,
            defStyleAttr, 0
        )
        try {
            backgroundResId = getResourceId(a, R.styleable.AttrCardView_cardBackgroundColor)
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
        val colorStateList = SkinResourceLoader.loadColorStateList(
            view.context,
            resId
        ) ?: return
        view.setCardBackgroundColor(colorStateList)
    }
}