package com.mx.skinchange.factory

import android.content.Context
import android.util.AttributeSet
import com.mx.skinchange.MXSkinManager
import com.mx.skinchange.androidx.views.MXSkinCardView
import com.mx.skinchange.androidx.views.MXSkinConstraintLayout
import com.mx.skinchange.androidx.views.MXSkinRecyclerView
import com.mx.skinchange.base.ISkinView
import com.mx.skinchange.common.views.*

object MXSkinViewRegister {
    /**
     * 内置换肤插件
     */
    private val skinDefaultViewList = HashMap<String, Class<out ISkinView>>()

    /**
     * 外部注册换肤插件
     */
    private val skinViewList = HashMap<String, Class<out ISkinView>>()

    init {
        registerDefault(MXSkinLinearLayout::class.java)
        registerDefault(MXSkinRelativeLayout::class.java)
        registerDefault(MXSkinFrameLayout::class.java)
        registerDefault(MXSkinTextView::class.java)
        registerDefault(MXSkinImageView::class.java)
        registerDefault(MXSkinView::class.java)
        registerDefault(MXSkinButton::class.java)
        registerDefault(MXSkinScrollView::class.java)
        registerDefault(MXSkinHorizontalScrollView::class.java)
        registerDefault(MXSkinEditText::class.java)
        registerDefault(MXSkinRadioButton::class.java)
        registerDefault(MXSkinRadioGroup::class.java)
        registerDefault(MXSkinCheckBox::class.java)
        registerDefault(MXSkinCheckTextView::class.java)
        registerDefault(MXSkinProgressBar::class.java)
        registerDefault(MXSkinRatingBar::class.java)
        registerDefault(MXSkinSeekBar::class.java)
        registerDefault(MXSkinViewGroup::class.java)

        try {
            registerDefault(MXSkinCardView::class.java)
        } catch (e: java.lang.Exception) {
        }
        try {
            registerDefault(MXSkinConstraintLayout::class.java)
        } catch (e: java.lang.Exception) {
        }
        try {
            registerDefault(MXSkinRecyclerView::class.java)
        } catch (e: java.lang.Exception) {
        }
    }

    private fun registerDefault(iSkinView: Class<out ISkinView>) {
        val name = getNameByClass(iSkinView) ?: return
        val exitsOne = skinDefaultViewList.keys.firstOrNull { it == name }
        if (exitsOne != null) {
            skinDefaultViewList.remove(exitsOne)
        }
        skinDefaultViewList[name] = iSkinView
    }

    fun register(iSkinView: Class<out ISkinView>) {
        val name = getNameByClass(iSkinView) ?: return
        val exitsOne = skinViewList.keys.firstOrNull { it == name }
        if (exitsOne != null) {
            skinViewList.remove(exitsOne)
        }
        skinViewList[name] = iSkinView
    }

    private fun getNameByClass(iSkinView: Class<out ISkinView>): String? {
        return createSkinView(iSkinView, MXSkinManager.appContext, null)?.getName()
    }

    fun createSkinView(
        clazz: Class<out ISkinView>,
        context: Context?,
        attributeSet: AttributeSet?
    ): ISkinView? {
        try {
            val constructor = clazz.getConstructor(Context::class.java, AttributeSet::class.java)
            return constructor.newInstance(context, attributeSet)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            val constructor =
                clazz.getConstructor(Context::class.java, AttributeSet::class.java, Int::class.java)
            return constructor.newInstance(context, attributeSet, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getRegisterClass(name: String): Class<out ISkinView>? {
        return skinViewList[name] ?: skinDefaultViewList[name]
    }
}