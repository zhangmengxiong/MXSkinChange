package com.mx.skinchange.common_attrs

import android.content.res.TypedArray
import android.os.Build
import android.util.AttributeSet
import android.widget.CheckedTextView
import com.mx.skinchange.R
import com.mx.skinchange.factory.SkinResourceLoader

open class AttrCheckedTextView(val view: CheckedTextView) : AttrBase {
    private var checkedMarkResId = AttrBase.INVALID_ID
    private var checkMarkTintResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrCheckTextView,
            defStyleAttr, 0
        )
        try {
            checkedMarkResId = getResourceId(a, R.styleable.AttrCheckTextView_android_checkMark)
            checkMarkTintResId =
                getResourceId(a, R.styleable.AttrCheckTextView_android_checkMarkTint)
        } finally {
            a.recycle()
        }

        applyCheckedMarkTintRes()
        applyAttrs()
    }

    override fun applyAttrs() {
        applyCheckedMarkRes()
    }

    private fun applyCheckedMarkRes() {
        val resId = checkResourceId(checkedMarkResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val drawable = SkinResourceLoader.loadDrawable(
            view.context,
            resId
        )
        view.checkMarkDrawable = drawable
    }

    private fun applyCheckedMarkTintRes() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val resId = checkResourceId(checkMarkTintResId)
            if (resId == AttrBase.INVALID_ID) {
                return
            }
            val colorStateList = SkinResourceLoader.loadColorStateList(
                view.context,
                resId
            )
            view.checkMarkTintList = colorStateList
        }
    }

    fun setCheckMarkDrawable(resId: Int) {
        checkedMarkResId = resId
        applyAttrs()
    }
}