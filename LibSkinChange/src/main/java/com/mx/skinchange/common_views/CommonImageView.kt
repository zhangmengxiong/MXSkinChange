package com.mx.skinchange.common_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.mx.skinchange.common_attrs.AttrImageView

class CommonImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr), ISkinView {

    private val imageViewAttr by lazy { AttrImageView(this) }

    init {
        imageViewAttr.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return "ImageView"
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onChange() {
        imageViewAttr.applyAttrs()
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        imageViewAttr.setImageResource(resId)
    }
}