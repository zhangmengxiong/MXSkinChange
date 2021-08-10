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
import com.mx.skinchange.R
import com.mx.skinchange.factory.SkinResourceLoader

open class AttrProgressBar(val view: ProgressBar) : AttrBase {
    private var mIndeterminateDrawableResId = AttrBase.INVALID_ID
    private var mProgressDrawableResId = AttrBase.INVALID_ID
    private var mIndeterminateTintResId = AttrBase.INVALID_ID

    override fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a: TypedArray = view.context.obtainStyledAttributes(
            attrs,
            R.styleable.AttrProgressBar,
            defStyleAttr, 0
        )
        try {
            mIndeterminateDrawableResId =
                getResourceId(a, R.styleable.AttrProgressBar_android_indeterminateDrawable)
            mProgressDrawableResId =
                getResourceId(a, R.styleable.AttrProgressBar_android_progressDrawable)
            mIndeterminateTintResId =
                getResourceId(a, R.styleable.AttrProgressBar_android_indeterminateTint)
        } finally {
            a.recycle()
        }


        applyIndeterminateDrawableRes()
        applyProgressDrawableRes()
        applyIndeterminateTintRes()
    }

    override fun applyAttrs() {
    }

    private fun applyIndeterminateDrawableRes() {
        val resId = checkResourceId(mIndeterminateDrawableResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val drawable = SkinResourceLoader.loadDrawable(
            view.context,
            resId
        ) ?: return
        view.indeterminateDrawable?.bounds?.let {
            drawable.bounds = it
        }
        view.indeterminateDrawable = tileifyIndeterminate(drawable)
    }

    private fun applyIndeterminateTintRes() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val resId = checkResourceId(mIndeterminateTintResId)
            if (resId == AttrBase.INVALID_ID) {
                return
            }
            val colorStateList = SkinResourceLoader.loadColorStateList(
                view.context,
                resId
            ) ?: return

            view.indeterminateTintList = colorStateList
        }
    }

    private fun applyProgressDrawableRes() {
        val resId = checkResourceId(mProgressDrawableResId)
        if (resId == AttrBase.INVALID_ID) {
            return
        }
        val drawable = SkinResourceLoader.loadDrawable(
            view.context,
            resId
        ) ?: return
        view.progressDrawable = tileify(drawable, false)
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
}