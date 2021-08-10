package com.mx.skinchange.androidx.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.mx.skinchange.androidx.attrs.AttrCardView
import com.mx.skinchange.base.ISkinView

open class CommonCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr), ISkinView {

    private val attrBackground by lazy { AttrCardView(this) }

    init {
        attrBackground.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return "androidx.cardview.widget.CardView"
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onChange() {
        attrBackground.applyAttrs()
    }
}