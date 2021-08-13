package com.mx.skinchange.common.attrs

import android.content.res.ColorStateList
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
import com.mx.skinchange.R
import com.mx.skinchange.base.BaseAttr
import com.mx.skinchange.models.AttrItem

open class AttrProgressBar(val view: ProgressBar) : BaseAttr {
    private val indeterminateDrawableAttr = AttrItem()
    private val indeterminateTintAttr = AttrItem()
    private val progressDrawableAttr = AttrItem()
    private val progressTintAttr = AttrItem()


    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrProgressBar,
            defStyleAttr, 0
        )
        try {
            indeterminateDrawableAttr.init(
                a,
                R.styleable.AttrProgressBar_android_indeterminateDrawable
            )
            indeterminateTintAttr.init(a, R.styleable.AttrProgressBar_android_indeterminateTint)

            progressDrawableAttr.init(
                a,
                R.styleable.AttrProgressBar_android_progressDrawable
            )
            progressTintAttr.init(a, R.styleable.AttrProgressBar_android_progressTint)
        } finally {
            a.recycle()
        }
        indeterminateDrawableAttr.onApplyDrawable { drawable ->
            view.indeterminateDrawable?.bounds?.let {
                drawable.bounds = it
            }
            view.indeterminateDrawable = tileifyIndeterminate(drawable)
        }
        indeterminateTintAttr.onApplyColorStateList { colorStateList ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.indeterminateTintList = colorStateList
            }
        }

        progressDrawableAttr.onApplyDrawable { drawable ->
            view.progressDrawable = tileify(drawable, false)
        }
        progressTintAttr.onApplyColorStateList { colorStateList ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.progressTintList = colorStateList
            }
        }

        applyAttrs()
    }

    override fun applyAttrs() {
        indeterminateDrawableAttr.apply(view.context)
        indeterminateTintAttr.apply(view.context)
        progressDrawableAttr.apply(view.context)
        progressTintAttr.apply(view.context)
    }

    /**
     * Converts a drawable to a tiled version of itself. It will recursively
     * traverse layer and state list drawables.
     */
    private fun tileify(drawable: Drawable, clip: Boolean): Drawable {
        if (drawable is LayerDrawable) {
            val N = drawable.numberOfLayers
            val outDrawables = arrayOfNulls<Drawable>(N)
            for (i in 0 until N) {
                val id = drawable.getId(i)
                outDrawables[i] = tileify(
                    drawable.getDrawable(i),
                    id == android.R.id.progress || id == android.R.id.secondaryProgress
                )
            }
            val newBg = LayerDrawable(outDrawables)
            for (i in 0 until N) {
                newBg.setId(i, drawable.getId(i))
            }
            return newBg
        } else if (drawable is BitmapDrawable) {
            val tileBitmap = drawable.bitmap
            val shapeDrawable = ShapeDrawable(getDrawableShape())
            val bitmapShader = BitmapShader(
                tileBitmap,
                Shader.TileMode.REPEAT, Shader.TileMode.CLAMP
            )
            shapeDrawable.paint.shader = bitmapShader
            shapeDrawable.paint.colorFilter = drawable.paint.colorFilter
            return if (clip) ClipDrawable(
                shapeDrawable, Gravity.LEFT,
                ClipDrawable.HORIZONTAL
            ) else shapeDrawable
        }
        return drawable
    }

    /**
     * Convert a AnimationDrawable for use as a barberpole animation.
     * Each frame of the animation is wrapped in a ClipDrawable and
     * given a tiling BitmapShader.
     */
    private fun tileifyIndeterminate(drawable: Drawable): Drawable? {
        if (drawable is AnimationDrawable) {
            val N = drawable.numberOfFrames
            val newBg = AnimationDrawable()
            newBg.isOneShot = drawable.isOneShot
            for (i in 0 until N) {
                val frame = tileify(drawable.getFrame(i), true)
                frame.level = 10000
                newBg.addFrame(frame, drawable.getDuration(i))
            }
            newBg.level = 10000
            return newBg
        }
        return drawable
    }

    private fun getDrawableShape(): Shape {
        val roundedCorners = floatArrayOf(5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f)
        return RoundRectShape(roundedCorners, null, null)
    }

    fun setIndeterminateDrawable(drawable: Drawable?) {
        indeterminateDrawableAttr.disable()
    }

    fun setIndeterminateTintList(tint: ColorStateList?) {
        indeterminateTintAttr.disable()
    }

    fun setProgressDrawable(drawable: Drawable?) {
        progressDrawableAttr.disable()
    }

    fun setProgressTintList(tint: ColorStateList?) {
        progressTintAttr.disable()
    }
}