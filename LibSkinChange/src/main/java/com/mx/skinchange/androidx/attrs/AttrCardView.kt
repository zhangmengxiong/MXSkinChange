package com.mx.skinchange.androidx.attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.mx.skinchange.R
import com.mx.skinchange.base.BaseAttr
import com.mx.skinchange.resource.MXSkinResource
import com.mx.skinchange.base.BaseAttr.Companion.checkResourceId
import com.mx.skinchange.base.BaseAttr.Companion.getResourceId

open class AttrCardView(val view: CardView) : BaseAttr {
    private var backgroundResId = BaseAttr.INVALID_ID

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
        if (resId == BaseAttr.INVALID_ID) {
            return
        }
        val colorStateList = MXSkinResource.getColorStateList(
            view.context,
            resId
        ) ?: return
        view.setCardBackgroundColor(colorStateList)
    }
}