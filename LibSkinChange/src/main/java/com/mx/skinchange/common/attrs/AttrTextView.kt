package com.mx.skinchange.common.attrs

import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.TextView
import com.mx.skinchange.R
import com.mx.skinchange.factory.SkinResourceLoader

open class AttrTextView(val view: TextView) : com.mx.skinchange.base.AttrBase {
    private var textColorResId = AttrBase.INVALID_ID
    private var textColorHintResId = AttrBase.INVALID_ID
    private var mDrawableBottomResId: Int = AttrBase.INVALID_ID
    private var mDrawableLeftResId: Int = AttrBase.INVALID_ID
    private var mDrawableRightResId: Int = AttrBase.INVALID_ID
    private var mDrawableTopResId: Int = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrTextView,
            defStyleAttr, 0
        )
        try {
            textColorResId = getResourceId(a, R.styleable.AttrTextView_android_textColor)
            textColorHintResId = getResourceId(a, R.styleable.AttrTextView_android_textColorHint)

            mDrawableLeftResId = getResourceId(a, R.styleable.AttrTextView_android_drawableLeft)
            if (mDrawableLeftResId == AttrBase.INVALID_ID) {
                getResourceId(a, R.styleable.AttrTextView_android_drawableStart)
            }
            mDrawableTopResId = getResourceId(a, R.styleable.AttrTextView_android_drawableTop)
            mDrawableRightResId = getResourceId(a, R.styleable.AttrTextView_android_drawableRight)
            if (mDrawableRightResId == AttrBase.INVALID_ID) {
                getResourceId(a, R.styleable.AttrTextView_android_drawableEnd)
            }
            mDrawableBottomResId = getResourceId(a, R.styleable.AttrTextView_android_drawableBottom)
        } finally {
            a.recycle()
        }
        applyAttrs()
    }

    override fun applyAttrs() {
        applyTextColor()
        applyTextColorHint()
        applyDrawableRound()
    }


    private fun applyTextColor() {
        val resId = checkResourceId(textColorResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        view.setTextColor(SkinResourceLoader.loadColorStateList(view.context, resId))
    }

    private fun applyTextColorHint() {
        val resId = checkResourceId(textColorHintResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }

        view.setHintTextColor(SkinResourceLoader.loadColorStateList(view.context, resId))
    }

    fun setTextAppearance(resId: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            resId, R.styleable.AttrTextView
        )
        try {
            textColorResId = getResourceId(a, R.styleable.AttrTextView_android_textColor)
            textColorHintResId = getResourceId(a, R.styleable.AttrTextView_android_textColorHint)
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
        mDrawableLeftResId = start
        mDrawableTopResId = top
        mDrawableRightResId = end
        mDrawableBottomResId = bottom
        applyDrawableRound()
    }

    fun setCompoundDrawablesWithIntrinsicBounds(
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        mDrawableLeftResId = left
        mDrawableTopResId = top
        mDrawableRightResId = right
        mDrawableBottomResId = bottom
        applyDrawableRound()
    }

    private fun applyDrawableRound() {
        val left = if (mDrawableLeftResId != AttrBase.INVALID_ID) {
            SkinResourceLoader.loadDrawable(
                view.context,
                mDrawableLeftResId
            )
        } else null
        val top = if (mDrawableTopResId != AttrBase.INVALID_ID) {
            SkinResourceLoader.loadDrawable(
                view.context,
                mDrawableTopResId
            )
        } else null
        val right = if (mDrawableRightResId != AttrBase.INVALID_ID) {
            SkinResourceLoader.loadDrawable(
                view.context,
                mDrawableRightResId
            )
        } else null
        val bottom = if (mDrawableBottomResId != AttrBase.INVALID_ID) {
            SkinResourceLoader.loadDrawable(
                view.context,
                mDrawableBottomResId
            )
        } else null

        view.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
    }
}