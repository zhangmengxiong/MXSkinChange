package com.mx.skinchange.common.attrs

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.widget.SeekBar
import com.mx.skinchange.R
import com.mx.skinchange.models.AttrItem
import com.mx.skinchange.models.AttrType

open class AttrSeekBarProgressBar(private val seekBar: SeekBar) : AttrProgressBar(seekBar) {
    private val thumbDrawableAttr = AttrItem()
    private val thumbTintAttr = AttrItem()

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrSeekBar,
            defStyleAttr, 0
        )
        try {
            thumbDrawableAttr.init(a, R.styleable.AttrSeekBar_android_thumb)
            thumbTintAttr.init(a, R.styleable.AttrSeekBar_android_thumbTint)
        } finally {
            a.recycle()
        }
        thumbDrawableAttr.onApplyDrawable { drawable ->
            seekBar.thumb = drawable
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            thumbTintAttr.onApplyColorStateList { colorStateList ->
                seekBar.thumbTintList = colorStateList
            }
        }

        applyAttrs()
    }

    override fun applyAttrs() {
        thumbDrawableAttr.apply(view.context)
        thumbTintAttr.apply(view.context)
    }

    fun setThumb(thumb: Drawable?) {
        thumbDrawableAttr.disable()
    }

    fun setThumbTintList(tint: ColorStateList?) {
        thumbTintAttr.disable()
    }
}