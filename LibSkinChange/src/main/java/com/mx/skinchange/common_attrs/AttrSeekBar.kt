package com.mx.skinchange.common_attrs

import android.content.res.TypedArray
import android.graphics.BitmapShader
import android.graphics.Shader
import android.graphics.drawable.*
import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.drawable.shapes.Shape
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ProgressBar
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
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