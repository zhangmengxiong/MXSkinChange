package com.mx.skinchange.common.attrs

import android.content.res.TypedArray
import android.os.Build
import android.util.AttributeSet
import android.widget.SeekBar
import com.mx.skinchange.R
import com.mx.skinchange.factory.SkinResourceLoader

open class AttrSeekBar(private val seekBar: SeekBar) : AttrProgressBar(seekBar) {
    private var mThumbResId = AttrBase.INVALID_ID
    private var mThumbTintResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrSeekBar,
            defStyleAttr, 0
        )
        try {
            mThumbResId = getResourceId(a, R.styleable.AttrSeekBar_android_thumb)
            mThumbTintResId = getResourceId(a, R.styleable.AttrSeekBar_android_thumbTint)
        } finally {
            a.recycle()
        }

        applyThumbRes()
        applyThumbTintRes()
    }

    override fun applyAttrs() {
    }

    private fun applyThumbRes() {
        val resId = checkResourceId(mThumbResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val drawable = SkinResourceLoader.loadDrawable(
            view.context,
            resId
        ) ?: return
        seekBar.thumb = drawable
    }

    private fun applyThumbTintRes() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val resId = checkResourceId(mThumbTintResId)
            if (resId == AttrBase.INVALID_ID) {
                return
            }
            val drawable = SkinResourceLoader.loadColorStateList(
                view.context,
                resId
            ) ?: return
            seekBar.thumbTintList = drawable
        }
    }
}