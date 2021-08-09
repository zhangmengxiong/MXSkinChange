package com.mx.skinchange.common_attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.TextView
import com.mx.skinchange.R
import com.mx.skinchange.factory.SkinResourceLoader

class AttrTextView(val view: TextView) : AttrBase {
    private var textColorResId = AttrBase.INVALID_ID
    private var textColorHintResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrTextView,
            defStyleAttr, 0
        )
        try {
            if (a.hasValue(R.styleable.AttrTextView_android_textColor)) {
                textColorResId = a.getResourceId(
                    R.styleable.AttrTextView_android_textColor,
                    AttrBase.INVALID_ID
                )
            }
            if (a.hasValue(R.styleable.AttrTextView_android_textColorHint)) {
                textColorHintResId = a.getResourceId(
                    R.styleable.AttrTextView_android_textColorHint,
                    AttrBase.INVALID_ID
                )
            }
        } finally {
            a.recycle()
        }
        applyAttrs()
    }

    override fun applyAttrs() {
        applyTextColor()
        applyTextColorHint()
    }


    private fun applyTextColor() {
        val resId = checkResourceId(textColorResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val skinResId = SkinResourceLoader.loadSkinResourceId(
            view.context,
            resId,
            SkinResourceLoader.TYPE_COLOR
        )
        view.setTextColor(view.resources.getColorStateList(skinResId))
    }

    private fun applyTextColorHint() {
        val resId = checkResourceId(textColorHintResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val skinResId = SkinResourceLoader.loadSkinResourceId(
            view.context,
            resId,
            SkinResourceLoader.TYPE_COLOR
        )
        view.setHintTextColor(view.resources.getColorStateList(skinResId))
    }

    fun setTextAppearance(resId: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            resId, R.styleable.AttrTextView
        )
        try {
            if (a.hasValue(R.styleable.AttrTextView_android_textColor)) {
                textColorResId = a.getResourceId(
                    R.styleable.AttrTextView_android_textColor,
                    AttrBase.INVALID_ID
                )
            }
            if (a.hasValue(R.styleable.AttrTextView_android_textColorHint)) {
                textColorHintResId = a.getResourceId(
                    R.styleable.AttrTextView_android_textColorHint,
                    AttrBase.INVALID_ID
                )
            }
        } finally {
            a.recycle()
        }
        applyAttrs()
    }
}