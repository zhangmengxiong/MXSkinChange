package com.mx.skinchange.common.attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.TextView
import com.mx.skinchange.R
import com.mx.skinchange.base.BaseAttr
import com.mx.skinchange.models.AttrItem

open class AttrTextView(val view: TextView) : BaseAttr {
    private val textColorAttr = AttrItem()
    private val textColorHintAttr = AttrItem()

    private val drawableLeftAttr = AttrItem()
    private val drawableTopAttr = AttrItem()
    private val drawableRightAttr = AttrItem()
    private val drawableBottomAttr = AttrItem()

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrTextView,
            defStyleAttr, 0
        )
        try {
            textColorAttr.init(a, R.styleable.AttrTextView_android_textColor)
            textColorHintAttr.init(a, R.styleable.AttrTextView_android_textColorHint)

            drawableLeftAttr.init(
                a, R.styleable.AttrTextView_android_drawableLeft,
                R.styleable.AttrTextView_android_drawableStart
            )
            drawableTopAttr.init(a, R.styleable.AttrTextView_android_drawableTop)
            drawableRightAttr.init(
                a,
                R.styleable.AttrTextView_android_drawableRight,
                R.styleable.AttrTextView_android_drawableEnd
            )
            drawableBottomAttr.init(a, R.styleable.AttrTextView_android_drawableBottom)
        } finally {
            a.recycle()
        }

        textColorAttr.onApplyColorStateList { colorStateList ->
            view.setTextColor(colorStateList)
        }
        textColorHintAttr.onApplyColorStateList { colorStateList ->
            view.setHintTextColor(colorStateList)
        }

        applyAttrs()
    }

    override fun applyAttrs() {
        textColorAttr.apply(view.context)
        textColorHintAttr.apply(view.context)
        applyDrawableRound()
    }

    fun setTextAppearance(resId: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            resId, R.styleable.AttrTextView
        )
        try {
            textColorAttr.init(a, R.styleable.AttrTextView_android_textColor)
            textColorHintAttr.init(a, R.styleable.AttrTextView_android_textColorHint)
        } finally {
            a.recycle()
        }
        applyAttrs()
    }

    fun setCompoundDrawablesRelativeWithIntrinsicBounds(
        start: Int,
        top: Int,
        end: Int,
        bottom: Int
    ) {
        drawableLeftAttr.setResourceId(start)
        drawableTopAttr.setResourceId(top)
        drawableRightAttr.setResourceId(end)
        drawableBottomAttr.setResourceId(bottom)
        applyDrawableRound()
    }

    fun setCompoundDrawablesWithIntrinsicBounds(
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        drawableLeftAttr.setResourceId(left)
        drawableTopAttr.setResourceId(top)
        drawableRightAttr.setResourceId(right)
        drawableBottomAttr.setResourceId(bottom)
        applyDrawableRound()
    }

    private fun applyDrawableRound() {
        val left = drawableLeftAttr.getDrawable(view.context)
        val top = drawableTopAttr.getDrawable(view.context)
        val right = drawableRightAttr.getDrawable(view.context)
        val bottom = drawableBottomAttr.getDrawable(view.context)

        view.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
    }

    fun disableCompoundDrawables() {
        drawableLeftAttr.disable()
        drawableTopAttr.disable()
        drawableRightAttr.disable()
        drawableBottomAttr.disable()
    }
}