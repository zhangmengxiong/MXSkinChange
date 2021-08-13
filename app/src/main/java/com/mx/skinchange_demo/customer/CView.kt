package com.mx.skinchange_demo.customer

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import com.mx.skinchange.base.BaseAttr
import com.mx.skinchange.base.ISkinView
import com.mx.skinchange.resource.MXSkinResource
import com.mx.skinchange_demo.R

class CView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatTextView(context, attrs), ISkinView {
    private var colorResId = BaseAttr.INVALID_ID

    init {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CView,
            0, 0
        )
        try {
            colorResId = BaseAttr.getResourceId(a, R.styleable.CView_color)
        } finally {
            a.recycle()
        }

        onSkinChange()
    }

    override fun getName(): String {
        // 这里返回这个View在xml中定义的名字
        return CView::class.java.name
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onSkinChange() {
        // 皮肤更新
        if (colorResId != BaseAttr.INVALID_ID) {
            val color = MXSkinResource.getColorStateList(context, colorResId)
            setTextColor(color)
        }
    }

    override fun needObserved(): Boolean {
        return true
    }
}