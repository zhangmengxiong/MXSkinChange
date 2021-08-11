package com.mx.skinchange.androidx.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.mx.skinchange.androidx.attrs.AttrCardView
import com.mx.skinchange.base.ISkinView
import com.mx.skinchange.utils.MXSkinObserver

open class MXSkinCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr), ISkinView {

    private var attrBackground: AttrCardView? = null

    init {
        attrBackground = AttrCardView(this)
        attrBackground?.initAttrs(attrs, defStyleAttr)
    }

    override fun getName(): String {
        return "androidx.cardview.widget.CardView"
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onSkinChange() {
        attrBackground?.applyAttrs()
    }

    override fun setCardBackgroundColor(color: ColorStateList?) {
        super.setCardBackgroundColor(color)
        attrBackground?.setCardBackgroundColor(color)
    }

    override fun setCardBackgroundColor(color: Int) {
        super.setCardBackgroundColor(color)
        attrBackground?.setCardBackgroundColor(color)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (needObserved()) {
            MXSkinObserver.addObserver(this)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (needObserved()) {
            MXSkinObserver.deleteObserver(this)
        }
    }
}